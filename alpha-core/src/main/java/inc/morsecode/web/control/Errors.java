package inc.morsecode.web.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by morsecode on 7/18/2017.
 */
@RestControllerAdvice
public class Errors {

    private final Logger LOG= LoggerFactory.getLogger(Errors.class);

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleBadRequest(HttpServletRequest request) {
        LOG.error("Got a Bad Request. ["+ request +"]");
        Map<String, Object> response= new HashMap<>();
        return response;
    }

    /*
    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleNullPointer(HttpServletRequest request, NullPointerException npe) {
        LOG.error("Null Pointer. ["+ request.getRequestURI() +"]", npe);
        LOG.error("caused by ",npe.getCause());
        Map<String, Object> response= new HashMap<>();
        response.put("on_page", request.getRequestURL());
        response.put("exception", "NullPointerException: "+ npe.getMessage());
        StackTraceElement at= npe.getStackTrace()[0];
        response.put("error_message", at.getClassName() +"."+ at.getMethodName() +"() unhandled null reference ["+ at.getFileName() +":"+ at.getLineNumber() +"]");
        return response;
    }
    */

}
