package formulairesClient.services;

import formulairesClient.dto.CountDTO;
import formulairesClient.dto.PatientDTO;

import java.util.List;

public interface IAnamneseService {
    List<PatientDTO> getAllBy(int page, int Size, String search) throws Exception;
    CountDTO countBy(String search) throws Exception;
    PatientDTO saveOrUpdate(PatientDTO patientDTO) throws Exception;
    void deleteById(long id) throws Exception;
    PatientDTO getById(long id);
}
