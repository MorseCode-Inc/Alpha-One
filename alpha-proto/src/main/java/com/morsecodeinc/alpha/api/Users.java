package com.morsecodeinc.alpha.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.morsecodeinc.alpha.api.JsonPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by morsecode on 7/20/2017.
 */
@RestController
@RequestMapping(path= "/api/users", headers= {"Content-Type=application/json"})
public class Users {


    @RequestMapping("/{id}")
    public ResponseEntity<JsonNode> get(@RequestParam int id, Model model, HttpServletRequest httpReq) {


        return new JsonPayload(model).asResponse200();
    }


}
