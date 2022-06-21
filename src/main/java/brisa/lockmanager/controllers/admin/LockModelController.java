package brisa.lockmanager.controllers.admin;

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
import brisa.lockmanager.models.LockModel;
import brisa.lockmanager.repositories.LockModelRepository;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class LockModelController extends BaseAdminController<LockModelRepository> {

	private static final String OBJECTS = "objects";

	@GetMapping(ADMIN_LOCK_MODEL_LIST)
	public String index(final Model model) {

		final List<LockModel> lstLockModel = this.repository.findAll();

		model.addAttribute(OBJECTS, lstLockModel);
		return ADMIN_LOCK_MODEL_LIST;
	}

	@GetMapping(ADMIN_LOCK_MODEL_EDIT)
	@ApiIgnore
	public String showAdd(final Model model) {

		final LockModel object = new LockModel();
		model.addAttribute(OBJECT, object);
		return ADMIN_LOCK_MODEL_EDIT;
	}

	@GetMapping(ADMIN_LOCK_MODEL_EDIT + "/{id}")
	@ApiIgnore
	public String showEdit(final Model model, @PathVariable("id") final long id) {

		final LockModel object = super.repository.findById(id).orElseThrow(() -> new IllegalArgumentException());

		model.addAttribute(OBJECT, object);
		return ADMIN_LOCK_MODEL_EDIT;
	}

	@PostMapping(ADMIN_LOCK_MODEL_EDIT)
	@ApiIgnore
	@Transactional
	public String save(@Valid @ModelAttribute(OBJECT) final LockModel object, final BindingResult result,
			final RedirectAttributes redirect, final Model model) {

		if (result.hasErrors()) {
			model.addAttribute(Alerts.error());
			model.addAttribute(OBJECT, object);
			return ADMIN_LOCK_MODEL_EDIT;
		}

		this.repository.save(object);
		redirect.addFlashAttribute(Alerts.success());
		return this.forward(ADMIN_LOCK_MODEL_LIST);
	}

	@GetMapping(ADMIN_LOCK_MODEL_DELETE)
	@ApiIgnore
	@Transactional
	public String delete(final RedirectAttributes redirect, final Model model, @RequestParam("id") final long id) {

		final LockModel object = super.repository.findById(id).get();

		if (object != null) {
			super.repository.delete(object);
		}

		redirect.addFlashAttribute(Alerts.success());
		return this.forward(ADMIN_LOCK_MODEL_LIST);
	}
}
