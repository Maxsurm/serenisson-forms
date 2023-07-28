package formulairesClient.services;

import formulairesClient.entities.Question;
import formulairesClient.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends GenericService<Question, Long, QuestionRepository> {

    protected QuestionService(QuestionRepository repository) {
        super(repository);
    }


}
