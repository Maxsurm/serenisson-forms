package formulairesClient.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class FormPatient {

    @NotBlank
    @Size(min=2,max=50)
    private String prenom;

    @NotBlank
    @Size(min=2,max=80)
    private String nom;

    @NotBlank
    @Email
    private String mail;

    public FormPatient() {
    }

    public FormPatient(String prenom, String nom, String mail) {
        this.prenom = prenom;
        this.nom = nom;
        this.mail = mail;
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
}
