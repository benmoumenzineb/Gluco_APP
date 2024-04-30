package com.example.Gluco_APP.Controller;

import com.example.Gluco_APP.Model.Patient;
import com.example.Gluco_APP.Repository.PatientRepository; // Importation du repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Pour renvoyer des listes de patients

@RestController
@RequestMapping("/api/patients") // Point de départ pour les routes liées aux patients
public class PatientController {

    private final PatientRepository patientRepository; // Injection du repository

    @Autowired // Injection via le constructeur
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository; // Assignation du repository
    }

    @GetMapping("/{id}") // Point de terminaison pour obtenir un patient par son ID
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        // Trouver le patient par ID
        return patientRepository.findById(id) // Utilisation du repository
                .map(patient -> ResponseEntity.ok(patient)) // Si trouvé
                .orElse(ResponseEntity.notFound().build()); // Si non trouvé
    }

    @PostMapping("/") // Point de terminaison pour créer un nouveau patient
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        // Sauvegarder le nouveau patient
        Patient savedPatient = patientRepository.save(patient);
        return ResponseEntity.status(201).body(savedPatient); // Retourner le patient créé
    }

    @PutMapping("/{id}") // Point de terminaison pour mettre à jour un patient
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient updatedPatient) {
        // Trouver le patient à mettre à jour
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setIdPatient(updatedPatient.getIdPatient());
                    patient.setDoctor(updatedPatient.getDoctor());
                    patient.setGlucoseRecords(updatedPatient.getGlucoseRecords());
                    Patient savedPatient = patientRepository.save(patient); // Sauvegarder les changements
                    return ResponseEntity.ok(savedPatient);
                })
                .orElse(ResponseEntity.notFound().build()); // Si le patient n'est pas trouvé
    }

    @DeleteMapping("/{id}") // Point de terminaison pour supprimer un patient
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        if (patientRepository.existsById(id)) { // Vérifier si le patient existe
            patientRepository.deleteById(id); // Supprimer le patient
            return ResponseEntity.noContent().build(); // Retourner une réponse vide
        } else {
            return ResponseEntity.notFound().build(); // Si le patient n'est pas trouvé
        }
    }
}

