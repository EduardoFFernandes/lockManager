package brisa.lockmanager.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import brisa.lockmanager.Routes;

@Controller
public class WebController implements Routes{

    private static final String ADMIN_HOME = "/admin/home";

    @GetMapping(ADMIN_HOME)
    public String index() {
        return ADMIN_LOCK_LIST;
    }
}