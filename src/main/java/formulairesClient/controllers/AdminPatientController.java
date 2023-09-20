package formulairesClient.controllers;

import formulairesClient.dto.CountDTO;
import formulairesClient.dto.PatientDTO;

import formulairesClient.repositories.PatientRepository;
import formulairesClient.services.IEmailService;
import formulairesClient.services.IPatientService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import formulairesClient.entities.Question.Formulaire;

@RestController
@RequestMapping("/admin/patients")
public class AdminPatientController {
    @Value("${storage.folder}")
    private String storageFolder;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IEmailService emailService;


    //Liste avec pagination
    @GetMapping(value = {"/{page}/{size}/{search}", "/{page}/{size}"}, produces = "application/json")
    public Page<PatientDTO> getAllBy(@PathVariable("page") int page,
                                     @PathVariable("size") int size,
                                     @PathVariable(value = "search", required = false) Optional<String> searchOpt) throws Exception {
        //page commence par 1
        return patientService.getAllBy(page - 1, size, searchOpt.orElse(""));
    }

    //---GetbyId---
    @GetMapping(value = "/{id}", produces = "application/json")
    public PatientDTO findById(@PathVariable long id) {
        return patientService.getById(id);
    }

    // --- Renvoyer le nombre de patients ---
    @GetMapping(value = {"/count/{search}", "/count"}, produces = "application/json")
    public CountDTO countBy(@PathVariable(value = "search", required = false) Optional<String> searchOpt) throws Exception {
        if (searchOpt.isPresent()) {
            return patientService.countBy(searchOpt.get());
        } else {
            return patientService.countBy("");
        }
    }

    //---Creation ou modif Patient---
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO patientDTO) throws Exception {
        PatientDTO dtoSaved = patientService.saveOrUpdate(patientDTO);
        return ResponseEntity.ok(dtoSaved);
    }

    //Envoyer un mail pour les formulaires
    @GetMapping("/sendform/{formulaire}/{id}")
    public String sendForm(@PathVariable Formulaire formulaire, @PathVariable Long id) throws MessagingException {
        emailService.sendMail(formulaire, patientService.getById(id));

        return "redirect:/admin/patients";
    }

    @GetMapping("/getform/{formulaire}/{id}")
    public ResponseEntity<Resource> getPatientForm(@PathVariable Formulaire formulaire, @PathVariable Long id) throws IOException {
        PatientDTO p = patientService.getById(id);

        Resource resource = null;
        try {
            Path path = switch (formulaire) {
                case SIXMOIS -> Paths.get(".").resolve(storageFolder+p.getSixpath());
                default -> Paths.get(".").resolve(storageFolder+p.getAnapath());
            };
            resource = new UrlResource(path.toUri());
        }catch(Exception e){
            throw new RuntimeException();
        }
        Path newPath = resource.getFile().toPath();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(newPath))
                .body(resource);
    }
}
