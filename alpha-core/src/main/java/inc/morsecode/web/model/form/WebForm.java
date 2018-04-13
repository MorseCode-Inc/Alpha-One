package inc.morsecode.web.model.form;

import inc.morsecode.json.JsonObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by morsecode on 7/24/2017.
 */
public class WebForm {

    private HtmlTag model= new HtmlTag();

    private Map<String, FormElement> elements= new HashMap<>();


   public WebForm setAttribute(String name, String value) {
       model.setAttribute(name, value);
       return this;
   }

   public WebForm setName(String value) {
       model.setName(value);
       return this;
   }

    public JsonObject toJson() {
        return model.toJson();
    }

    public Map<String, Object> toMap() { return model.asMap(); }

    public WebForm add(FormElement element) {
       // set the order
        element.setOrdinal(elements.size());

        elements.put(element.getName(), element);
        return this;
    }

    public Collection<FormElement> getElements() {
        return elements.values();
    }
}

