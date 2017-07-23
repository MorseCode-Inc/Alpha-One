package inc.morsecode.api;

import inc.morsecode.json.JsonArray;
import inc.morsecode.json.JsonObject;
import inc.morsecode.json.JsonPrimitive;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by morsecode on 7/23/2017.
 */
public class JsonStacktrace extends JsonObject {

    private JsonArray trace;

    public JsonStacktrace(Throwable ex) {

        trace= new JsonArray( Arrays.stream(ex.getStackTrace())
                .map(ste -> new JsonPrimitive(ste.getClassName() +":"+ ste.getLineNumber() +" "+ ste.getMethodName()))
                .collect(Collectors.toList())
        );

        if (ex.getCause() != null) {
            JsonObject cause= new JsonStacktrace(ex.getCause());
            set("cause", cause);
        } else {
            setNull("cause");
        }

        set("trace", trace);
        set("message", ex.getMessage());
        set("type", ex.getClass().getCanonicalName());
    }

}
