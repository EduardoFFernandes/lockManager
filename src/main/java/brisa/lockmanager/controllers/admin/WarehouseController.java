package brisa.lockmanager.controllers.admin;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import brisa.lockmanager.commons.constants.Alerts;
import brisa.lockmanager.commons.utils.DateUtil;
import brisa.lockmanager.models.Warehouse;
import brisa.lockmanager.repositories.WarehouseRepository;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class WarehouseController extends BaseAdminController<WarehouseRepository> {

    private static final String OBJECTS = "objects";

    @GetMapping(ADMIN_WAREHOUSE_LIST)
    public String index(final Model model) {

        final List<Warehouse> lstWarehouse = this.repository.findAll();


        model.addAttribute(OBJECTS, lstWarehouse);
        return ADMIN_WAREHOUSE_LIST;
    }
    
	@GetMapping(ADMIN_WAREHOUSE_EDIT)
	@ApiIgnore
	public String showAdd(final Model model) {

		final Warehouse object = new Warehouse();
		model.addAttribute(OBJECT, object);
		return ADMIN_WAREHOUSE_EDIT;
	}

	@GetMapping(ADMIN_WAREHOUSE_EDIT + "/{id}")
	@ApiIgnore
	public String showEdit(final Model model, @PathVariable("id") final long id) {

		final Warehouse object = super.repository.findById(id).orElseThrow(() -> new IllegalArgumentException());

		model.addAttribute(OBJECT, object);
		return ADMIN_WAREHOUSE_EDIT;
	}

	@PostMapping(ADMIN_WAREHOUSE_EDIT)
	@ApiIgnore
	@Transactional
	public String save(@Valid @ModelAttribute(OBJECT) final Warehouse object, final BindingResult result,
			final RedirectAttributes redirect, final Model model) {

		final boolean isEditing = object.getId() != null;
		final Timestamp now = DateUtil.getCurrentTimestamp();

		if (result.hasErrors()) {
			model.addAttribute(Alerts.error());
			model.addAttribute(OBJECT, object);
			return ADMIN_WAREHOUSE_EDIT;
		}

		if (isEditing) {
			final Warehouse currentObject = this.repository.findById(object.getId()).get();
			object.setRegistryDate(currentObject.getRegistryDate());
			object.setUpdateDate(now);
		} else {
		    object.setRegistryDate(now);
		}

		this.repository.save(object);
		redirect.addFlashAttribute(Alerts.success());
		return this.forward(ADMIN_WAREHOUSE_LIST);
	}

	@GetMapping(ADMIN_WAREHOUSE_DELETE)
	@ApiIgnore
	@Transactional
	public String delete(final RedirectAttributes redirect, final Model model, @RequestParam("id") final long id) {

		final Warehouse object = super.repository.findById(id).get();

		if (object != null) {
			super.repository.delete(object);
		}

		redirect.addFlashAttribute(Alerts.success());
		return this.forward(ADMIN_WAREHOUSE_LIST);
	}

}
