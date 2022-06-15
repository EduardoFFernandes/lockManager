package brisa.lockmanager.controllers.guest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import brisa.lockmanager.Routes;
import brisa.lockmanager.commons.constants.ControllerConstant;
import springfox.documentation.annotations.ApiIgnore;


@Controller
public class ErrorController implements Routes, ControllerConstant {

    @GetMapping({
            ERROR,
            SERVER_ERROR
    })
    @ApiIgnore
    public String serverError() {
        return "/error/500";
    }

    @GetMapping({
            ACCESS_DENIED
    })
    @ApiIgnore
    public String accessDenied() {
        return "/error/403";
    }

    @GetMapping({
            NOT_FOUND
    })
    @ApiIgnore
    public String notFound() {
        return "/error/404";
    }
}
