package com.morsecodeinc.alpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.*;
import org.springframework.stereotype.Component;


/**
 * Created by morsecode on 7/16/2017.
 */
@Component
@EnableAutoConfiguration
public class InitDotD implements ApplicationContextInitializer, ApplicationContextAware, ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private Logger LOG= LoggerFactory.getLogger(InitDotD.class);

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        LOG.info("INITIALIZE: "+ context);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        LOG.info("SET CONTEXT: "+ context);
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        LOG.info("CONTAINER INITIALIZED: "+ event);
    }
}
