package formulairesClient.services;

import formulairesClient.dto.PatientDTO;
import formulairesClient.entities.Question.Formulaire;
import jakarta.mail.MessagingException;

import java.util.Map;

public interface IEmailService {

    void sendMail(Formulaire formulaire, PatientDTO byId) throws MessagingException;
}
