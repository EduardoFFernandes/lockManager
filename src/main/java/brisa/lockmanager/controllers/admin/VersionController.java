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
import brisa.lockmanager.models.ChangeLocksObject;
import brisa.lockmanager.models.Lock;
import brisa.lockmanager.models.Version;
import brisa.lockmanager.repositories.LockRepository;
import brisa.lockmanager.repositories.VersionRepository;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class VersionController extends BaseAdminController<VersionRepository> {

    private static final String OBJECTS = "objects";
    private static final String LST_LOCK = "lstLock";

    @Autowired
    private LockRepository lockRepository;

    @GetMapping(ADMIN_VERSION_LIST)
    public String index(final Model model) {

        final List<Version> lstVersions = this.repository.findAll();

        model.addAttribute(OBJECTS, lstVersions);
        return ADMIN_VERSION_LIST;
    }

    @GetMapping(ADMIN_VERSION_EDIT)
    @ApiIgnore
    public String showAdd(final Model model) {

        final Version object = new Version();
        model.addAttribute(OBJECT, object);
        return ADMIN_VERSION_EDIT;
    }

    @GetMapping(ADMIN_VERSION_EDIT + "/{id}")
    @ApiIgnore
    public String showEdit(final Model model, @PathVariable("id") final long id) {

        final Version object = super.repository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        model.addAttribute(OBJECT, object);
        return ADMIN_VERSION_EDIT;
    }

    @PostMapping(ADMIN_VERSION_EDIT)
    @ApiIgnore
    @Transactional
    public String save(@Valid @ModelAttribute(OBJECT) final Version object, final BindingResult result,
            final RedirectAttributes redirect, final Model model) {

        final boolean isEditing = object.getId() != null;
        final Timestamp now = DateUtil.getCurrentTimestamp();

        if (result.hasErrors()) {
            model.addAttribute(Alerts.error());
            model.addAttribute(OBJECT, object);
            return ADMIN_VERSION_EDIT;
        }

        if (isEditing) {
            final Version currentObject = this.repository.findById(object.getId()).get();
            object.setRegistryDate(currentObject.getRegistryDate());
            object.setUpdateDate(now);
        } else {
            object.setRegistryDate(now);
        }

        this.repository.save(object);
        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_VERSION_LIST);
    }

    @GetMapping(ADMIN_VERSION_CHANGE_LOCKS + "/{id}")
    public String changeLocksVerion(final Model model, @PathVariable("id") final long id) {

        ChangeLocksObject object = new ChangeLocksObject();

        final Version version = super.repository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        final List<Lock> lstLock = this.lockRepository.findByVersionIdNotOrVersionIsNullOrderBySerialNumberAsc(id);

        object.setVersion(version);
        model.addAttribute(LST_LOCK, lstLock);
        model.addAttribute(OBJECT, object);
        return ADMIN_VERSION_CHANGE_LOCKS;
    }

    @PostMapping(ADMIN_VERSION_CHANGE_LOCKS)
    @ApiIgnore
    @Transactional
    public String saveLocks(@Valid @ModelAttribute(OBJECT) final ChangeLocksObject object, final BindingResult result,
            final RedirectAttributes redirect, final Model model) {

        List<Lock> lstLocks = object.getLstLock();

        lstLocks.forEach(x -> {
            x.setVersion(object.getVersion());
        });

        this.lockRepository.saveAll(lstLocks);

        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_VERSION_LIST);
    }

    @GetMapping(ADMIN_VERSION_DELETE)
    @ApiIgnore
    @Transactional
    public String delete(final RedirectAttributes redirect, final Model model, @RequestParam("id") final long id) {

        final Version object = super.repository.findById(id).get();

        if (object != null) {
            super.repository.delete(object);
        }

        redirect.addFlashAttribute(Alerts.success());
        return this.forward(ADMIN_VERSION_LIST);
    }

    @GetMapping(path = {
            API_RELEASE_NOTES + "/{idVersion}"

    })
    @ResponseBody
    public ResponseEntity<?> getReleaseNotes(@PathVariable(name = "idVersion") final Long versionId) {
        Version object = repository.findById(versionId).orElseThrow(() -> new IllegalArgumentException());
        return ResponseEntity.ok().body(object);
    }

    @GetMapping(path = {
            API_EXISTS_WAREHOUSE_ASSOCIATIONS + "/{idVersion}"

    })
    @ResponseBody
    public ResponseEntity<?> existsAssociation(@PathVariable(name = "idVersion") final Long versionId) {
        boolean hasAssociations = lockRepository.existsByVersionId(versionId);
        return ResponseEntity.ok().body(hasAssociations);
    }

}
