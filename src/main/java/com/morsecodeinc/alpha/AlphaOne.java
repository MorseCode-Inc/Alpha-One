package com.morsecodeinc.alpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by morsecode on 7/16/2017.
 */
@SpringBootApplication
@ComponentScan("com.morsecodeinc.web.control")
public class AlphaOne {

    public static void main(String[] args) {
        SpringApplication.run(AlphaOne.class, args);
    }

}
