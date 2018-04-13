package inc.morsecode.zk;

import com.fasterxml.jackson.databind.ObjectMapper;
import inc.morsecode.json.JsonObject;
import inc.morsecode.json.JsonParser;
import inc.morsecode.json.ex.MalformedJsonException;
import inc.morsecode.spec.json.JsonStructure;

import java.io.IOException;

public class JacksonEncoder implements StorageCodec<Object> {

    @Override
    public byte[] write(Object value) throws IOException {
        ObjectMapper mapper= new ObjectMapper();

        return new JsonObject()
            .set("t", value.getClass().getCanonicalName())
            .set("v", mapper.writeValueAsString(value))
            .toString()
            .getBytes();
    }

    @Override
    public Object read(byte[] bytes) throws IOException {
        ObjectMapper mapper= new ObjectMapper();
        try {
            JsonStructure json= JsonParser.parse(new String(bytes));
            return mapper.readValue(json.get("t").getBytes(), Class.forName(json.get("v")));
        } catch (ClassNotFoundException cnf) {
            return mapper.readTree(bytes);
        } catch (MalformedJsonException mjx) {
            throw new IOException(mjx.getMessage(), mjx);
        }

    }

}
