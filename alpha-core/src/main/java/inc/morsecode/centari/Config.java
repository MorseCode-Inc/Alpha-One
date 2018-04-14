package inc.morsecode.centari;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Map;

/**
 * Created by morsecode on 7/16/2017.
 */
@ComponentScan(
{"inc.morsecode.centari"
,"inc.morsecode.web.resource"
,"inc.morsecode.web.service"
,"inc.morsecode.web.control"
,"inc.morsecode.web.security"
,"inc.morsecode.web.helper"
})
@Configuration
@EnableAutoConfiguration
public class Config extends WebMvcConfigurerAdapter {

    private static final Logger LOG= LoggerFactory.getLogger(Config.class);

    @Autowired
    ResourceHttpRequestHandler resourceHandler;

    @Autowired
    private ViewResolver viewResolver;

    @Autowired
    private Routes.RoutesYml routes;

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/flight-records/");
        //for (Routes.Route r : routes.get()){
            //registry.addViewController(r.getPath()).setViewName(r.getView());
        //}

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/favicon.ico");


    }

    @Bean(name="zookeeper")
    public CuratorFramework zookeeper() {

        RetryPolicy retryPolicy= new RetryPolicy() {
            @Override
            public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                return i < 5;
            }
        };
        CuratorFramework zk= CuratorFrameworkFactory.newClient("zk11.hdfs,zk12.hdfs,zk13.hdfs", 3000, 9000, retryPolicy);
        return zk;
    }


}
