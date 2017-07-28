package inc.morsecode.centari;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginLogout {

    @RequestMapping(method= RequestMethod.GET,
            path= "/welcome-login")
    public String welcome() {

        // this is the login form page
        return "welcome-login";
    }

    @RequestMapping(method= RequestMethod.POST,
            path="/welcome-login")
    public String login(HttpServletRequest httpReq) {

        // this is the landing page on successful login.
        return "welcome";
    }
}
