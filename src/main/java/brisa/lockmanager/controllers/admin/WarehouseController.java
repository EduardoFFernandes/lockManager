package brisa.lockmanager.controllers.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import brisa.lockmanager.models.Warehouse;
import brisa.lockmanager.repositories.WarehouseRepository;

@Controller
public class WarehouseController extends BaseAdminController<WarehouseRepository> {

    private static final String OBJECTS = "objects";

    @GetMapping(ADMIN_WAREHOUSE_LIST)
    public String index(final Model model) {

        final List<Warehouse> lstWarehouse = this.repository.findAll();


        model.addAttribute(OBJECTS, lstWarehouse);
        return ADMIN_WAREHOUSE_LIST;
    }

}
