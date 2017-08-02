package inc.morsecode.web.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginLogout {

    @Autowired
    private SessionRepository<ExpiringSession> sessions;

    private static final Logger LOG= LoggerFactory.getLogger(LoginLogout.class);

    @RequestMapping(method= RequestMethod.GET,
            path= "/welcome-login")
    public String welcome(Model model, HttpServletRequest httpReq, HttpServletResponse httpResp, HttpSession session, CsrfToken token) {
        // this is the login form page
        model.addAttribute("sessionid", session.getId());

        model.addAttribute("_csrf.token", token.getToken())
            .addAttribute("_csrf.headerName", token.getHeaderName())
            .addAttribute("_csrf.parameterName", token.getParameterName());

        LOG.info("set _csrf: "+ token);
        LOG.info("set _csrf: "+ token.getToken());
        LOG.info("set _csrf: "+ token.getHeaderName());


        ExpiringSession s= sessions.getSession(session.getId());
        if (s == null) {
            s= sessions.createSession();
            s.setLastAccessedTime(System.currentTimeMillis());
            s.setMaxInactiveIntervalInSeconds(500);
        }
        sessions.save(s);

        httpResp.setHeader("JSESSIONID", s.getId());
        httpResp.setHeader("jsessionid", s.getId());

        return "welcome-login";
    }

    /*
    @RequestMapping(method= RequestMethod.POST,
            path="/welcome-login")
    public String login(HttpServletRequest httpReq) {
        // this is the landing page on successful login.
        return "welcome";
    }
    */

    @RequestMapping(path="/goodbye")
    public String goodbye(HttpServletRequest httpReq, HttpSession session) {
        // destroy any tokens and session information indicating the use is logged in
        return "redirect:/";
    }
}
