package formulairesClient.services.Old;

import formulairesClient.entities.Anamnese;
import formulairesClient.repositories.AnamneseRepository;
import org.springframework.stereotype.Service;

@Service
public class AnamneseServiceOld extends GenericServiceOld<Anamnese, Long, AnamneseRepository> {

    protected AnamneseServiceOld(AnamneseRepository repository) {
        super(repository);
    }


}
