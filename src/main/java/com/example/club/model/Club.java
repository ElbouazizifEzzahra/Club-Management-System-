package com.example.club.model;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String domain;
    private String logo; // Chemin du fichier logo
// explication
    /*@Transient : Indique à JPA/Hibernate d'ignorer ce champ lors de la persistance.

MultipartFile logoFile : Utilisé uniquement pour récupérer le fichier uploadé dans le contrôleur.

String logo : Stocke le chemin du fichier logo (ex: /pictures/logo.png).*/
    @Transient // Ce champ ne sera PAS stocké en base de données
    private MultipartFile logoFile;
    @ElementCollection
    private List<String> members; // Liste des membres

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
    public MultipartFile getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(MultipartFile logoFile) {
        this.logoFile = logoFile;
    }
}