package formulairesClient.controllers;


import formulairesClient.dto.ResponseDTO;
import formulairesClient.entities.Question;
import formulairesClient.entities.Question.Formulaire;
import formulairesClient.entities.Question.QuestionType;
import formulairesClient.repositories.PatientRepository;
import formulairesClient.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/anamnese")
public class FrontAnamnese {


    //Accès au repository des questions
    @Autowired
    private QuestionRepository q_repository;

    //Accès au repository de patient
    @Autowired
    private PatientRepository p_repository;

    @Value("c:/tools/fakestorage")
    private String storageFolder;

    @PostMapping
    public void recupererReponses (@RequestBody List<ResponseDTO> reponses) throws Exception{

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("reponses", reponses );
        context.setVariable("date", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        OutputStream outputStream = new FileOutputStream(storageFolder + "/anamnese.pdf");

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(templateEngine.process("anamnese-skull", context));
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }


}
