package inc.morsecode.util.zk;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ZooUtils {

    private CuratorFramework zk;

    public ZooUtils(@Autowired @Qualifier("zookeeper") CuratorFramework zk) {
        this.zk= zk;
    }

    public ZooUtils set(String path, String key, Object value) throws Exception {
        zk.getChildren().forPath(path);
        return this;
    }

    public ZooUtils ensure(String path) {
        String[] nodes= path.split("/");
        String base= "";
        for (String node : nodes) {
            if (node == null || "".equals(node)) { continue; }
            try {
                zk.checkExists().forPath(base + "/" + node);
            } catch (Exception error) {

            }

            base+= "/"+ node;
        }
        return this;
    }
}
