package formulairesClient.services;

import formulairesClient.entities.Anamnese;
import formulairesClient.repositories.AnamneseRepository;
import org.springframework.stereotype.Service;

@Service
public class AnamneseService extends GenericService<Anamnese, Long, AnamneseRepository> {

    protected AnamneseService(AnamneseRepository repository) {
        super(repository);
    }


}
