package formulairesClient.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User extends BaseEntity {

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}
