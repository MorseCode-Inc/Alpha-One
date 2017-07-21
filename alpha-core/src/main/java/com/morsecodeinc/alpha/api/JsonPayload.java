package com.morsecodeinc.alpha.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import inc.morsecode.json.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Created by morsecode on 7/20/2017.
 */
public class JsonPayload extends JsonObject {

    private JsonObject json;

    public JsonPayload() {
        this.json= this;
    }

    public ResponseEntity<JsonNode> asResponse(HttpStatus status) {
        ObjectMapper parser= new ObjectMapper();
        json.set("http_code", status.value());
        json.set("http_status", status.value());

        try {
            JsonNode payload = parser.readTree(json.toString());

            ResponseEntity<JsonNode> response = new ResponseEntity<JsonNode>(payload, status);
            return response;
        } catch (IOException iox) {
            iox.printStackTrace();  // will go to std error.
            return null;
        }
    }
}
