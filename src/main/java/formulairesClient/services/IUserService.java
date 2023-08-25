package formulairesClient.services;

import formulairesClient.dto.*;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAll() throws Exception;
    UserDTO findByEmail(String email)throws Exception;

    UserDTO saveOrUpdate(UserDTO userDto)throws Exception;
    void deleteById(long id)throws Exception;
    LoginResponseDTO checkLogin(LoginDTO loginDto) throws Exception;
    UserDTO getById(long id);
}
