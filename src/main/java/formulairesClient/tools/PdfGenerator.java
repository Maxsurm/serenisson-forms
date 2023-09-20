package formulairesClient.tools;

import formulairesClient.dto.ResponseDTO;
import formulairesClient.entities.Patient;
import formulairesClient.entities.Question.Formulaire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PdfGenerator {

    @Value("${storage.folder}")
    private String storageFolder;

    public String generatePdf(Formulaire formulaire, Patient patient, List<ResponseDTO> reponses) throws Exception {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("reponses", reponses);
        context.setVariable("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        context.setVariable("nom", patient.getNom().toUpperCase());
        context.setVariable("prenom", patient.getPrenom());
        context.setVariable("form", formulaire);

        String format = String.format("/%s-%s-%s.pdf", formulaire.name(), patient.getNom(), patient.getPrenom());
        OutputStream outputStream = new FileOutputStream(storageFolder + format);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(templateEngine.process("form-skull", context));
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();

        return format;
    }
}
