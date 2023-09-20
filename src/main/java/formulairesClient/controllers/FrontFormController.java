package formulairesClient.controllers;



import formulairesClient.dto.ResponseDTO;
import formulairesClient.entities.Patient;
import formulairesClient.entities.Question.Formulaire;
import formulairesClient.repositories.PatientRepository;
import formulairesClient.repositories.QuestionRepository;
import formulairesClient.tools.JwtTokenTool;
import formulairesClient.tools.PatientUpdater;
import formulairesClient.tools.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/form")
public class FrontFormController {


    //Accès au repository des questions
    @Autowired
    private QuestionRepository q_repository;

    //Accès au repository de patient
    @Autowired
    private PatientRepository p_repository;

    @Autowired
    private JwtTokenTool tokenTool;

    @Value("${storage.folder}")
    private String storageFolder;

    @Autowired
    private PdfGenerator pdfGenerator;
    @Autowired
    private PatientUpdater patientUpdater;


    @PostMapping("/{formulaire}/{token}")
    public void recupererReponses(@PathVariable Formulaire formulaire, @PathVariable String token, @RequestBody List<ResponseDTO> reponses) throws Exception {
        Patient patient = p_repository.findByMail(tokenTool.getUsernameFromToken(token));
        String pdfFileName = pdfGenerator.generatePdf(formulaire, patient, reponses);
        patientUpdater.updatePatient(patient, formulaire, pdfFileName);
    }


}
