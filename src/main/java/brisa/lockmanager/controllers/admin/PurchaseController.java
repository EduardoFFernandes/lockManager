package brisa.lockmanager.controllers.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
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
import brisa.lockmanager.models.Invoice;
import brisa.lockmanager.models.Item;
import brisa.lockmanager.models.Purchase;
import brisa.lockmanager.repositories.ClientRepository;
import brisa.lockmanager.repositories.ItemRepository;
import brisa.lockmanager.repositories.LockRepository;
import brisa.lockmanager.repositories.PurchaseRepository;
import brisa.lockmanager.services.InvoiceService;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class PurchaseController extends BaseAdminController<PurchaseRepository> {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private LockRepository lockRepository;
    @Autowired
    private ItemRepository itemRepository;

    private static final String OBJECTS = "objects";
    private static final String LST_LOCK = "lstLock";
    private static final String LST_CLIENT = "lstClient";
    private static final String LST_ITEM = "lstItem";

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
        model.addAttribute(LST_ITEM, this.itemRepository.findAllByPurchaseIsNull());
        model.addAttribute(OBJECT, object);
        return ADMIN_PURCHASE_EDIT;
    }

    @GetMapping(ADMIN_PURCHASE_EDIT + "/{id}")
    @ApiIgnore
    public String showEdit(final Model model, @PathVariable("id") final long id) {

        final Purchase object = super.repository.findById(id).orElseThrow(() -> new IllegalArgumentException());

        final List<Item> lstItem = new ArrayList<Item>();
        lstItem.addAll(this.itemRepository.findAllByPurchaseIsNull());
        lstItem.addAll(object.getLstPurchaseItem());

        model.addAttribute(LST_LOCK, this.lockRepository.findAll());
        model.addAttribute(LST_CLIENT, this.clientRepository.findAll());
        model.addAttribute(LST_ITEM, lstItem);
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
            model.addAttribute(LST_LOCK, this.lockRepository.findAll());
            model.addAttribute(LST_CLIENT, this.clientRepository.findAll());
            model.addAttribute(LST_ITEM, this.itemRepository.findAllByPurchaseIsNull());
            model.addAttribute(OBJECT, object);
            return ADMIN_PURCHASE_EDIT;
        }

        if (isEditing) {
            final Purchase currentObject = this.repository.findById(object.getId()).get();
            object.setRegistryDate(currentObject.getRegistryDate());
            object.setUpdateDate(now);

            if (!currentObject.getLstPurchaseItem().isEmpty()) {
                currentObject.getLstPurchaseItem().forEach(x -> {
                    x.setPurchase(null);
                    this.itemRepository.save(x);
                });
            }

            if (!object.getLstPurchaseItem().isEmpty()) {
                object.getLstPurchaseItem().forEach(x -> {
                    x.setPurchase(object);
                    this.itemRepository.save(x);
                });
            }
        } else {
            object.setRegistryDate(now);

            if (!object.getLstPurchaseItem().isEmpty()) {
                object.getLstPurchaseItem().forEach(x -> {
                    x.setPurchase(object);
                    this.itemRepository.save(x);
                });
            }
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

    @GetMapping(path = {
            ADMIN_PURCHASE_ITEMS + "/{id}"
    })
    @ResponseBody
    public ResponseEntity<?> findItems(@PathVariable(value = "id") final Long id) {

        final List<Item> lstItem = this.itemRepository.findAllByPurchaseId(id);
        return ResponseEntity.ok().body(lstItem);
    }

//    @GetMapping(ADMIN_INVOICE + "/{id}")
//    @ApiIgnore
//    @Transactional
//    public String getInvoice(@PathVariable(value = "id") final Long id, final RedirectAttributes redirect, HttpServletResponse resp) throws FileNotFoundException, IOException {
//
//        final Purchase object = this.repository.findById(id).get();
//
//        Invoice invoice = InvoiceService.populateInvoice(object);
//        
//        resp.setHeader("Content-disposition", "attachment; filename=invoice.pdf");
//
//        File pdfFile = new File(PATH_INVOICE_PDF);
//        
//        
//        InvoiceService.generatePDF(invoice);
//
//        try(OutputStream out = resp.getOutputStream()) {
//            
//            InputStream inputStream = new FileInputStream(pdfFile);
//            IOUtils.copy(inputStream, out);
//            resp.flushBuffer();
//            
//            InvoiceService.deleteCopiedFiles();
//            
//            redirect.addFlashAttribute(Alerts.success());
//            return this.forward(ADMIN_PURCHASE_LIST);
//        } catch (Exception e) {
////            ResponseEntity<?> response = ResponseEntity.badRequest().body(e.getMessage());
//            return ADMIN_PURCHASE_LIST;
//        }
//        
//    }
    
    @GetMapping(ADMIN_INVOICE + "/{id}")
    @ApiIgnore
    @Transactional
    public ResponseEntity<InputStreamResource> getInvoice(@PathVariable(value = "id") final Long id, HttpServletResponse resp) throws IOException {
        
        final Purchase object = this.repository.findById(id).get();

        Invoice invoice = InvoiceService.populateInvoice(object);
        
        /* inline -> open pdf on web    //   attachment -> downloads pdf */
        resp.setHeader("Content-disposition", "inline; filename=invoice.pdf");

        File pdfFile = new File(PATH_INVOICE_PDF);

        InvoiceService.generatePDF(invoice);

        ResponseEntity<InputStreamResource> response = ResponseEntity
                .ok()
                .contentLength(pdfFile.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(new InputStreamResource(new FileInputStream(pdfFile)));

        InvoiceService.deleteCopiedFiles();

        return response;
    }

}
