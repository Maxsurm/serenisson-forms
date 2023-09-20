package formulairesClient.services;

import formulairesClient.dto.CountDTO;
import formulairesClient.dto.PatientDTO;
import formulairesClient.entities.Patient;
import formulairesClient.entities.Question.Formulaire;
import formulairesClient.repositories.PatientRepository;
import formulairesClient.tools.DtoTool;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Page<PatientDTO> getAllBy(int page, int size, String search) throws Exception {
        return patientRepository.findAllByNomContaining(search, PageRequest.of(page,size)).map(
                p->DtoTool.convert(p, PatientDTO.class));
    }

    @Override
    public CountDTO countBy(String search) throws Exception {
        long nb = patientRepository.countByNomContaining(search);
        CountDTO count = new CountDTO();
        count.setNb(nb);
        return count;
    }

    @Override
    public PatientDTO saveOrUpdate(PatientDTO patientDto) throws Exception {
        Patient patient = DtoTool.convert(patientDto, Patient.class);
        Patient savedPatient = patientRepository.saveAndFlush(patient);
        return DtoTool.convert(savedPatient, PatientDTO.class);
    }

    @Override
    public void deleteById(long id) throws Exception {
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDTO getById(long id) {
        Optional<Patient> patientOpt = patientRepository.findById(id);
        if(patientOpt.isPresent()){
            return DtoTool.convert(patientOpt.get(), PatientDTO.class);
        }
        return null;
    }


}
