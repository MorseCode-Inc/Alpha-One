package test.alpha_core.zk;

import inc.morsecode.zk.JacksonEncoder;
import inc.morsecode.zk.ZooUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;

public class MoreZooTests {


    public void testWrite() {
        System.out.println("hello");
    }


    public static void main(String[] args) throws Exception {
        CuratorFramework zk= CuratorFrameworkFactory.newClient(
                "zk11.hdfs:2181,zk12.hdfs:2181,zk13.hdfs:2181",
                30000,
                30000,
                (i, l, retrySleeper) -> (i < 3) );

        ZooUtils z= new ZooUtils(zk, new JacksonEncoder());

        Pojo pojo= new Pojo();
        pojo.setName("Brad Morse");
        pojo.setKey("bmorse");
        pojo.setTimestamp(System.currentTimeMillis());
        z.persist("/hello/there/again", "pojo", pojo);

        Pojo p= (Pojo)z.read("/hello/there/again", "pojo");

        System.out.println(p);
    }


}
