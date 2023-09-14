package formulairesClient.intercepteurs;

import formulairesClient.tools.JwtTokenTool;
import formulairesClient.tools.TokenSaver;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {

        @Autowired
        private JwtTokenTool jwtTokenTool;
        @Value("${jwt.secret}")
        private String secret;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            //Verification du token dans le header de la requete (Bearer Token)
            String header = request.getHeader("Authorization");
            if (header == null || header.length()<7 || header.trim().equals("")){
//                throw new Exception("Token invalide.");
                response.setStatus(403);
                response.getWriter().write("Token invalide.");
                return false;
            }
            String token = header.substring(7); //suppression du mot clé Bearer suivi d'un espace
            if(jwtTokenTool.isTokenExpired(token)){ //verification de l'expiration
                response.setStatus(403);
                response.getWriter().write("Token invalide.");
                return false;
            }
//            Comparer le token fournit avec le Token sauvegardé dans le server
            String email = jwtTokenTool.getUsernameFromToken(token); //On récupère l'email grace au token fournit
            if(!TokenSaver.tokenByEmail.containsKey(email) || !TokenSaver.tokenByEmail.get(email).equals(token)){
                response.setStatus(403);
                response.getWriter().write("Token invalide.");
                return false;
            }
            return true;
        }
}
