package com.morsecodeinc.alpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by morsecode on 7/16/2017.
 */
@ComponentScan({"com.morsecodeinc.alpha"})
@SpringBootApplication
public class AlphaOne {

    private static Logger LOG= LoggerFactory.getLogger(AlphaOne.class);

    public static void main(String[] args) {
        SpringApplication.run(AlphaOne.class, args);
        LOG.info("READY");
    }

}


