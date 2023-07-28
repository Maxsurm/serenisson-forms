package formulairesClient.entities;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length= 50)
    private String prenom;

    @Column(length= 80)
    private String nom;

    @Column(nullable = false,length = 100)
    private String mail;

    @OneToOne
    @Column(nullable = true)
    private Anamnese anamnese;
}
