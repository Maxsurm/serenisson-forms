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
    @Column(nullable = false,length = 100, unique = true)
    private String mail;

    @Column(nullable = true)
    private String anapath;

    @Column(nullable = true)
    private String sixpath;

    public Patient(String prenom, String nom, String mail) {
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
    }
}
