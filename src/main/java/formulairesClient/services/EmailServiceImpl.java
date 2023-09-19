package formulairesClient.services;

import formulairesClient.dto.PatientDTO;
import formulairesClient.entities.Question.Formulaire;
import formulairesClient.tools.JwtTokenTool;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements IEmailService{

    private final JwtTokenTool tokenTool;
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    public EmailServiceImpl(JwtTokenTool tokenTool, JavaMailSender javaMailSender) {
        this.tokenTool = tokenTool;
        this.javaMailSender = javaMailSender;
    }
    //Envoi de mail templated


    @Override
    public void sendMail(Formulaire formulaire, PatientDTO byId) throws MessagingException {
        String template = switch (formulaire) {
            case SIXMOIS -> "sixmail";
            default -> "anamail";
        };
        String titre = switch (formulaire) {
            case SIXMOIS -> "Votre formulaire après six mois d'utilisation";
            default -> "Formulaire avant rendez-vous";
        };
        String token = tokenTool.doGenerateToken(new HashMap<>(), byId.getMail());
        String url = String.format("http://localhost:5173/%s?token=%s", formulaire.name(), token) ;

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("nom", byId.getNom() );
        context.setVariable("prenom", byId.getPrenom() );
        context.setVariable("url", url );

        String emailTemplate = templateEngine.process(template, context);

        MimeMessage msg = javaMailSender.createMimeMessage();
        msg.setFrom(from);
        msg.setRecipients(Message.RecipientType.TO, byId.getMail());
        msg.setText(emailTemplate);
        javaMailSender.send(msg);
    }
}
