package formulairesClient.repositories;

import formulairesClient.entities.Question;
import formulairesClient.entities.Question.Formulaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByRankOrder(int rankOrder);
    Page<Question> findAllByQuestionContaining(String nom, Pageable pageable);
    long countByQuestionContaining(String nom);

    List<Question> findAllByFormulaire(Formulaire formulaire);

}
