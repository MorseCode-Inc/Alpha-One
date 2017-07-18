package com.morsecodeinc.web.control;

import com.morsecodeinc.web.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by morsecode on 7/16/2017.
 */
@RestController
@RequestMapping("/")
public class DefaultController {

    private static final Logger LOG= LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private Bootstrap bootstrap;

    public DefaultController() {
        LOG.info("Class loaded.");
    }

    @RequestMapping(path="/index", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> index() {
        LOG.info("HERE, running on port "+ bootstrap.getPort());
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

}
