package com.morsecodeinc.web.model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for managing field definitions
 * Created by morsecode on 7/16/2017.
 */
@RestController
public class Fields {

    @RequestMapping(path = "/hello")
    public String hello() {

        return "hello";

    }
}
