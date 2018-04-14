package inc.morsecode.web.model.form;

import inc.morsecode.json.JsonArray;
import inc.morsecode.json.JsonMember;
import inc.morsecode.json.JsonObject;
import inc.morsecode.json.ValueFactory;
import inc.morsecode.spec.json.JsonElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by morsecode on 7/24/2017.
 */
public class HtmlTag {

    final private JsonObject json= new JsonObject();
    private Map<String, JsonMember> attributes= new HashMap<>();

    public HtmlTag setName(String name) { json.set("name", name); return this; }

    public HtmlTag setId(String id) { json.set("id", id); return this; }

    public HtmlTag setValue(String value) { json.set("value", value); return this; }

    public HtmlTag setCssClass(String value) { json.set("css_class", value); return this; }

    public String getName() { return json.get("name"); }
    public String getId() { return json.get("id"); }
    public String getValue() { return json.get("value"); }
    public String getCssClass() { return json.get("css_class"); }

    public HtmlTag set(String name, String value) { json.set(name, value); return this; }
    public HtmlTag set(String name, int value) { json.set(name, value); return this; }

    public JsonObject toJson() {
        Function<JsonMember, JsonElement> mapper= new Function<JsonMember, JsonElement>() {
            @Override
            public JsonElement apply(JsonMember member) {
                return new JsonObject().set(member);
            }
        };

        json.set("attributbes",
                new JsonArray(
                        attributes.values()
                                .stream().map(mapper)
                                .collect(Collectors.toList())
        ));

        return json;
    }

    public Map<String, Object> asMap() {
        return toJson().asMap();
    }

    public HtmlTag setAttribute(String name, String value) {
        attributes.put(name, new JsonMember(name, ValueFactory.create(value)));
        return this;
    }

    public String getAttribute(String name) {
        return (String)attributes.get(name).getValue();
    }

}
