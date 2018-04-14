package inc.morsecode.centari;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by morsecode on 7/16/2017.
 */
@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:/routes.properties")
@ConfigurationProperties(prefix="routes")

/**
 *
 */
public class Routes {

    private Logger LOG= LoggerFactory.getLogger(Routes.class);

    @PostConstruct
    public void init() {
        System.out.println(this);
    }

    public static class RoutesYml {
        private Map<String, String> routes= new HashMap<>();

        public Map<String, String> getRoutes() { return routes; }
    }

    @Bean
    public RoutesYml getRoutes() {
        // spring does some magic..
        return new RoutesYml();
    }


}
