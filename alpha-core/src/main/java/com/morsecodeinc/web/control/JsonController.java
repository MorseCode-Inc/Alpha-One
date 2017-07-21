package com.morsecodeinc.web.control;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morsecodeinc.alpha.api.JsonPayload;
import org.eclipse.jetty.server.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by morsecode on 7/16/2017.
 */
@RestController
@RestControllerAdvice
@RequestMapping(path= "/api", headers = {"Content-Type=application/json"})
public class JsonController {

    private static final Logger LOG= LoggerFactory.getLogger(JsonController.class);

    //@Autowired
    //private InitDotD bootstrap;


    @RequestMapping(path="/", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> index(Model model, Session session) throws IOException {
        LOG.info("HERE, i'm running...");
        ObjectMapper mapper= new ObjectMapper();

        JsonPayload payload= new JsonPayload();

        // set whatever data on the payload we need to
        payload.set("model", model.asMap());

        return payload.asResponse(HttpStatus.OK);
    }

    @RequestMapping(path="/page", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> page(Model model, HttpServletRequest httpReq) {

        JsonPayload payload= new JsonPayload();

        // set whatever data on the payload we need to
        model.addAttribute("test", true);
        model.addAttribute("client", httpReq.getRemoteAddr());

        payload.merge(model.asMap());

        return payload.asResponse(HttpStatus.OK);

    }



    @ExceptionHandler({Throwable.class})
    @RequestMapping(headers = {"Content-Type:application/json"})
    public ResponseEntity<JsonNode> handleAny(HttpServletRequest request, Throwable error) {
        JsonPayload payload= new JsonPayload();

        payload.set("error_message", error.getMessage());
        payload.set("error", error.getClass().getSimpleName());

        return payload.asResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}