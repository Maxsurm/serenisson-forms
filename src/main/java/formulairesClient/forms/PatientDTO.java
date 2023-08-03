package formulairesClient.forms;

import formulairesClient.entities.Anamnese;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientDTO {

    private long id;


    @NotBlank
    @Size(min=2,max=50)
    private String prenom;

    @NotBlank
    @Size(min=2,max=80)
    private String nom;

    @NotBlank
    @Email
    private String mail;

    private Anamnese anamnese;

    public PatientDTO() {
    }

    public PatientDTO(String prenom, String nom, String mail) {
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }
}
