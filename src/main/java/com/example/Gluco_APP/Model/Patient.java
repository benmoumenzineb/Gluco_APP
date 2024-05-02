package com.example.Gluco_APP.Model;
import jakarta.persistence.*; // Importations nécessaires
import java.util.List; // Importations nécessaires

@Entity
@Table(name = "patient") // Nom de la table dans la base de données
public class Patient extends Userr { // Classe qui étend `Userr`

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Génération automatique de l'ID
    private Long id; // Clé primaire auto-générée

    @Column(name = "id_patient", unique = true) // Nom de la colonne
    private String idPatient; // Propriété avec un nom cohérent

    @ManyToOne
    @JoinColumn(name = "doctor_id") // Correction du nom de la colonne
    private Doctor doctor; // Relation avec `Doctor`

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true) // Configuration de la relation
    private List<GlucoseRecord> glucoseRecords; // Collection d'enregistrements de glucose

    // Constructeur par défaut requis par JPA
    public Patient() {}

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<GlucoseRecord> getGlucoseRecords() {
        return glucoseRecords;
    }

    public void setGlucoseRecords(List<GlucoseRecord> glucoseRecords) {
        this.glucoseRecords = glucoseRecords;
    }

    public String getIdPatient() { // Correction du nom de la méthode
        return idPatient;
    }

    public void setIdPatient(String idPatient) { // Correction du nom de la méthode
        this.idPatient = idPatient;
    }


}

