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
@ComponentScan({"com.morsecodeinc.web"})
@ComponentScan({"com.morsecodeinc.web.control"})
@SpringBootApplication
public class AlphaOne implements SpringApplicationRunListener {

    private static Logger LOG= LoggerFactory.getLogger(AlphaOne.class);

    public static void main(String[] args) {
        LOG.info("BOOTSTRAP WEBSERVER");
        SpringApplication.run(AlphaOne.class, args);
        LOG.info("STARTUP COMPLETE");
    }

    @Override
    public void starting() {
        LOG.info("starting");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext configurableApplicationContext) {
        LOG.info("context loaded");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext configurableApplicationContext) {
        LOG.info("context prepared");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment configurableEnvironment) {
        LOG.info("environment prepared");
    }

    @Override
    public void finished(ConfigurableApplicationContext configurableApplicationContext, Throwable throwable) {
        LOG.info("finished");
    }

}


