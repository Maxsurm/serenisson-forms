package formulairesClient.services;

import formulairesClient.dto.LoginDTO;
import formulairesClient.dto.LoginResponseDTO;
import formulairesClient.dto.UserDTO;
import formulairesClient.entities.User;
import formulairesClient.repositories.UserRepository;
import formulairesClient.tools.DtoTool;
import formulairesClient.tools.HashTool;
import formulairesClient.tools.JwtTokenTool;
import formulairesClient.tools.TokenSaver;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenTool jwtTokenTool;
    @Override
    public List<UserDTO> getAll() throws Exception {
        List<UserDTO> result = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User u : users) {
            result.add(DtoTool.convert(u, UserDTO.class));
        }
        return result;
    }

    @Override
    public UserDTO findByEmail(String email) throws Exception {
        User u = userRepository.findByEmail(email);
        if(u != null){
            return DtoTool.convert(u, UserDTO.class);
        }
        return null;
    }

    @Override
    public UserDTO saveOrUpdate(UserDTO userDto) throws Exception {
        //Crypter le password
        User user = DtoTool.convert(userDto, User.class);
        if(user.getId() == 0){
            //Création
            user.setPassword(HashTool.cryptPassword(user.getPassword()));
        }else{
            //Modification : on doit verifier si le password à été modifié coté Front - si c'est le cas on crypt le password
            User userDb = userRepository.findById(user.getId()).get();
            if (!userDb.getPassword().equals(user.getPassword())){
                user.setPassword(HashTool.cryptPassword(user.getPassword()));
            }
        }
        User savedUser = userRepository.saveAndFlush(user);
        return DtoTool.convert(savedUser, UserDTO.class);
    }

    @Override
    public void deleteById(long id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public LoginResponseDTO checkLogin(LoginDTO loginDto) throws Exception {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if(user != null && user.getPassword().equals(HashTool.cryptPassword(loginDto.getPassword()))){
            //connexion Ok - renvoyer le LoginResponseDto
            LoginResponseDTO responseDto = DtoTool.convert(user, LoginResponseDTO.class);
            //Gestion du token
            //Choisir les infos (claims) à injecter dans le body du Token
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            claims.put("email", user.getEmail());
            //Générer le Token
            String token = jwtTokenTool.doGenerateToken(claims, user.getEmail());
            //Sauvegarde du token coté server
            TokenSaver.tokenByEmail.put(user.getEmail(),token);
            //Injecter le Token dans loginResponseDto
            responseDto.setToken(token);
            return responseDto;
        }else{
            throw new Exception("Echec connexion....");
        }

    }

    @Override
    public UserDTO getById(long id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return DtoTool.convert(optional.get(),UserDTO.class);
        }
        return null;
    }
}
