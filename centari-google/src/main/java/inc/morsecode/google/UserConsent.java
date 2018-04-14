package inc.morsecode.google;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Provides the controller actions and exposes endpoints for capturing and managing authorized consent
 * Author: morsecode 2018-04-14
 */
public class UserConsent {

    @RequestMapping(path="/g/required_consent")
    public String required_consent(HttpServletRequest request) {

        return "g/required_consent";
    }

    @RequestMapping(path="/g/auth")
    public String auth(HttpServletRequest request) {

        return "g/auth";
    }

    @RequestMapping(path="/g/auth_fail")
    public String auth_fail(HttpServletRequest request) {

        return "g/auth_fail";
    }

    @RequestMapping(path="/g/auth_ok")
    public String auth_ok(HttpServletRequest request) {

        return "g/auth_ok";
    }
}
