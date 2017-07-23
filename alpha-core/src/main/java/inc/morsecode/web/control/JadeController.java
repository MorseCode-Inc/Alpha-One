package inc.morsecode.web.control;

import inc.morsecode.centari.InitDotD;
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

    @Autowired
    private InitDotD bootstrap;

    public JadeController() {
        LOG.info("Class loaded.");
    }

    @RequestMapping(path="/", method=RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(path="/page", method=RequestMethod.GET)
    public String page(Model model) {
        return "page";
    }

}
