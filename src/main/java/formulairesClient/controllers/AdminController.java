package formulairesClient.controllers;

import formulairesClient.entities.Patient;
import formulairesClient.entities.Question;
import formulairesClient.forms.FormPatient;
import formulairesClient.forms.FormQuestion;
import formulairesClient.repositories.QuestionRepository;
import formulairesClient.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /*
    ---------------------PATIENTS---------------------
     */
    //Accès au repository de patient
    @Autowired
    private PatientService p_repository;

    //Affichage des patients
    @GetMapping("/patients")
    public String displayPatient(Model model){
        List<Patient> patients=p_repository.findAll();

        model.addAttribute("patients", patients);
        return "patients";
    }

    //Boutons d'ajout patient - Accès au formulaire
    @GetMapping("/patients/add")
    public String addPatient(@ModelAttribute("formpatient") FormPatient formpatient) {return "addpatient";}

    //Ajout/Modification de patient en BDD
    @PostMapping("/patients/add")
    public String addPatientPost(@Valid @ModelAttribute("formpatient") FormPatient formpatient, BindingResult results, Model model) {
        if(results.hasErrors()) {
            model.addAttribute("formpatient", formpatient);
            model.addAttribute("errors", results);
            return "addpatient";
        }
        else {
            Patient patient=new Patient(formpatient.getNom(),formpatient.getPrenom(),formpatient.getMail());
            p_repository.saveOrUpdate(patient);
            return "redirect:/admin/patients";
        }
    }


    /*
    ---------------------QUESTIONS---------------------
     */
    

    //Accès au repository de question
    @Autowired
    private QuestionRepository q_repository;
    @GetMapping("/questions")
    public String displayQuestion(Model model){
        List<Question> questions=q_repository.findAll();
        model.addAttribute("questions", questions);
        return "questions";
    }

    //Boutons d'ajout questions- Accès au formulaire
    @GetMapping("/questions/add")
    public String addQuestion(@ModelAttribute("formquestion") FormQuestion formquestion) {return "addquestion";}

    //Ajout/Modification de question en BDD
    @PostMapping("/questions/add")
    public String addQuestionPost(@Valid @ModelAttribute("formquestion") FormQuestion formquestion, BindingResult results, Model model) {
        if(results.hasErrors()) {
            model.addAttribute("formquestion", formquestion);
            model.addAttribute("errors", results);
            return "addquestion";
        }
        else {
            Question question=new Question(formquestion.getRankOrder(),formquestion.getQuestion(),formquestion.getType());
            q_repository.saveAndFlush(question);
            return "redirect:/admin/questions";
        }
    }

    //Suppression de question en BDD
    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable long id) throws Exception {
        q_repository.deleteById(id);
        return "redirect:/admin/questions";
    }



}
