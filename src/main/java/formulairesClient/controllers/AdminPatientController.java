package formulairesClient.controllers;

import formulairesClient.entities.Patient;
import formulairesClient.forms.FormPatient;
import formulairesClient.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPatientController {
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
        return "admin/patient/patients";
    }

    //Boutons d'ajout patient - Accès au formulaire
    @GetMapping("/patients/add")
    public String addPatient(@ModelAttribute("formpatient") FormPatient formpatient) {return "admin/patient/addpatient";}

    //Ajout/Modification de patient en BDD
    @PostMapping("/patients/add")
    public String addPatientPost(@Valid @ModelAttribute("formpatient") FormPatient formpatient, BindingResult results, Model model) {
        if(results.hasErrors()) {
            model.addAttribute("formpatient", formpatient);
            model.addAttribute("errors", results);
            return "admin/patient/addpatient";
        }
        else {
            Patient patient=new Patient(formpatient.getNom(),formpatient.getPrenom(),formpatient.getMail());
            p_repository.saveOrUpdate(patient);
            return "redirect:/admin/patients";
        }
    }
}
