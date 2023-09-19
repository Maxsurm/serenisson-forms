package formulairesClient.controllers;


import formulairesClient.dto.ResponseDTO;
import formulairesClient.repositories.PatientRepository;
import formulairesClient.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sixmois")
public class FrontSixMois {


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
        context.setVariable("date", new Date());

        OutputStream outputStream = new FileOutputStream(storageFolder + "/six-mois.pdf");

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(templateEngine.process("six-mois-skull", context));
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }


}
