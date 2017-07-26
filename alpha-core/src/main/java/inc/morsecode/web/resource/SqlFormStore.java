package inc.morsecode.web.resource;

import inc.morsecode.centari.FormStore;
import inc.morsecode.web.model.form.FormElement;
import inc.morsecode.web.model.form.WebForm;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by morsecode on 7/24/2017.
 */
@Component
public class SqlFormStore implements FormStore {


    /**
     * Fetch a WebForm from the database
     * @param name
     * @return
     */
    @Override
    public WebForm readForm(String name) {
        WebForm form= new WebForm();


        return form;
    }

    @Override
    public FormElement readElement(String form, String name) {
        return null;
    }

    @Override
    public boolean write(WebForm form) throws IOException {
        return false;
    }

    @Override
    public boolean write(String form, FormElement e) {
        return false;
    }

    @Override
    public boolean delete(String form, FormElement e) {
        return false;
    }

    @Override
    public boolean elementExists(String form, String name) {
        return false;
    }

    @Override
    public boolean formExists(String name) {
        return false;
    }
}
