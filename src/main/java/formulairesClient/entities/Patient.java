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
public class Patient extends BaseEntity {


    @Column(nullable = false,length= 50)
    private String prenom;

    @Column(nullable = false,length= 80)
    private String nom;

    @Email
    @Column(nullable = false,length = 100)
    private String mail;

    @OneToOne
    // peut etre vide lors de la création du patient, car peuplé par le patient dans un deuxième temps
    private Anamnese anamnese;

    public Patient(String prenom, String nom, String mail) {
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
    }
}
