package formulairesClient.services;

import formulairesClient.entities.Patient;
import formulairesClient.repositories.PatientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @Mock
    private PatientRepository repository;
    @InjectMocks
    private PatientService service;



    private static final Patient patient1 = new Patient(1L,null,null,null,null);
    private static final Patient patient2 = new Patient(2L,null,null,null,null);


    private static List<Patient> patients = new ArrayList<>(List.of(
            patient1,
            patient2
    ));

    @BeforeEach
    void setup() {
         patients = new ArrayList<>(List.of(
                patient1,
                patient2
        ));
        service = new PatientService(repository);
    }


    @Test
    void allTest() {
        // arrange
        List<Patient> listePatient = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(listePatient);
        // action
        service.findAll();
        // assert
        Mockito.verify(repository).findAll();
    }

    @Test
    void deleteByIdTest() {
        Mockito.doNothing().when(repository).deleteById(1L);
        service.deleteById(1L);
        Mockito.verify(repository).deleteById(1L);
    }

    @Test
    void findByIdTest() {
        // arrange
        Patient patient = new Patient();
        long id = 1L;
        patient.setId(id);

        Mockito.when(repository.findById(id))
                // Le retour de thenReturn doit correspondre au type de retour de la méthode mockée
                .thenReturn(Optional.of(patient)); // le retour attendu etant optionnel on rajoute "Optional.of(entity)"

        // act
        Patient result = service.findById(id);

        // assert
        // Syntaxe assert 1ere valeur est "expected" la seconde est "actual" (result)
        assertEquals(patient,result);
    }

    @Test
    void findByIdTestShouldReturnNull() {
        // arrange
        long id = 1L;

        Mockito.when(repository.findById(id))
                // Le retour de thenReturn doit correspondre au type de retour de la méthode mockée
                .thenReturn(Optional.empty());

        // act
        Patient result = service.findById(id);

        // assert
        assertNull(result);
    }


    private static Stream<Arguments> byIdProvider() {

        return Stream.of(
                Arguments.of(patients, 1L, patient1),
                Arguments.of(patients, 2L, patient2),
                Arguments.of(patients, 3L, null)
        );
    }

    @ParameterizedTest
    @MethodSource("byIdProvider")
    void findByIdParametrizedTest(List<Patient> datas, long id, Patient expected) {
        // arrange
        Mockito.when(repository.findById(id))
                .thenReturn(datas.stream().filter(patient -> patient.getId() == id).findFirst());
        // act
        Patient result = service.findById(id);
        // assert
        // Syntaxe assert 1ere valeur est "expected" la seconde est "actual" (result)
        assertEquals(expected,result);
    }




    private static Stream<Arguments> saveOrUpdateProvider() {
        return Stream.of(
                Arguments.of(patients, new Patient(1L, "Otto", "Wayred", null, null) , 1L),
                Arguments.of(patients, new Patient(2L, "Jhon", "Doe", null, null) , 2L),
                Arguments.of(patients, new Patient(0L, "Alex", "Terrieur", null, null) , 3L)
        );
    }

    @ParameterizedTest
    @MethodSource("saveOrUpdateProvider")
    void saveOrUpdateTest(List<Patient> datas, Patient toSave, long expected) {

        Mockito.when(repository.save(any(Patient.class))).thenAnswer(invocation ->
            datas.stream().filter(patient -> patient.getId() == toSave.getId())
                    .findFirst().map(patientFound -> {
                        System.out.println("Updating Existing value");
                        datas.remove(patientFound);
                        datas.add(toSave);
                        return toSave;
                    }).orElseGet(() -> {
                        System.out.println("Adding new value");
                        toSave.setId(3L);
                        datas.add(toSave);
                        return toSave;
                    }));

        Patient result = service.saveOrUpdate(toSave);

        assertAll(
                () -> assertTrue(datas.contains(toSave)),
                () -> assertEquals(expected, result.getId())
        );
    }
}

