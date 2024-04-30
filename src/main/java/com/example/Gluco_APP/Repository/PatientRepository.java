package com.example.Gluco_APP.Repository; // Assurez-vous que le package est correct

import com.example.Gluco_APP.Model.Patient; // Importation de l'entité
import org.springframework.data.jpa.repository.JpaRepository; // Importation de `JpaRepository`
import org.springframework.stereotype.Repository; // Importation de l'annotation `@Repository`

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> { // Spécification du type générique
    Patient findByIdPatient(String idPatient); // Correction du nom de la méthode
}

