package formulairesClient.controllers;

import formulairesClient.dto.LoginDTO;
import formulairesClient.dto.LoginResponseDTO;
import formulairesClient.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private IUserService userService;
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public LoginResponseDTO checkLogin(@RequestBody LoginDTO loginDto) throws Exception {
        return userService.checkLogin(loginDto);
    }
}
