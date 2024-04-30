package com.example.Gluco_APP.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "glucose_record")
public class GlucoseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false) // Ajout de nullable=false pour la contrainte de clé étrangère
    private Patient patient; // Relation avec l'entité Patient

    @Column(name = "glucose_level", nullable = false) // Assurez-vous que la colonne n'accepte pas de valeurs nulles
    private double glucoseLevel;

    @Column(name = "recorded_date", nullable = false) // Assurez-vous que la date d'enregistrement est non nulle
    private LocalDateTime recordedDate;

    // Constructeur par défaut
    public GlucoseRecord() {}
    public GlucoseRecord(double glucoseLevel) {
        // Assurez-vous que les valeurs par défaut sont définies correctement
        this.glucoseLevel = glucoseLevel; // Now correctly setting glucoseLevel
        this.recordedDate = LocalDateTime.now(); // Définir la date d'enregistrement à l'heure actuelle
    }


    // Constructeur avec glucoseLevel et patient
    public GlucoseRecord(double glucoseLevel, Patient patient) {
        this(glucoseLevel);
        this.glucoseLevel = glucoseLevel; // Définition du niveau de glucose
        this.patient = patient; // Association au patient
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public double getGlucoseLevel() {
        return glucoseLevel;
    }

    public void setGlucoseLevel(double glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }

    public LocalDateTime getRecordedDate() {
        return recordedDate;
    }

    public void setRecordedDate(LocalDateTime recordedDate) {
        this.recordedDate = recordedDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

}

