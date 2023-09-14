package formulairesClient.services;

import java.util.Map;

public interface IEmailService {
    void sendMail(String template, Map<String, Object>modelMap, String title, String to, String from);
}
