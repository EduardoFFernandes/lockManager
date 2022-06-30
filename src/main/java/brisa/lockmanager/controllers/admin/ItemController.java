package brisa.lockmanager.controllers.admin;

import java.sql.Timestamp;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import brisa.lockmanager.commons.constants.Alerts;
import brisa.lockmanager.commons.utils.DateUtil;
import brisa.lockmanager.models.Item;
import brisa.lockmanager.repositories.ItemRepository;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class ItemController extends BaseAdminController<ItemRepository> {

    @PostMapping(ADMIN_ITEM_EDIT)
    @ApiIgnore
    @Transactional
    public String save(@Valid @ModelAttribute(OBJECT) final Item object, final BindingResult result,
            final RedirectAttributes redirect, final Model model) {

        final Timestamp now = DateUtil.getCurrentTimestamp();

        final boolean isEditing = object.getId() != null;
        if (result.hasErrors()) {
            model.addAttribute(Alerts.error());
            model.addAttribute(OBJECT, object);
            return ADMIN_LOCK_LIST;
        }

        if (isEditing) {
            final Item currentObject = this.repository.findById(object.getId()).get();
            object.setRegistryDate(currentObject.getRegistryDate());
            object.setPurchase(currentObject.getPurchase());
            object.setUpdateDate(now);
        } else {
            object.setRegistryDate(now);
        }
        this.repository.save(object);
        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_LOCK_LIST);
    }

    @GetMapping(ADMIN_ITEM_DELETE)
    @ApiIgnore
    @Transactional
    public String delete(final RedirectAttributes redirect, final Model model, @RequestParam("id") final long id) {

        final Item object = super.repository.findById(id).get();

        if (object != null) {
            super.repository.delete(object);
        }

        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_LOCK_LIST);
    }
}
