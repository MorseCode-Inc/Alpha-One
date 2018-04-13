package test.alpha_core.zk;

import inc.morsecode.zk.SerializedEncoder;
import inc.morsecode.zk.ZooUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;

import java.util.Date;

public class ZooUtilsTests {


    public void testWrite() {
        System.out.println("hello");
    }


    public static void main(String[] args) throws Exception {
        CuratorFramework zk= CuratorFrameworkFactory.newClient(
                "zk11.hdfs:2181,zk12.hdfs:2181,zk13.hdfs:2181",
                30000,
                30000,
                (i, l, retrySleeper) -> (i < 3) );

        ZooUtils z= new ZooUtils(zk, new SerializedEncoder());

        z.ensure("/hello/there/world");
        String path= "/hello/there/world";

        z.watch(path +"/long", new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                if (curatorEvent.getData() == null) {
                    curatorFramework.getChildren().inBackground(this).forPath(curatorEvent.getPath());
                    return;
                }
                System.out.println("event: "+ curatorEvent.getPath() +" changed "+ curatorEvent.getData());
            }
        });

        z.persist("/hello/there/world", "id", 201);
        z.persist(path, "integer", 201);
        z.persist(path, "double", 2.01);
        z.persist(path, "boolean", true);
        z.persist(path, "long", System.currentTimeMillis());

        Date now= new Date();

        SerializedEncoder e= new SerializedEncoder();
        System.out.println(e.looksNumeric("1.2") +" : 1.2 looks numeric");
        System.out.println(e.looksNumeric("-1.2") +" : -1.2 looks numeric");
        System.out.println(e.looksNumeric("45") +" : 45 looks numeric");

        z.persist(path, "date", now);

        Date when= (Date) z.read(path, "date");
        System.out.println("WHEN: "+ when);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) { }

        z.close();
    }


}
