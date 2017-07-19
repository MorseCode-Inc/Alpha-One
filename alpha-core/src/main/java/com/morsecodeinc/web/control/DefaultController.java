package com.morsecodeinc.web.control;

import com.morsecodeinc.alpha.InitDotD;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by morsecode on 7/16/2017.
 */
@RestController
@RequestMapping("/")
public class DefaultController {

    private static final Logger LOG= LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private InitDotD bootstrap;

    public DefaultController() {
        LOG.info("Class loaded.");
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public ResponseEntity<String> index() {
        LOG.info("HERE, i'm running...");
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

}
