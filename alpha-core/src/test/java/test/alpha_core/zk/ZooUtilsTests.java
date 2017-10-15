package test.alpha_core.zk;

import inc.morsecode.util.zk.ZooUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.utils.ZookeeperFactory;
import org.junit.Test;

public class ZooUtilsTests {



    public static void main(String[] args) {
        CuratorFramework zk= CuratorFrameworkFactory.newClient(
                "zk11.hdfs,zk12.hdfs,zk13.hdfs",
                3000,
                10,
                (i, l, retrySleeper) -> (i < 3) );

        ZooUtils z= new ZooUtils(zk);

        z.ensure("/hello/there/world");
    }


}
