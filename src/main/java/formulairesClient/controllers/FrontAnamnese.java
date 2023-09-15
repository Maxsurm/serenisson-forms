package formulairesClient.controllers;


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
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
    public void recupererReponses () throws Exception{
        Map<Question, String>
        reponses = Map.of(
//                new Question(1,"quel age as tu ?", QuestionType.TEXT ,Formulaire.ANAMNESE),
//                "osef"
        );
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("reponses", reponses );

        OutputStream outputStream = new FileOutputStream(storageFolder + "/test.pdf");

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(templateEngine.process("anamnese-skull", context));
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }


}
