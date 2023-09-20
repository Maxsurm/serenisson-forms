package formulairesClient.tools;

import formulairesClient.entities.Patient;
import formulairesClient.entities.Question.Formulaire;
import formulairesClient.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientUpdater {
    @Autowired
    private PatientRepository p_repository;

    public void updatePatient(Patient patient, Formulaire formulaire, String pdfFileName) {
        if (formulaire.equals(Formulaire.ANAMNESE)) {
            patient.setAnapath(pdfFileName);
        } else {
            patient.setSixpath(pdfFileName);
        }
        p_repository.saveAndFlush(patient);
    }
}
