package brisa.lockmanager.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import brisa.lockmanager.Routes;
import brisa.lockmanager.models.Invoice;

public class InvoiceService implements Routes{

    public static void generatePDF(Invoice invoice) throws FileNotFoundException, IOException {

        String result = parseThymeleafTemplate(invoice);
        generateCss();

        try (
             OutputStream os = new FileOutputStream(PATH_INVOICE_PDF);) {

            File file = new File(PATH_INVOICE_HTML);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            bw.write(result);
            bw.close();

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withUri(FILE + PATH_INVOICE_HTML);
            builder.toStream(os);
            builder.run();

        }

    }

    public static String parseThymeleafTemplate(Invoice invoice) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("invoice", invoice);

        return templateEngine.process(PATH_TEMPLATE, context);
    }

    public static void generateCss() throws FileNotFoundException, IOException {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".css");
        templateResolver.setTemplateMode(TemplateMode.CSS);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        String result = templateEngine.process(PATH_TEMPLATE, new Context());

        try (
             OutputStream os = new FileOutputStream(PATH_INVOICE_CSS);) {

            File file = new File(PATH_INVOICE_CSS);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            bw.write(result);
            bw.close();

        }
    }

    public static void deleteCopiedFiles() throws IOException {

        Files.deleteIfExists(Paths.get(PATH_INVOICE_CSS));
        Files.deleteIfExists(Paths.get(PATH_INVOICE_HTML));
        Files.deleteIfExists(Paths.get(PATH_INVOICE_PDF));

    }
}
