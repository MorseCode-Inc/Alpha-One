package inc.morsecode.zk;

import com.fasterxml.jackson.databind.ObjectMapper;
import inc.morsecode.json.JsonObject;
import inc.morsecode.json.JsonParser;
import inc.morsecode.json.ex.MalformedJsonException;
import inc.morsecode.spec.json.JsonStructure;

import java.io.IOException;

public class ObjectEncoder implements StorageCodec<Object> {

    @Override
    public byte[] write(Object value) throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        // return mapper.readerFor(Class.forName(json.get("class"))).readValue(json.get("object").toString());

        return new JsonObject()
            .set("class", value.getClass().getCanonicalName())
            .set("object", mapper.writeValueAsString(value))
            .toString()
            .getBytes();

        // return mapper.writeValueAsBytes(value);
    }

    @Override
    public Object read(byte[] bytes) throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        try {
            JsonStructure json= JsonParser.parse(new String(bytes));
            return mapper.readValue(json.get("object").getBytes(), Class.forName(json.get("class")));
        } catch (ClassNotFoundException cnf) {
            return mapper.readTree(bytes);
        } catch (MalformedJsonException mjx) {
            throw new IOException(mjx.getMessage(), mjx);
        }

    }

}
