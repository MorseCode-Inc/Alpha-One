package inc.morsecode.web.control;

import inc.morsecode.web.model.form.FormElement;
import inc.morsecode.web.model.form.WebForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * The controller for managing field definitions
 * Created by morsecode on 7/16/2017.
 */
@Controller
@RequestMapping(path = "/admin/forms")
public class Forms {

    @RequestMapping(path = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(path = "/")
    public String index(Model model, HttpServletRequest httpReq) {

        WebForm login= new WebForm()
                .setName("login")
                .setAttribute("secret", "random-stuff-here");

        login.add(new FormElement().setName("user")
                     .setAttribute("title", "Username"))
                .add(new FormElement().setName("password")
                     .setAttribute("title", "Password")
                     .setAttribute("password", "true"))
                .add(new FormElement().setName("email"))
                .add(new FormElement().setName("firstname"))
                .add(new FormElement().setName("lastname"));

        model.addAttribute("loginform", login.toMap());

        return "admin/forms/index.jade";
    }


}
