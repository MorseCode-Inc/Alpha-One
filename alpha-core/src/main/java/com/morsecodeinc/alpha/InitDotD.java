package com.morsecodeinc.alpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.Map;
import java.util.function.Consumer;


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
        for (String key : context.getBeansOfType(AbstractHandlerMethodMapping.class).keySet()) {

            Map<RequestMappingInfo, HandlerMethod> methods= context.getBean(key, AbstractHandlerMethodMapping.class).getHandlerMethods();

            methods.entrySet().stream().forEach(
                    new Consumer<Map.Entry<RequestMappingInfo, HandlerMethod>>() {
                        @Override
                        public void accept(Map.Entry<RequestMappingInfo, HandlerMethod> entry) {
                            LOG.info("Endpoint Discovered: "+ entry.getValue());
                            // TODO: collect all of the exposed endpoints
                        }
                    }
            );
        }
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        LOG.info("CONTAINER INITIALIZED: "+ event);
    }
}
