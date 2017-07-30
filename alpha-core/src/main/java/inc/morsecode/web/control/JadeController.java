package inc.morsecode.web.control;

import inc.morsecode.centari.Discovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by morsecode on 7/16/2017.
 */
@Controller
@RequestMapping("/")
public class JadeController {

    private static final Logger LOG= LoggerFactory.getLogger(JadeController.class);
    static { LOG.info("Class loaded."); }

    @Autowired
    private Discovery bootstrap;

    @RequestMapping(method=RequestMethod.GET,
            path="/")
    public String index(Model model) {
        return "index.jade";
    }

    @RequestMapping(method=RequestMethod.GET,
            path="/page")
    public String page(Model model) {
        return "page";
    }

}
