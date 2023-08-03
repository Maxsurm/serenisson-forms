package formulairesClient.forms;

import formulairesClient.entities.question.Formulaire;
import formulairesClient.entities.question.QuestionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class FormQuestion {

    @Range(min=1)
    private int rankOrder;

    @NotBlank
    @Size(min=2,max=255)
    private String question;

    private QuestionType type;

    private Formulaire formulaire;

    public Formulaire getFormulaire() {
        return formulaire;
    }

    public void setFormulaire(Formulaire formulaire) {
        this.formulaire = formulaire;
    }

    public int getRankOrder() {
        return rankOrder;
    }

    public void setRankOrder(int rankOrder) {
        this.rankOrder = rankOrder;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }
}
