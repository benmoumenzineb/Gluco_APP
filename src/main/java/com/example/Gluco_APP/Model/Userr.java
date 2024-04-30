package com.example.Gluco_APP.Model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class Userr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisation correcte de la clé primaire
    private Long id; // Correction du type de clé primaire

    private String nom; // Correction des noms des variables
    private String prenom;
    private String motDePasse;
    private String telephone;

    // Constructeur par défaut requis par JPA
    public Userr() {}

    // Constructeur avec tous les attributs
    public Userr(String nom, String prenom, String motDePasse, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }

    // Getters et Setters
    public Long getId() { // Correction de la clé primaire
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
