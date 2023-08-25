//package formulairesClient.services;
//
//import formulairesClient.entities.question.Formulaire;
//import formulairesClient.entities.Question;
//import formulairesClient.entities.question.QuestionType;
//import formulairesClient.repositories.QuestionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//public class QuestionServiceTest {
//    @Mock
//    private QuestionRepository repository;
//    @InjectMocks
//    private QuestionService service;
//
//    private static final Question q1 = new Question(1L,1, "question 1 ?", QuestionType.QCM, Formulaire.ANAMNESE );
//    private static final Question q2 = new Question(2L,2, "question 2 ?", QuestionType.TEXT, Formulaire.SIXMOIS );
//
//    private static List<Question> questions = new ArrayList<>(List.of(
//            q1,
//            q2
//    ));
//
//    @BeforeEach
//    void setup(){
//        questions = new ArrayList<>(List.of(
//                q1,
//                q2
//        ));
//        service = new QuestionService(repository);
//    }
//
//    @Test
//    void findAllTest(){
//        Mockito.when(repository.findAll()).thenReturn(questions);
//        service.findAll();
//        Mockito.verify(repository).findAll();
//    }
//
//    @Test
//    void deleteByIdTest(){
//        Mockito.doNothing().when(repository).deleteById(2L);
//        service.deleteById(2L);
//        Mockito.verify(repository).deleteById(2L);
//    }
//
//    @Test
//    void findByIdTest(){
//        Question q = new Question();
//        long id = 2L;
//        q.setId(id);
//        Mockito.when(repository.findById(id)).thenReturn(Optional.of(q));
//        Question resultat = service.findById(id).orElse(null);
//        assertEquals(q,resultat);
//    }
//
//    private static Stream<Arguments> createModifyProvider(){
//            return Stream.of(
//                    Arguments.of(questions, new Question(1L,4, "question 4 ?", QuestionType.RADIO, Formulaire.ANAMNESE), 1L),
//                    Arguments.of(questions, q2, 2L),
//                    Arguments.of(questions, new Question(0L,3, "question 3 ?", QuestionType.RADIO, Formulaire.SIXMOIS),3L)
//            );
//    }
//
//    @ParameterizedTest
//    @MethodSource("createModifyProvider")
//    void saveOrUpdateTest(List<Question> donnees, Question toEditOrCreate, long valeurAttendue){
//        Mockito.when(repository.save(any(Question.class))).thenAnswer(invocation ->
//                donnees.stream().filter( question -> question.getId() == toEditOrCreate.getId())
//                .findFirst().map(questionTrouvee -> {
//                    donnees.remove(questionTrouvee);
//                    donnees.add(toEditOrCreate);
//                    return toEditOrCreate;
//                }).orElseGet(() ->{
//                    toEditOrCreate.setId(3L);
//                    donnees.add(toEditOrCreate);
//                    return toEditOrCreate;
//                }));
//        Question resultat = service.saveOrUpdate(toEditOrCreate);
//
//        assertAll(
//                ()-> assertTrue(donnees.contains(toEditOrCreate)),
//                ()-> assertEquals(valeurAttendue, resultat.getId())
//        );
//
//    }
//
//
//
//
//
//}
