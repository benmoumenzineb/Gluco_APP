package com.example.Gluco_APP.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List; // Pour renvoyer des collections
import java.util.Optional; // Pour gérer les valeurs optionnelles
import com.example.Gluco_APP.Model.Doctor; // Importation de l'entité
import com.example.Gluco_APP.Repository.DoctorRepository; // Importation du repository

@RestController
@RequestMapping("/api/doctors") // Chemin de base pour les routes liées aux médecins
public class DoctorController {

    private final DoctorRepository doctorRepository; // Injection du repository

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository; // Initialisation du repository
    }

    @GetMapping("/") // Point de terminaison pour obtenir la liste de tous les médecins
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll(); // Obtenir tous les médecins
        return ResponseEntity.ok(doctors); // Retourner la liste des médecins
    }

    @GetMapping("/{id}") // Point de terminaison pour obtenir un médecin par ID
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorRepository.findById(id); // Rechercher le médecin par ID
        return doctor.map(ResponseEntity::ok) // Si trouvé, retourner le médecin
                .orElse(ResponseEntity.notFound().build()); // Si non trouvé, retourner 404
    }

    @PostMapping("/") // Point de terminaison pour créer un nouveau médecin
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor); // Sauvegarder le médecin
        return ResponseEntity.status(201).body(savedDoctor); // Retourner le médecin créé avec code 201
    }

    @PutMapping("/{id}") // Point de terminaison pour mettre à jour un médecin par ID
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        return doctorRepository.findById(id) // Rechercher le médecin par ID
                .map(existingDoctor -> {
                    // Mettre à jour les détails du médecin
                    existingDoctor.setName(doctorDetails.getName());
                    Doctor updatedDoctor = doctorRepository.save(existingDoctor); // Sauvegarder les changements
                    return ResponseEntity.ok(updatedDoctor); // Retourner le médecin mis à jour
                })
                .orElse(ResponseEntity.notFound().build()); // Si non trouvé, retourner 404
    }

    @DeleteMapping("/{id}") // Point de terminaison pour supprimer un médecin par ID
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorRepository.existsById(id)) { // Si le médecin existe
            doctorRepository.deleteById(id); // Supprimer le médecin
            return ResponseEntity.noContent().build(); // Retourner 204 (sans contenu)
        } else {
            return ResponseEntity.notFound().build(); // Si non trouvé, retourner 404
        }
    }
}
