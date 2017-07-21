package com.morsecodeinc.alpha.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import inc.morsecode.json.JsonArray;
import inc.morsecode.json.JsonObject;
import inc.morsecode.json.JsonValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.io.IOException;

/**
 * Created by morsecode on 7/20/2017.
 */
public class JsonPayload extends JsonObject {

    private JsonObject json;
    private JsonObject header;

    public JsonPayload() {
        this.json= this;
        this.header= new JsonObject();
        json.set("header", header);
    }

    public JsonPayload(Model model) {
        this();
        merge(model.asMap());
    }

    public JsonObject getHeader() { return header; }

    /*
     * Seems like it would be handy to quickly set header data
     * notice the chaining that would happen, returning the reference
     * to the header instead of this.
     */
    public JsonObject setHeader(String name, Long value) { return header.set(name, value); }
    public JsonObject setHeader(String name, Short value) { return header.set(name, value); }
    public JsonObject setHeader(String name, Double value) { return header.set(name, value); }
    public JsonObject setHeader(String name, String value) { return header.set(name, value); }
    public JsonObject setHeader(String name, Boolean value) { return header.set(name, value); }
    public JsonObject setHeader(String name, Integer value) { return header.set(name, value); }
    public JsonObject setHeader(String name, JsonArray value) { return header.set(name, value); }
    public JsonObject setHeader(String name, JsonValue value) { return header.set(name, value); }


    /**
     * This would be used as part of the return line in a REST controller
     *
     * Use Example:
     * <code>
     *
     *   @RequestMapping(path="/", method=RequestMethod.GET)
     *   public ResponseEntity<JsonNode> index(Model model, Session session) {
     *       JsonPayload payload= new JsonPayload();
     *       // set whatever data on the payload we need to
     *       return payload.asResponse(HttpStatus.OK);
     *   }
     * </code>
     *
     * @param status
     * @return
     */
    public ResponseEntity<JsonNode> asResponse(HttpStatus status) {
        ObjectMapper parser= new ObjectMapper();

        setHeader(
                "status", status.value())
                .set("http", status.getReasonPhrase()
        );

        try {
            // TODO: find better way.. or trust the json-lite serialization
            JsonNode payload = parser.readTree(json.toString());
            return new ResponseEntity<>(payload, status);
        } catch (IOException iox) {
            iox.printStackTrace();  // will go to std error.
            return null;
        }
    }


    /*
     * Some convenience methods
     */
    public ResponseEntity<JsonNode> asResponse200() { return asResponse(HttpStatus.OK); }
    public ResponseEntity<JsonNode> asResponse400() { return asResponse(HttpStatus.BAD_REQUEST); }
    public ResponseEntity<JsonNode> asResponse401() { return asResponse(HttpStatus.UNAUTHORIZED); }
    public ResponseEntity<JsonNode> asResponse404() { return asResponse(HttpStatus.NOT_FOUND); }
    public ResponseEntity<JsonNode> asResponse500() { return asResponse(HttpStatus.INTERNAL_SERVER_ERROR); }

}
