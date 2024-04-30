package com.example.Gluco_APP.Repository; // Assurez-vous que le package est correct

import com.example.Gluco_APP.Model.Doctor; // Importations appropriées
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> { // Assurez-vous que le type générique est correct
    // Correction des types des méthodes
    Optional<Doctor> findById(Long id); // Utilisez `Long` au lieu de `String`
}

