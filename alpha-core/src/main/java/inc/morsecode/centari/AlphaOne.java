package inc.morsecode.centari;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by morsecode on 7/16/2017.
 */
@SpringBootApplication
@EnableConfigurationProperties
public class AlphaOne {

    private static Logger LOG= LoggerFactory.getLogger(AlphaOne.class);

    public static void main(String[] args) {
        SpringApplication.run(AlphaOne.class, args);
        LOG.info("READY");
    }

    @Bean
    @ConfigurationProperties
    public ConfigMap configMap() {
        return new ConfigMap();
    }

    public static class ConfigMap {
        private Map<String, Object> info = new HashMap<String, Object>();

        public Map<String, Object> getInfo() {
            return this.info;
        }
    }
}


