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
import brisa.lockmanager.models.Purchase;
import brisa.lockmanager.repositories.ClientRepository;
import brisa.lockmanager.repositories.LockRepository;
import brisa.lockmanager.repositories.PurchaseRepository;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class PurchaseController extends BaseAdminController<PurchaseRepository> {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LockRepository lockRepository;

    private static final String OBJECTS = "objects";
    private static final String LST_LOCK = "lstLock";
    private static final String LST_CLIENT = "lstClient";

    @GetMapping(ADMIN_PURCHASE_LIST)
    public String index(final Model model) {

        final List<Purchase> lstPurchase = this.repository.findAll();

        model.addAttribute(OBJECTS, lstPurchase);
        return ADMIN_PURCHASE_LIST;
    }

    @GetMapping(ADMIN_PURCHASE_EDIT)
    @ApiIgnore
    public String showAdd(final Model model) {

        final Purchase object = new Purchase();

        model.addAttribute(LST_LOCK, this.lockRepository.findAll());
        model.addAttribute(LST_CLIENT, this.clientRepository.findAll());
        model.addAttribute(OBJECT, object);
        return ADMIN_PURCHASE_EDIT;
    }

    @GetMapping(ADMIN_PURCHASE_EDIT + "/{id}")
    @ApiIgnore
    public String showEdit(final Model model, @PathVariable("id") final long id) {

        final Purchase object = super.repository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        model.addAttribute(LST_LOCK, this.lockRepository.findAll());
        model.addAttribute(LST_CLIENT, this.clientRepository.findAll());
        model.addAttribute(OBJECT, object);
        return ADMIN_PURCHASE_EDIT;
    }

    @PostMapping(ADMIN_PURCHASE_EDIT)
    @ApiIgnore
    @Transactional
    public String save(@Valid @ModelAttribute(OBJECT) final Purchase object, final BindingResult result,
            final RedirectAttributes redirect, final Model model) {

        final boolean isEditing = object.getId() != null;
        final Timestamp now = DateUtil.getCurrentTimestamp();

        if (result.hasErrors()) {
            model.addAttribute(Alerts.error());
            model.addAttribute(OBJECT, object);
            return ADMIN_PURCHASE_EDIT;
        }

        if (isEditing) {
            final Purchase currentObject = this.repository.findById(object.getId()).get();
            object.setRegistryDate(currentObject.getRegistryDate());
            object.setUpdateDate(now);
        } else {
            object.setRegistryDate(now);
        }

        this.repository.save(object);
        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_PURCHASE_LIST);
    }

    @GetMapping(ADMIN_PURCHASE_DELETE)
    @ApiIgnore
    @Transactional
    public String delete(final RedirectAttributes redirect, final Model model, @RequestParam("id") final long id) {

        final Purchase object = super.repository.findById(id).get();

        if (object != null) {
            super.repository.delete(object);
        }

        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_PURCHASE_LIST);
    }

}