package formulairesClient.services;

import formulairesClient.dto.CountDTO;
import formulairesClient.dto.PatientDTO;

import java.util.List;

public class AnamneseServiceImpl implements IAnamneseService{
    @Override
    public List<PatientDTO> getAllBy(int page, int Size, String search) throws Exception {
        return null;
    }

    @Override
    public CountDTO countBy(String search) throws Exception {
        return null;
    }

    @Override
    public PatientDTO saveOrUpdate(PatientDTO patientDTO) throws Exception {
        return null;
    }

    @Override
    public void deleteById(long id) throws Exception {

    }

    @Override
    public PatientDTO getById(long id) {
        return null;
    }
}
