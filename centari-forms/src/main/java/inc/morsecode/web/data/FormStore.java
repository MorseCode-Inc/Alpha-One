package inc.morsecode.web.data;

import inc.morsecode.web.form.FormElement;
import inc.morsecode.web.form.WebForm;

import java.io.IOException;

/**
 * Created by morsecode on 7/24/2017.
 */
public interface FormStore {

    // will have some persistent storage mechanism
    // able to read and write enough information to
    // construct a web form

    WebForm readForm(String name) throws IOException;

    FormElement readElement(String form, String name) throws IOException;

    boolean write(String form, FormElement e) throws IOException;

    boolean write(WebForm form) throws IOException;

    boolean delete(String form, FormElement e);

    boolean elementExists(String form, String name);

    boolean formExists(String name);
}
