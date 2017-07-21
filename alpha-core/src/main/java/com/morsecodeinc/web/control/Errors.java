package com.morsecodeinc.web.control;

import com.fasterxml.jackson.databind.JsonNode;
import com.morsecodeinc.alpha.api.JsonPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by morsecode on 7/18/2017.
 */
@RestControllerAdvice
public class Errors {

    private final Logger LOG= LoggerFactory.getLogger(Errors.class);

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBadRequest(HttpRequest request) {
        LOG.error("Got a Bad Request. ["+ request +"]");
        Map<String, Object> response= new HashMap<>();
        return response;
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleNullPointer(HttpRequest request, NullPointerException npe) {
        LOG.error("Null Pointer. ["+ request +"]", npe);
        Map<String, Object> response= new HashMap<>();
        return response;
    }

}
