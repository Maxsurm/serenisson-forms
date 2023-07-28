package formulairesClient.services;

import formulairesClient.entities.User;
import formulairesClient.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User, Long, UserRepository> {

    protected UserService(UserRepository repository) {
        super(repository);
    }


}
