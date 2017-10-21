package inc.morsecode.util.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ZooUtils implements ConnectionStateListener {

    private CuratorFramework zk;
    private StorageCodec encoder;

    private boolean wasConnected= false;
    private boolean isConnected= false;

    public ZooUtils(@Autowired @Qualifier("zookeeper") CuratorFramework zk, @Autowired StorageCodec encoder) {
        this.zk= zk;
        this.encoder= encoder;
        zk.getConnectionStateListenable().addListener(this);
        try {
            this.zk.start();
        } catch (IllegalStateException alreadyStarted) { }
    }

    public CuratorFramework curator() { return zk(); }

    public ZooUtils persist(String path, String key, Double value) throws Exception { return set(path, key, ""+ value, CreateMode.PERSISTENT, encoder); }
    public ZooUtils persist(String path, String key, Boolean value) throws Exception { return set(path, key, ""+ value, CreateMode.PERSISTENT, encoder); }
    public ZooUtils persist(String path, String key, Byte value) throws Exception { return set(path, key, ""+ value, CreateMode.PERSISTENT, encoder); }
    public ZooUtils persist(String path, String key, Integer value) throws Exception { return set(path, key, ""+ value, CreateMode.PERSISTENT, encoder); }
    public ZooUtils persist(String path, String key, Long value) throws Exception { return set(path, key, ""+ value, CreateMode.PERSISTENT, encoder); }
    public ZooUtils persist(String path, String key, String value) throws Exception { return set(path, key, ""+ value, CreateMode.PERSISTENT, encoder); }
    public ZooUtils persist(String path, String key, Object value) throws Exception { return set(path, key, value, CreateMode.PERSISTENT, encoder); }

    public ZooUtils watch(String path, BackgroundCallback callback) throws Exception {
        zk().getChildren().inBackground(callback).forPath(path);
        return this;
    }

    public ZooUtils set(String path, String key, Object value, CreateMode mode, StorageCodec encoder) throws Exception {
        ensure(path);
        try {
            zk().create().withMode(mode).forPath(path + "/" + key, encoder.write(value));
        } catch (KeeperException.NodeExistsException exists) {
            zk().setData().forPath(path +"/"+ key, encoder.write(value));
        }
        return this;
    }

    public Object read(String path, String key) throws Exception {
        return encoder.read(zk().getData().forPath(path +"/"+ key));
    }


    public ZooUtils delete(String path, String key, BackgroundCallback callback) throws Exception {
        zk().delete().inBackground(callback).forPath(path +"/"+ key);
        return this;
    }

    public ZooUtils delete(String path, String key) throws Exception {
        zk().delete().guaranteed().forPath(path +"/"+ key);
        return this;
    }

    public ZooUtils ensure(String path) throws Exception {
        String[] nodes= path.split("/");
        String base= "";
        for (String node : nodes) {
            if (node == null || "".equals(node)) { continue; }
            try {
                Stat stat= zk().checkExists().forPath(base + "/" + node);
                if (stat == null) {
                    String r= zk().create().withMode(CreateMode.PERSISTENT).forPath(base + "/" + node, "".getBytes());
                }
            } catch (Exception failed) {
                // failed.printStackTrace();
                throw failed;
            }

            base+= "/"+ node;
        }
        return this;
    }

    private CuratorFramework zk() { return this.zk; }

    public void close() { zk().close(); }

    public boolean isConnected() { return isConnected; }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
        this.wasConnected= this.isConnected;
        this.isConnected= connectionState.isConnected();
    }
}
