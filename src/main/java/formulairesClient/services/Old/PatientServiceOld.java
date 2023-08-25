package formulairesClient.services.Old;

import formulairesClient.dto.PatientDTO;
import formulairesClient.entities.Patient;
import formulairesClient.repositories.PatientRepository;
import formulairesClient.tools.DtoTool;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientServiceOld extends GenericServiceOld<Patient, Long, PatientRepository> {

    protected PatientServiceOld(PatientRepository repository) {
        super(repository);
    }

    public PatientDTO findById(long id) {
        Optional<Patient> patOpt = repository.findById(id);
        if(patOpt.isPresent()){
            return DtoTool.convert(patOpt.get(), PatientDTO.class);
        }
        return null;
    }



}
