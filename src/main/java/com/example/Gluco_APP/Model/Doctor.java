package com.example.Gluco_APP.Model; // Assurez-vous que le package est correct

import jakarta.persistence.*; // Importations appropriées
import java.util.List; // Importations si nécessaire

@Entity
@Table(name = "doctor") // Nom de la table
public class Doctor { // Assurez-vous de la définition correcte de la classe

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id; // Assurez-vous que le type d'identifiant est `Long`

    // Autres propriétés de `Doctor`
    private String name; // Exemple de propriété
    // Ajoutez d'autres propriétés comme nécessaire

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
}

