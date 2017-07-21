package com.morsecodeinc.web.control;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morsecodeinc.alpha.InitDotD;
import org.eclipse.jetty.server.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by morsecode on 7/16/2017.
 */
@RestController
@RequestMapping(path= "/api", headers = {"Content-Type=application/json"})
public class JsonController {

    private static final Logger LOG= LoggerFactory.getLogger(JsonController.class);

    @Autowired
    private InitDotD bootstrap;


    public JsonController() {
        LOG.info("Class loaded.");
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> index(Model model, Session session) throws IOException {
        LOG.info("HERE, i'm running...");
        ObjectMapper mapper= new ObjectMapper();
        try {
            JsonNode json = mapper.readTree("{\"name\":\"alpha-one\"}");
            return new ResponseEntity<JsonNode>(json, HttpStatus.OK);
        } catch (IOException iox) {
            throw iox;
        }
    }

    @RequestMapping(path="/page", method=RequestMethod.GET)
    public String page(Model model) {
        return "page";
    }

}
