package formulairesClient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientDTO {

    private long id;

    private int version;

    @NotBlank
    @Size(min=2,max=50)
    private String prenom;

    @NotBlank
    @Size(min=2,max=80)
    private String nom;

    @NotBlank
    @Email
    private String mail;

    private String anapath;

    private String sixpath;

    public String getAnapath() {
        return anapath;
    }

    public void setAnapath(String anapath) {
        this.anapath = anapath;
    }

    public String getSixpath() {
        return sixpath;
    }

    public void setSixpath(String sixpath) {
        this.sixpath = sixpath;
    }

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
