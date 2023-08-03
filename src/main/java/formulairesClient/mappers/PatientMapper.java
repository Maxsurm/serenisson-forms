package formulairesClient.mappers;

import formulairesClient.entities.Patient;
import formulairesClient.forms.PatientDTO;
import org.mapstruct.Mapper;

@Mapper
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);

    Patient toEntity(PatientDTO patientDTO);

}
