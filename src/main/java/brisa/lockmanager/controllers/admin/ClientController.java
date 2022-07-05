package brisa.lockmanager.controllers.admin;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import brisa.lockmanager.commons.constants.Alerts;
import brisa.lockmanager.commons.utils.DateUtil;
import brisa.lockmanager.models.Client;
import brisa.lockmanager.repositories.ClientRepository;
import brisa.lockmanager.repositories.PurchaseRepository;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class ClientController extends BaseAdminController<ClientRepository> {

    private static final String OBJECTS = "objects";
    private static final String SIGN_PLUS = "+";
    private static final String I18N_CELLPHONE_FORMAT = "+%s%s";
    private static final String EMPTY_STRING = "";

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping(ADMIN_CLIENT_LIST)
    public String index(final Model model) {

        final List<Client> lstClient = this.repository.findAll();

        model.addAttribute(OBJECTS, lstClient);
        return ADMIN_CLIENT_LIST;
    }

    @GetMapping(ADMIN_CLIENT_EDIT)
    @ApiIgnore
    public String showAdd(final Model model) {

        final Client object = new Client();
        model.addAttribute(OBJECT, object);
        return ADMIN_CLIENT_EDIT;
    }

    @GetMapping(ADMIN_CLIENT_EDIT + "/{id}")
    @ApiIgnore
    public String showEdit(final Model model, @PathVariable("id") final long id) {

        final Client object = super.repository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        model.addAttribute(OBJECT, object);
        return ADMIN_CLIENT_EDIT;
    }

    @PostMapping(ADMIN_CLIENT_EDIT)
    @ApiIgnore
    @Transactional
    public String save(@Valid @ModelAttribute(OBJECT) final Client object, final BindingResult result,
            final RedirectAttributes redirect, final Model model) {
        final boolean isEditing = object.getId() != null;
        final Timestamp now = DateUtil.getCurrentTimestamp();

        if (result.hasErrors()) {
            model.addAttribute(Alerts.error());
            model.addAttribute(OBJECT, object);
            return ADMIN_CLIENT_EDIT;
        }

        if (isEditing) {
            final Client currentObject = this.repository.findById(object.getId()).get();
            object.setRegistryDate(currentObject.getRegistryDate());
            object.setUpdateDate(now);
        } else {
            object.setRegistryDate(now);
        }

        if (!object.getCellphone().isEmpty()) {
            if (!object.getCellphone().contains(SIGN_PLUS)) {
                object.setCellphone(String.format(
                        I18N_CELLPHONE_FORMAT,
                        object.getDialCode().replace(SIGN_PLUS, EMPTY_STRING),
                        object.getCellphone()));
            }
        }

        this.repository.save(object);
        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_CLIENT_LIST);
    }

    @GetMapping(ADMIN_CLIENT_DELETE)
    @ApiIgnore
    @Transactional
    public String delete(final RedirectAttributes redirect, final Model model, @RequestParam("id") final long id) {

        final Client object = super.repository.findById(id).get();

        if (object != null) {
            super.repository.delete(object);
        }

        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_CLIENT_LIST);
    }

    @GetMapping(path = {
            API_EXISTS_CLIENT_ASSOCIATIONS + "/{idClient}"

    })
    @ResponseBody
    public ResponseEntity<?> existsAssociation(@PathVariable(name = "idClient") final Long clientId) {
        boolean hasAssociations = purchaseRepository.existsByClientId(clientId);
        return ResponseEntity.ok().body(hasAssociations);
    }

}
