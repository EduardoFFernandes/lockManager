package brisa.lockmanager.controllers.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import brisa.lockmanager.models.Client;
import brisa.lockmanager.repositories.ClientRepository;

@Controller
public class ClientController extends BaseAdminController<ClientRepository> {

    private static final String OBJECTS = "objects";

    @GetMapping(ADMIN_CLIENT_LIST)
    public String index(final Model model) {

        final List<Client> lstClient = this.repository.findAll();


        model.addAttribute(OBJECTS, lstClient);
        return ADMIN_CLIENT_LIST;
    }

}
