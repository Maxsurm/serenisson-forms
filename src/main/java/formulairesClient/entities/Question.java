package formulairesClient.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Enumeration;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //rang dans lequel apparaitra la question
    private int rankOrder;

    //intitulé de la question
    private String question;

    // L'énumération servira à choisir le template en fonction du type de question (RADIO, QCM ou TexteArea)
    @Enumerated(EnumType.STRING)
    private QuestionType type;
}
