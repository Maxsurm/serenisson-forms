package formulairesClient.services.Old;

import formulairesClient.entities.User;
import formulairesClient.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceOld extends GenericServiceOld<User, Long, UserRepository> {

    protected UserServiceOld(UserRepository repository) {
        super(repository);
    }


}
