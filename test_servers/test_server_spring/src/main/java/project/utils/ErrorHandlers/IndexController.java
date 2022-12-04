package project.utils.ErrorHandlers;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import project.utils.classes.ResultType;
import project.utils.expections.MultiError;

@ControllerAdvice
@RestController
public class IndexController implements ErrorController{
    private static final String PATH = "/error";
    
    @ExceptionHandler(value = MultiError.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultType<String[]> multiexception(HttpServletResponse response, MultiError exception) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResultType<String[]>("error", "error", exception.getMessages());
    }
    
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResultType<String> exception(HttpServletResponse response, Exception exception) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResultType<String>("error", "error", exception.getMessage());
    }
    
    @RequestMapping(value = PATH)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResultType<String> error(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        return new ResultType<String>("error", "error", "We can't you're request in the api find in the api");
    }
    
    public String getErrorPath() {
        return PATH;
    }
}
