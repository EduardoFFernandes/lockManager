package brisa.lockmanager.controllers.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import brisa.lockmanager.models.LockModel;
import brisa.lockmanager.repositories.LockModelRepository;

@Controller
public class LockModelController extends BaseAdminController<LockModelRepository> {

    private static final String OBJECTS = "objects";

    @GetMapping(ADMIN_LOCK_MODEL_LIST)
    public String index(final Model model) {

        final List<LockModel> lstLockModel = this.repository.findAll();


        model.addAttribute(OBJECTS, lstLockModel);
        return ADMIN_LOCK_MODEL_LIST;
    }

}
