package formulairesClient.controllers;

import formulairesClient.dto.CountDTO;
import formulairesClient.dto.PatientDTO;
import formulairesClient.services.IEmailService;
import formulairesClient.services.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin/patients")
public class AdminPatientController {

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IEmailService emailService;


    //Liste avec pagination
    @GetMapping(value = {"/{page}/{size}/{search}", "/{page}/{size}"}, produces = "application/json")
    public Page<PatientDTO> getAllBy(@PathVariable("page") int page,
                                     @PathVariable("size")int size,
                                     @PathVariable(value = "search", required = false) Optional<String> searchOpt) throws Exception{
        //page commence par 1
        return patientService.getAllBy(page -1,size, searchOpt.orElse(""));
    }

    //---GetbyId---
    @GetMapping(value = "/{id}", produces = "application/json")
    public PatientDTO findById(@PathVariable("id")long id) throws Exception{
        return patientService.getById(id);
    }

    // --- Renvoyer le nombre de patients ---
    @GetMapping(value = {"/count/{search}","/count"}, produces = "application/json")
    public CountDTO countBy(@PathVariable(value = "search", required = false)Optional<String> searchOpt) throws Exception{
        if(searchOpt.isPresent()){
            return patientService.countBy(searchOpt.get());
        }else{
            return patientService.countBy("");
        }
    }

    //---Creation ou modif Patient---
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO patientDTO) throws Exception{
        PatientDTO dtoSaved = patientService.saveOrUpdate(patientDTO);
        return ResponseEntity.ok(dtoSaved);
    }

    //Envoyer un mail pour le formulaire d'Anamnèse
    @GetMapping("/sendanamnese")
    public String sendAnamnèse(){
        Map<String, Object> model = new HashMap<>();
//        model.put(info = "info")
        emailService.sendMail("anamail.html", model,"Formulaire avant rendez-vous","surmontmaxime@gmail.com" , "cs@serenisson.com" );
        return "redirect:/admin/patients";
    }




























//    //Affichage des patients
//    @GetMapping("/patients")
//    public String displayPatient(Model model){
//        List<Patient> patients=p_repository.findAll();
//
//        model.addAttribute("patients", patients);
//        return "admin/patient/patients";
//    }

    //Boutons d'ajout patient - Accès au formulaire
//    @GetMapping("/patients/add")
//    public String addPatient(@ModelAttribute("formpatient") PatientDTO formpatient) {return "admin/patient/addpatient";}
//
//    @GetMapping("/patients/edit/{id}")
//    public String editPatient(@PathVariable long id, @ModelAttribute("patient") PatientDTO formpatient, Model model) {
//
//        Patient patient = p_repository.findById(id).orElse(new Patient());
//
//        System.out.println(patient.toString());
//
//        model.addAttribute("patient", patientMapper.toDTO(patient));
//        //formpatient = patientMapper.toDTO(patient);
//
//        return "admin/patient/editpatient";
//
//    }


    //Ajout/Modification de patient en BDD
//    @PostMapping("/patients/add")
//    public String addPatientPost(@Valid @ModelAttribute("patient") PatientDTO formpatient, BindingResult results, Model model) {
//        if(results.hasErrors()) {
//            model.addAttribute("patient", formpatient);
//            model.addAttribute("errors", results);
//            return "admin/patient/addpatient";
//        }
//        else {
//            Patient patient = patientMapper.toEntity(formpatient);
//            p_repository.saveOrUpdate(patient);
//            return "redirect:/admin/patients";
//        }
//    }
}
