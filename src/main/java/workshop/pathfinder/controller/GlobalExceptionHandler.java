package workshop.pathfinder.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import workshop.pathfinder.domain.helpers.ErrorInfo;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String PASSWORD_MISS_MATCH = "[Password miss match]]";
    private final static String UNAVAILABLE_USERNAME = "[Username is not valid]]";
    private final static String LOGIN_ERROR_2 = "[Invalid user]]";
    private final static String LOGIN_ERROR_1 = "during isValid call";
    private final static String INPUT_SIZE_ERROR = " [size must be between 10 and 2147483647]";

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public @ResponseBody ErrorInfo handle500Error(HttpServletRequest req, Exception ex) {
//        return new ErrorInfo(req.getRequestURL().toString(), ex);
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle500Error(Model model, Exception ex) {
        String message = "";
        if (ex.getMessage().contains(PASSWORD_MISS_MATCH)) {
            message = "Passwords do not match try again!";
        } else if (ex.getMessage().contains(UNAVAILABLE_USERNAME)) {
            message = "Username is not valid please try another one!";
        } else if (ex.getMessage().contains(LOGIN_ERROR_1) || ex.getMessage().contains(LOGIN_ERROR_2)) {
            message = "Username or password is wrong please try again!";
        } else if (ex.getMessage().contains(INPUT_SIZE_ERROR)) {
            message = "Description size must be at least 10 characters!";
        } else {
            message = ex.getMessage();
        }
        model.addAttribute("message", message);
        return "error500";
    }
}
