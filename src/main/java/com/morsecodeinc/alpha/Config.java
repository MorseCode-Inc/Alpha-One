package com.morsecodeinc.alpha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by morsecode on 7/16/2017.
 */
@Configuration
public class Config {

    private static final Logger LOG= LoggerFactory.getLogger(Config.class);

    public Config() {
        LOG.info("Loading Configuration");
    }

}
