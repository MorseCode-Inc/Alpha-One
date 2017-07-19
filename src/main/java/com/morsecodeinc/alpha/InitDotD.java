package com.morsecodeinc.alpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by morsecode on 7/16/2017.
 */
@Component
public class InitDotD implements ApplicationContextInitializer, ApplicationContextAware, ApplicationListener<EmbeddedServletContainerInitializedEvent>, HandlerInterceptor {

    private Logger LOG= LoggerFactory.getLogger(InitDotD.class);

    /*
    @Autowired
    HandlerExecutionChain handlers;

    public void setHandlers(HandlerExecutionChain handlers) {
        this.handlers = handlers;
        LOG.info("got the list of handlers");
        handlers.addInterceptor(this);
    }
    */


    //@Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOG.info("pre handle: "+ httpServletRequest);
        return false;
    }

    //@Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOG.info("post handle: "+ httpServletRequest);

    }

    //@Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LOG.info("after: "+ httpServletRequest);

    }

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
