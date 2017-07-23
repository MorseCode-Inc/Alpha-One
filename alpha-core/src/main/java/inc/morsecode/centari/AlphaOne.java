package inc.morsecode.centari;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by morsecode on 7/16/2017.
 */
@SpringBootApplication
public class AlphaOne {

    private static Logger LOG= LoggerFactory.getLogger(AlphaOne.class);

    public static void main(String[] args) {
        SpringApplication.run(AlphaOne.class, args);
        LOG.info("READY");
    }

}


