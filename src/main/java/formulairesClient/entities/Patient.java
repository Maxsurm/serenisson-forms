package formulairesClient.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Patient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //generation automatique d'ID
    private long id;

    @Column(nullable = false,length= 50)
    private String prenom;

    @Column(nullable = false,length= 80)
    private String nom;

    @Email
    @Column(nullable = false,length = 100)
    private String mail;

    @OneToOne
    @Column(nullable = true) // peut etre vide lors de la création du patient, car peuplé par le patient dans un deuxième temps
    private Anamnese anamnese;

    public Patient(String nom, String prenom, String mail) {
    }
}
