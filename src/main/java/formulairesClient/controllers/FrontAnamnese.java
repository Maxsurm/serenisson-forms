package formulairesClient.controllers;


import formulairesClient.repositories.PatientRepository;
import formulairesClient.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anamnese")
public class FrontAnamnese {


    //Accès au repository des questions
    @Autowired
    private QuestionRepository q_repository;

    //Accès au repository de patient
    @Autowired
    private PatientRepository p_repository;


}
