package com.morsecodeinc.alpha;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for managing field definitions
 * Created by morsecode on 7/16/2017.
 */
@RestController()
@RequestMapping("/something")
public class SomeController {

    @RequestMapping(path = "/test")
    public String hello() {

        return "test";

    }

}
