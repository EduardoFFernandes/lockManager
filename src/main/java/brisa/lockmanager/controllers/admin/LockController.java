package brisa.lockmanager.controllers.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import brisa.lockmanager.models.Lock;
import brisa.lockmanager.repositories.LockRepository;

@Controller
public class LockController extends BaseAdminController<LockRepository> {

    private static final String OBJECTS = "objects";

    @GetMapping(ADMIN_LOCK_LIST)
    public String index(final Model model) {

        final List<Lock> lstLock = this.repository.findAll();


        model.addAttribute(OBJECTS, lstLock);
        return ADMIN_LOCK_LIST;
    }

}
