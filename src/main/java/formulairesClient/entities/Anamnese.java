package formulairesClient.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


// Entitée recevant les fichiers PDF suite à la complétion des formulaires par les patients
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Anamnese extends BaseEntity {


    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    //propriété recevant les pdf
//    @Lob
//    private byte[] document;
}
