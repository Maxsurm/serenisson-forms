package formulairesClient.services;

import formulairesClient.entities.Patient;
import formulairesClient.repositories.PatientRepository;
import jakarta.xml.bind.Unmarshaller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @Mock
    private PatientRepository repository;
    @Test

    void allTest() {
        // arrange
        List<Patient> listePatient = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(listePatient);
        // action
        PatientService service = new PatientService(repository);
        service.findAll();
        // assert
        Mockito.verify(repository).findAll();
    }


    }

