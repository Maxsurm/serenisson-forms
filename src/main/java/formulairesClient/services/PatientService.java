package formulairesClient.services;

import formulairesClient.entities.Patient;
import formulairesClient.repositories.PatientRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends GenericService<Patient, Long, PatientRepository> {

    protected PatientService(PatientRepository repository) {
        super(repository);
    }


}
