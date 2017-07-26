package inc.morsecode.web.model.form;

import inc.morsecode.json.*;
import inc.morsecode.spec.json.JsonStructure;

import java.util.function.Consumer;

/**
 * Created by morsecode on 7/23/2017.
 *
 * This class represents something on a web form.
 *
 * It could be an InputField, a Label, a HelpIcon or something similar
 */
public class FormElement {

    private HtmlTag model= new HtmlTag();

    public FormElement() {
    }


    public FormElement set(JsonStructure json) {
        setName(json.get("name"));
        json.get("attributes", new TypedJsonArray()).stream()
                .forEachOrdered(new Consumer() {
                    @Override
                    public void accept(Object o) {
                        JsonStructure struct= (JsonStructure)o;
                        struct.keys().forEach(key -> set(key, struct.get(key)));
                    }
                });

        return this;
    }

    public JsonStructure toJson() {
        return model.toJson();
    }

    public FormElement set(String name, String value) { model.set(name, value); return this; }

    public FormElement setName(String value) { model.setName(value); return this; }
    public FormElement setId(String value) { model.setId(value); return this; }
    public FormElement setCssClass(String value) { model.setCssClass(value); return this; }
    public FormElement setValue(String value) { model.setValue(value); return this; }

    public String getName() {
        return model.getName();
    }

    public FormElement setAttribute(String name, String value) {
        model.setAttribute(name, value);
        return this;
    }

    public FormElement setOrdinal(int ordinal) {
        model.set("ordinal", ordinal);
        return this;
    }

}
