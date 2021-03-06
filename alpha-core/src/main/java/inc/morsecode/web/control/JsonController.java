package inc.morsecode.web.control;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import inc.morsecode.api.JsonPayload;
import inc.morsecode.json.JsonArray;
import org.eclipse.jetty.server.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by morsecode on 7/16/2017.
 */
@RestController
@RestControllerAdvice
@RequestMapping(path= "/api", headers = {"Content-Type=application/json"})
public class JsonController {

    private static final Logger LOG= LoggerFactory.getLogger(JsonController.class);

    //@Autowired
    //private InitDotD bootstrap;


    @RequestMapping(path="/", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> index(Model model, Session session) throws IOException {
        // set the appropriate model data and return

        return new JsonPayload().with(model.asMap()).asResponse(HttpStatus.OK);
    }

    @RequestMapping(path="/page", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> page(Model model, HttpServletRequest httpReq) {

        JsonPayload payload= new JsonPayload();

        // set whatever data on the payload we need to
        model.addAttribute("test", true);
        model.addAttribute("client", httpReq.getRemoteAddr());

        payload.with(model.asMap());

        return payload.asResponse(HttpStatus.OK);

    }

    @RequestMapping(path="/env", method=RequestMethod.GET)
    public ResponseEntity<JsonNode> env(Model model, Session session) throws IOException {
        ObjectMapper mapper= new ObjectMapper();

        JsonPayload payload= new JsonPayload();

        model.addAttribute("env", System.getProperties());

        // set whatever data on the payload we need to, turn it in to a response and send it.
        return payload.with(model.asMap()).asResponse(HttpStatus.OK);
    }


    @ExceptionHandler({Throwable.class})
    @RequestMapping(headers = {"Content-Type:application/json"})
    public ResponseEntity<JsonNode> handleAny(HttpServletRequest request, Throwable error) {
        JsonPayload payload= new JsonPayload();
        JsonArray stacktrace= new JsonArray();

        for (StackTraceElement ste : error.getStackTrace()) {
            stacktrace.add(ste.getClassName() +":"+ ste.getLineNumber() +" "+ ste.toString());
        }

        payload.set("error_message", error.getMessage());
        payload.set("error", error.getClass().getSimpleName());

        payload.set("stacktrace", stacktrace);

        return payload.asResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
