package brisa.lockmanager.controllers.admin;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import brisa.lockmanager.models.Lock;
import brisa.lockmanager.repositories.ClientRepository;
import brisa.lockmanager.repositories.LockModelRepository;
import brisa.lockmanager.repositories.LockRepository;
import brisa.lockmanager.repositories.WarehouseRepository;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class LockController extends BaseAdminController<LockRepository> {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private WarehouseRepository warehouseRepository;
	@Autowired
	private LockModelRepository lockModelRepository;

	private static final String OBJECTS = "objects";
	private static final String LST_MODEL = "lstModel";
	private static final String LST_CLIENT = "lstClient";
	private static final String LST_WAREHOUSE = "lstWarehouse";

	@GetMapping(ADMIN_LOCK_LIST)
	public String index(final Model model) {

		final List<Lock> lstLock = this.repository.findAll();

		model.addAttribute(OBJECTS, lstLock);
		return ADMIN_LOCK_LIST;
	}

	@GetMapping(ADMIN_LOCK_EDIT)
	@ApiIgnore
	public String showAdd(final Model model) {

		final Lock object = new Lock();

		model.addAttribute(LST_MODEL, this.lockModelRepository.findAll());
		model.addAttribute(LST_CLIENT, this.clientRepository.findAll());
		model.addAttribute(LST_WAREHOUSE, this.warehouseRepository.findAll());
		model.addAttribute(OBJECT, object);
		return ADMIN_LOCK_EDIT;
	}

	@GetMapping(ADMIN_LOCK_EDIT + "/{id}")
	@ApiIgnore
	public String showEdit(final Model model, @PathVariable("id") final long id) {

		final Lock object = super.repository.findById(id).orElseThrow(() -> new IllegalArgumentException());
		
		model.addAttribute(LST_MODEL, this.lockModelRepository.findAll());
		model.addAttribute(LST_CLIENT, this.clientRepository.findAll());
		model.addAttribute(LST_WAREHOUSE, this.warehouseRepository.findAll());
		model.addAttribute(OBJECT, object);
		return ADMIN_LOCK_EDIT;
	}

	@PostMapping(ADMIN_LOCK_EDIT)
	@ApiIgnore
	@Transactional
	public String save(@Valid @ModelAttribute(OBJECT) final Lock object, final BindingResult result,
			final RedirectAttributes redirect, final Model model) {

		final boolean isEditing = object.getId() != null;
		final Timestamp now = DateUtil.getCurrentTimestamp();

		if (result.hasErrors()) {
			model.addAttribute(Alerts.error());
			model.addAttribute(OBJECT, object);
			return ADMIN_LOCK_EDIT;
		}

		if (isEditing) {
			final Lock currentObject = this.repository.findById(object.getId()).get();
			object.setRegistryDate(currentObject.getRegistryDate());
		}
		object.setUpdateDate(now);

		this.repository.save(object);
		redirect.addFlashAttribute(Alerts.success());
		return this.forward(ADMIN_LOCK_LIST);
	}

	@GetMapping(ADMIN_LOCK_DELETE)
	@ApiIgnore
	@Transactional
	public String delete(final RedirectAttributes redirect, final Model model, @RequestParam("id") final long id) {

		final Lock object = super.repository.findById(id).get();

		if (object != null) {
			super.repository.delete(object);
		}

		redirect.addFlashAttribute(Alerts.success());
		return this.forward(ADMIN_LOCK_LIST);
	}

}
