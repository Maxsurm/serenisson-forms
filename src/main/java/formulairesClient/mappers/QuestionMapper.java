package formulairesClient.mappers;

import formulairesClient.entities.question.Question;
import formulairesClient.forms.QuestionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface QuestionMapper {

    QuestionDTO toDTO(Question question);

    Question toEntity(QuestionDTO questionDTO);

}
