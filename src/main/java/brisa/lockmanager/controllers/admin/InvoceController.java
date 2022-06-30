package brisa.lockmanager.controllers.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import brisa.lockmanager.Routes;
import brisa.lockmanager.models.Invoice;
import brisa.lockmanager.services.InvoiceService;

@RestController
public class InvoceController implements Routes{

    @PostMapping(ADMIN_INVOICE)
    public static ResponseEntity<InputStreamResource> POSTEndpoint(@Valid @RequestBody Invoice invoice) throws IOException {

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

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<InvoiceExceptionHandler> handleValidationExceptions(MethodArgumentNotValidException ex) {
//
//        InvoiceExceptionHandler response = new InvoiceExceptionHandler(ex, HttpStatus.BAD_REQUEST);
//
//        return ResponseEntity
//                .ok()
//                .body(response);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(FileNotFoundException.class)
//    public ResponseEntity<InvoiceExceptionHandler> handleExceptionsFileNotFound(FileNotFoundException ex) {
//
//        InvoiceExceptionHandler response = new InvoiceExceptionHandler(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return ResponseEntity
//                .ok()
//                .body(response);
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<InvoiceExceptionHandler> handleExceptionsIOException(IOException ex) {
//
//        InvoiceExceptionHandler response = new InvoiceExceptionHandler(ex, HttpStatus.NOT_ACCEPTABLE);
//
//        return ResponseEntity
//                .ok()
//                .body(response);
//    }

}
