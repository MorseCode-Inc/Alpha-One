package com.morsecodeinc.alpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Created by morsecode on 7/16/2017.
 */
@ComponentScan(
{"com.morsecodeinc.alpha"
,"com.morsecodeinc.web.resource"
,"com.morsecodeinc.web.service"
,"com.morsecodeinc.web.control"
,"com.morsecodeinc.web.security"
,"com.morsecodeinc.web.helper"
})
@SpringBootApplication
public class AlphaOne {

    private static Logger LOG= LoggerFactory.getLogger(AlphaOne.class);

    public static void main(String[] args) {
        LOG.info("BOOTSTRAP WEBSERVER");
        SpringApplication.run(AlphaOne.class, args);
        LOG.info("STARTUP COMPLETE");
    }

}


