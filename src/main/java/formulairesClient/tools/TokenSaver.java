package formulairesClient.tools;

import java.util.HashMap;
import java.util.Map;

public class TokenSaver {
    /* MAp utilisée par le server pour stocker les tokens renvoyés aux clients
    clé : email
    Valeur : Token
     */

    public static Map<String, String> tokenByEmail;
    //Bloc d'initialisation static : permet d'initialiser tous les attributs static d'une classe
    static{
        tokenByEmail = new HashMap<>();
    }

}
