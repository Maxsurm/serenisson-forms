package formulairesClient.controllers;


import formulairesClient.dto.UserDTO;
import formulairesClient.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class AdminUserController {
    @Autowired
    private IUserService userService;

    @GetMapping(produces = "application/json")
    public List<UserDTO> getAll() throws Exception{
        return userService.getAll();
    }

    @DeleteMapping(value = "/{id}", produces="text/plain")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id) throws Exception{
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted.");
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) throws Exception{
        UserDTO dtoSaved = userService.saveOrUpdate(userDTO);
        return ResponseEntity.ok(dtoSaved);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserDTO getById(@PathVariable("id")long id){
        return userService.getById(id);
    }

}
