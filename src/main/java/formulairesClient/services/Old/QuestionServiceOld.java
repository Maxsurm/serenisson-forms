package formulairesClient.services.Old;

import formulairesClient.entities.Question;
import formulairesClient.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceOld extends GenericServiceOld<Question, Long, QuestionRepository> {

    protected QuestionServiceOld(QuestionRepository repository) {
        super(repository);
    }


}
