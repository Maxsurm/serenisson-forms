package formulairesClient.services;

import formulairesClient.dto.CountDTO;
import formulairesClient.dto.PatientDTO;
import formulairesClient.entities.Patient;
import formulairesClient.repositories.PatientRepository;
import formulairesClient.tools.DtoTool;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<PatientDTO> getAllBy(int page, int size, String search) throws Exception {
        List<PatientDTO> result = new ArrayList<>();
        List<Patient> patients = patientRepository.findAllByNomContaining(search, PageRequest.of(page,size)).getContent();
        for(Patient p : patients){
            result.add(DtoTool.convert(p, PatientDTO.class));
        }
        return result;
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
