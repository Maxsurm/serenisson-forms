package formulairesClient.controllers;

import formulairesClient.entities.question.Question;
import formulairesClient.forms.FormQuestion;
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
    @GetMapping("/questions")
    public String displayQuestion(Model model){
        List<Question> questions=q_repository.findAll();
        model.addAttribute("questions", questions);
        return "admin/question/questions";
    }

    //Boutons d'ajout questions- Accès au formulaire
    @GetMapping("/questions/add")
    public String addQuestion(@ModelAttribute("formquestion") FormQuestion formquestion) {return "admin/question/addquestion";}

    //Ajout/Modification de question en BDD
    @PostMapping("/questions/add")
    public String addQuestionPost(@Valid @ModelAttribute("formquestion") FormQuestion formquestion, BindingResult results, Model model) {
        if(results.hasErrors()) {
            model.addAttribute("formquestion", formquestion);
            model.addAttribute("errors", results);
            return "admin/question/addquestion";
        }
        else {
            Question question=new Question(0,formquestion.getRankOrder(),formquestion.getQuestion(),formquestion.getType(),formquestion.getFormulaire());
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
