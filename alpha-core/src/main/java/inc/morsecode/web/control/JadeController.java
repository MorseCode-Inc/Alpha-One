package inc.morsecode.web.control;

import inc.morsecode.centari.Discovery;
import inc.morsecode.centari.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by morsecode on 7/16/2017.
 */
@Controller
public class JadeController {

    private static final Logger LOG= LoggerFactory.getLogger(JadeController.class);
    static { LOG.info("Class loaded."); }

    @Autowired
    private Discovery bootstrap;

    @Autowired
    private Routes.RoutesYml routes;

    @RequestMapping(method=RequestMethod.GET, path={"","/"})
    public String index(Model model) {
        Map<String, String> r = routes.getRoutes();
        System.out.println(r.keySet());
        return "index";
    }

    @RequestMapping(method=RequestMethod.GET, path="/page")
    public String page(Model model) {
        return "page";
    }

    @RequestMapping(method=RequestMethod.GET, path="/error")
    public String error(HttpServletRequest request, Model model, Exception ex) {
        Map<String, Object> response= new HashMap<>();
        response.put("on_page", request.getRequestURL());
        response.put("exception", "NullPointerException: "+ ex.getMessage());
        StackTraceElement at= ex.getStackTrace()[0];
        response.put("error_message", at.getClassName() +"."+ at.getMethodName() +"() unhandled null reference ["+ at.getFileName() +":"+ at.getLineNumber() +"]");
        model.addAllAttributes(response);
        LOG.error(ex.getMessage(), ex);
        LOG.error(ex.getCause().getMessage(), ex.getCause());
        return "error";
    }

}
