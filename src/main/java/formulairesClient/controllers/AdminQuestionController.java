package formulairesClient.controllers;


import formulairesClient.dto.CountDTO;
import formulairesClient.dto.QuestionDTO;
import formulairesClient.dto.UserDTO;
import formulairesClient.entities.Question;
import formulairesClient.entities.Question.Formulaire;
import formulairesClient.services.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/questions")
public class AdminQuestionController {

    @Autowired
    private IQuestionService questionService;

    private List<Question> questionList = new ArrayList<>();

    //Liste avec pagination
    @GetMapping(value = {"/{page}/{size}/{search}", "/{page}/{size}"}, produces = "application/json")
    public Page<QuestionDTO> getAllBy(@PathVariable("page") int page,
                                      @PathVariable("size") int size,
                                      @PathVariable(value = "search", required = false) Optional<String> searchOpt) throws Exception {
        //page commence par 1
        return questionService.getAllBy(page - 1, size, searchOpt.orElse(""));
    }

    //---GetbyId---
    @GetMapping(value = "/{id}", produces = "application/json")
    public QuestionDTO findById(@PathVariable("id") long id) throws Exception {
        return questionService.getById(id);
    }

    // --- Renvoyer le nombre de questions ---
    @GetMapping(value = {"/count/{search}", "/count"}, produces = "application/json")
    public CountDTO countBy(@PathVariable(value = "search", required = false) Optional<String> searchOpt) throws Exception {
        if (searchOpt.isPresent()) {
            return questionService.countBy(searchOpt.get());
        } else {
            return questionService.countBy("");
        }
    }

    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) throws Exception {
        questionService.deleteById(id);
        return ResponseEntity.ok("Question avec l'id " + id + " est supprimée.");
    }


    //---Creation ou modif Question---
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<QuestionDTO> save(@RequestBody QuestionDTO questionDTO) throws Exception {
        QuestionDTO dtoSaved = questionService.saveOrUpdate(questionDTO);
        return ResponseEntity.ok(dtoSaved);
    }

//     Recuperation des questions lié a un formulaire spécifique
    @GetMapping(value = "/form/{formulaire}", produces = "application/json")
    public List<QuestionDTO> getAllByForm(@PathVariable Formulaire formulaire) throws Exception {
        return questionService.findByFormulaire(formulaire);
    }


}



