package com.morsecodeinc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by morsecode on 7/16/2017.
 */
@SpringBootConfiguration
public class Config {

    @Value("${server.port}")
    private int port;


    @Bean(name="server.port")
    public int getPort() {
        return port;
    }
}
