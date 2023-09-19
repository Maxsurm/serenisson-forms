package formulairesClient.controllers;


import formulairesClient.dto.PatientDTO;
import formulairesClient.dto.ResponseDTO;
import formulairesClient.entities.Patient;
import formulairesClient.entities.Question.Formulaire;
import formulairesClient.repositories.PatientRepository;
import formulairesClient.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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

@RestController
@RequestMapping("/form")
public class FrontFormController {


    //Accès au repository des questions
    @Autowired
    private QuestionRepository q_repository;

    //Accès au repository de patient
    @Autowired
    private PatientRepository p_repository;

    @Value("c:/tools/fakestorage")
    private String storageFolder;

    @PostMapping("/{formulaire}/{token}")
    public void recupererReponses (@PathVariable Formulaire formulaire, @PathVariable String token, @RequestBody List<ResponseDTO> reponses) throws Exception{

        Patient patient = p_repository.findByMail(tokenTool.getUsernameFromToken(token));
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("reponses", reponses );
        context.setVariable("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        context.setVariable("nom", patient.getNom().toUpperCase());
        context.setVariable("prenom", patient.getPrenom());
        context.setVariable("form", formulaire);

        OutputStream outputStream = new FileOutputStream(storageFolder + "/anamnese.pdf");

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(templateEngine.process("form-skull", context));
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }


}
