package formulairesClient.services;

import formulairesClient.dto.CountDTO;
import formulairesClient.dto.QuestionDTO;
import formulairesClient.dto.UserDTO;
import formulairesClient.entities.Question;
import formulairesClient.entities.User;
import formulairesClient.repositories.QuestionRepository;
import formulairesClient.tools.DtoTool;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import formulairesClient.entities.Question.Formulaire;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

        @Override
    public Page<QuestionDTO> getAllBy(int page, int size, String search) throws Exception {
        return questionRepository.findAllByQuestionContaining(search, PageRequest.of(page,size)).map(
                q->DtoTool.convert(q, QuestionDTO.class));
    }

    @Override
    public CountDTO countBy(String search) throws Exception {
        long nb = questionRepository.countByQuestionContaining(search);
        CountDTO count = new CountDTO();
        count.setNb(nb);
        return count;
    }

    @Override
    public QuestionDTO saveOrUpdate(QuestionDTO questionDto) throws Exception {
        Question question = DtoTool.convert(questionDto, Question.class);
        Question savedQuestion = questionRepository.saveAndFlush(question);
        return DtoTool.convert(savedQuestion, QuestionDTO.class);
    }

    @Override
    public void deleteById(long id) throws Exception {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionDTO getById(long id) {
        Optional<Question> questionOpt = questionRepository.findById(id);
        if(questionOpt.isPresent()){
            return DtoTool.convert(questionOpt.get(), QuestionDTO.class);
        }
        return null;
    }

    @Override
    public List<QuestionDTO> getAllByForm(Formulaire formulaire) throws Exception {
        List<QuestionDTO> result = new ArrayList<>();
        List<Question> questions = questionRepository.findAllByFormulaire(formulaire);
        for(Question q : questions){
            result.add(DtoTool.convert(q, QuestionDTO.class));
        }
        return result;
    }

}
