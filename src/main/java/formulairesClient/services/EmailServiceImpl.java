package formulairesClient.services;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailServiceImpl implements IEmailService{


    //Envoi de mail templated
    @Override
    public void sendMail(String template, Map<String, Object> modelMap, String title, String to, String from) {

    }
}
