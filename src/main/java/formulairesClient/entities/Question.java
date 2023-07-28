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

    private int rankOrder;

    private String question;

    @Enumerated(EnumType.STRING)
    private QuestionType type;
}
