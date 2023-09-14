package formulairesClient.services;

import formulairesClient.dto.CountDTO;
import formulairesClient.dto.QuestionDTO;
import formulairesClient.entities.Question.Formulaire;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IQuestionService {
    Page<QuestionDTO> getAllBy(int page, int Size, String search) throws Exception;
    CountDTO countBy(String search) throws Exception;
    QuestionDTO saveOrUpdate(QuestionDTO questionDTO) throws Exception;
    void deleteById(long id) throws Exception;
    QuestionDTO getById(long id);

    List<QuestionDTO> getAllByForm(Formulaire formulaire) throws Exception;
}
