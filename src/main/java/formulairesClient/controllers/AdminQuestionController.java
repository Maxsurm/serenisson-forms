package formulairesClient.controllers;


import formulairesClient.dto.CountDTO;
import formulairesClient.dto.QuestionDTO;
import formulairesClient.services.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/questions")
public class AdminQuestionController {

    @Autowired
    private IQuestionService questionService;

    //Liste avec pagination
    @GetMapping(value = {"/{page}/{size}/{search}", "/{page}/{size}"}, produces = "application/json")
    public List<QuestionDTO> getAllBy(@PathVariable("page") int page,
                                     @PathVariable("size")int size,
                                     @PathVariable(value = "search", required = false) Optional<String> searchOpt) throws Exception{
        //page commence par 1
        if(searchOpt.isPresent()){
            return questionService.getAllBy(page -1,size, searchOpt.get());
        }else{
            return questionService.getAllBy(page -1,size, "");
        }
    }

    //---GetbyId---
    @GetMapping(value = "/{id}", produces = "application/json")
    public QuestionDTO findById(@PathVariable("id")long id) throws Exception{
        return questionService.getById(id);
    }

    // --- Renvoyer le nombre de patients ---
    @GetMapping(value = {"/count/{search}","/count"}, produces = "application/json")
    public CountDTO countBy(@PathVariable(value = "search", required = false)Optional<String> searchOpt) throws Exception{
        if(searchOpt.isPresent()){
            return questionService.countBy(searchOpt.get());
        }else{
            return questionService.countBy("");
        }
    }

    @DeleteMapping(value = "/{id}", produces = "text/plain")
    public ResponseEntity<String> deleteById(@PathVariable("id")long id)throws Exception{
        questionService.deleteById(id);
        return ResponseEntity.ok("Question avec l'id "+id+" est supprimée.");
    }


    //---Creation ou modif Patient---
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<QuestionDTO> save(@RequestBody QuestionDTO questionDTO) throws Exception{
        QuestionDTO dtoSaved = questionService.saveOrUpdate(questionDTO);
        return ResponseEntity.ok(dtoSaved);
    }










//    @Autowired
//    private QuestionRepository repository;
//    @Autowired
//    private QuestionMapper questionMapper;
//
//    @GetMapping("/questions")
//    public String displayQuestion(Model model){
//        List<Question> questions=q_repository.findAll();
//        model.addAttribute("questions", questions);
//        return "admin/question/questions";
//    }
//
//    //Boutons d'ajout questions- Accès au formulaire
//    @GetMapping("/questions/add")
//    public String addQuestion(@ModelAttribute("formquestion") QuestionDTO formquestion) {return "admin/question/addquestion";}
//
//    //Ajout/Modification de question en BDD
//    @PostMapping("/questions/add")
//    public String addQuestionPost(@Valid @ModelAttribute("question") QuestionDTO formquestion, BindingResult results, Model model) {
//        if(results.hasErrors()) {
//            model.addAttribute("question", formquestion);
//            model.addAttribute("errors", results);
//            return "admin/question/addquestion";
//        }
//        else {
//            Question question = questionMapper.toEntity(formquestion);
//            q_repository.saveAndFlush(question);
//            return "redirect:/admin/questions";
//        }
//    }
//
//    @GetMapping("/questions/edit/{id}")
//    public String editQuestion(@PathVariable long id, @ModelAttribute("question") QuestionDTO formquestion, Model model) {
//
//        Question question = q_repository.findById(id).orElse(new Question());
//
//        System.out.println(question.toString());
//
//        model.addAttribute("question", questionMapper.toDTO(question));
//        //formquestion = questionMapper.toDTO(question);
//
//        return "admin/question/editquestion";
//
//    }
//
//    //Suppression de question en BDD
//    @GetMapping("/questions/delete/{id}")
//    public String deleteQuestion(@PathVariable long id) throws Exception {
//        q_repository.deleteById(id);
//        return "redirect:/admin/questions";
//    }
}
