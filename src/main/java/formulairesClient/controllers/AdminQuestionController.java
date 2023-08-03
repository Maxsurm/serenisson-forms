package formulairesClient.controllers;


import formulairesClient.entities.question.Question;
import formulairesClient.forms.QuestionDTO;
import formulairesClient.mappers.QuestionMapper;
import formulairesClient.repositories.QuestionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminQuestionController {
     /*
    ---------------------QUESTIONS---------------------
     */


    //Accès au repository de question
    @Autowired
    private QuestionRepository q_repository;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/questions")
    public String displayQuestion(Model model){
        List<Question> questions=q_repository.findAll();
        model.addAttribute("questions", questions);
        return "admin/question/questions";
    }

    //Boutons d'ajout questions- Accès au formulaire
    @GetMapping("/questions/add")
    public String addQuestion(@ModelAttribute("formquestion") QuestionDTO formquestion) {return "admin/question/addquestion";}

    //Ajout/Modification de question en BDD
    @PostMapping("/questions/add")
    public String addQuestionPost(@Valid @ModelAttribute("question") QuestionDTO formquestion, BindingResult results, Model model) {
        if(results.hasErrors()) {
            model.addAttribute("question", formquestion);
            model.addAttribute("errors", results);
            return "admin/question/addquestion";
        }
        else {
            Question question = questionMapper.toEntity(formquestion);
            q_repository.saveAndFlush(question);
            return "redirect:/admin/questions";
        }
    }

    @GetMapping("/questions/edit/{id}")
    public String editQuestion(@PathVariable long id, @ModelAttribute("question") QuestionDTO formquestion, Model model) {

        Question question = q_repository.findById(id).orElse(new Question());

        System.out.println(question.toString());

        model.addAttribute("question", questionMapper.toDTO(question));
        //formquestion = questionMapper.toDTO(question);

        return "admin/question/editquestion";

    }

    //Suppression de question en BDD
    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable long id) throws Exception {
        q_repository.deleteById(id);
        return "redirect:/admin/questions";
    }
}
