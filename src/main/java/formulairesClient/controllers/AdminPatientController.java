package formulairesClient.controllers;

import formulairesClient.entities.Patient;
import formulairesClient.forms.PatientDTO;
import formulairesClient.mappers.PatientMapper;
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
public class AdminPatientController {
    /*
---------------------PATIENTS---------------------
 */
    //Accès au repository de patient
    @Autowired
    private PatientService p_repository;

    @Autowired
    private PatientMapper patientMapper;

    //Affichage des patients
    @GetMapping("/patients")
    public String displayPatient(Model model){
        List<Patient> patients=p_repository.findAll();

        model.addAttribute("patients", patients);
        return "admin/patient/patients";
    }

    //Boutons d'ajout patient - Accès au formulaire
    @GetMapping("/patients/add")
    public String addPatient(@ModelAttribute("formpatient") PatientDTO formpatient) {return "admin/patient/addpatient";}

    @GetMapping("/patients/edit/{id}")
    public String editPatient(@PathVariable long id, @ModelAttribute("patient") PatientDTO formpatient, Model model) {

        Patient patient = p_repository.findById(id).orElse(new Patient());

        System.out.println(patient.toString());

        model.addAttribute("patient", patientMapper.toDTO(patient));
        //formpatient = patientMapper.toDTO(patient);

        return "admin/patient/editpatient";

    }


    //Ajout/Modification de patient en BDD
    @PostMapping("/patients/add")
    public String addPatientPost(@Valid @ModelAttribute("patient") PatientDTO formpatient, BindingResult results, Model model) {
        if(results.hasErrors()) {
            model.addAttribute("patient", formpatient);
            model.addAttribute("errors", results);
            return "admin/patient/addpatient";
        }
        else {
            Patient patient = patientMapper.toEntity(formpatient);
            p_repository.saveOrUpdate(patient);
            return "redirect:/admin/patients";
        }
    }
}
