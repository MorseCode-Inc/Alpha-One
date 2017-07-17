package com.morsecodeinc.web;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Created by morsecode on 7/16/2017.
 */
@Service
public class Bootstrap implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private int port= 0;

    @Override
    public void onApplicationEvent(final EmbeddedServletContainerInitializedEvent event) {
        port= event.getEmbeddedServletContainer().getPort();
    }

    public int getPort() {
        return port;
    }

}
