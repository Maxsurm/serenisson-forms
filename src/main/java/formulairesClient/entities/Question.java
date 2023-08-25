package formulairesClient.entities;

import formulairesClient.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Question extends BaseEntity {

    //rang dans lequel apparaitra la question
    private int rankOrder;

    //intitulé de la question
    private String question;

    // L'énumération servira à choisir le template en fonction du type de question (RADIO, QCM ou TexteArea)
    public enum QuestionType {
        RADIO, QCM, TEXT
    }

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    public enum Formulaire {
        ANAMNESE, SIXMOIS
    }

    @Enumerated(EnumType.STRING)
    private Formulaire formulaire;

}
