package com.example.Gluco_APP.Controller;

import com.example.Gluco_APP.Model.Userr; // Importation de l'entité
import com.example.Gluco_APP.Repository.UserRepository; // Importation du repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // Pour renvoyer des listes d'utilisateurs
import java.util.Optional; // Pour gérer des valeurs optionnelles

@RestController
@RequestMapping("/api/users") // Chemin de base pour les routes liées aux utilisateurs
public class UserrController {

    private final UserRepository userRepository; // Injection du repository

    @Autowired
    public UserrController(UserRepository userRepository) {
        this.userRepository = userRepository; // Assignation du repository
    }

    @GetMapping("/") // Point de terminaison pour obtenir tous les utilisateurs
    public ResponseEntity<List<Userr>> getAllUsers() {
        List<Userr> users = userRepository.findAll(); // Obtenir tous les utilisateurs
        return ResponseEntity.ok(users); // Retourner la liste des utilisateurs
    }

    @GetMapping("/{id}") // Point de terminaison pour obtenir un utilisateur par ID
    public ResponseEntity<Userr> getUserById(@PathVariable Long id) {
        Optional<Userr> user = userRepository.findById(id); // Rechercher l'utilisateur par ID
        return user.map(ResponseEntity::ok) // Si trouvé, retourner l'utilisateur
                .orElse(ResponseEntity.notFound().build()); // Si non trouvé, retourner 404
    }

    @PostMapping("/") // Point de terminaison pour créer un nouvel utilisateur
    public ResponseEntity<Userr> createUser(@RequestBody Userr user) {
        Userr savedUser = userRepository.save(user); // Sauvegarder l'utilisateur
        return ResponseEntity.status(201).body(savedUser); // Retourner le nouvel utilisateur avec code 201
    }

    @PutMapping("/{id}") // Point de terminaison pour mettre à jour un utilisateur par ID
    public ResponseEntity<Userr> updateUser(@PathVariable Long id, @RequestBody Userr userDetails) {
        return userRepository.findById(id) // Rechercher l'utilisateur par ID
                .map(existingUser -> {
                    // Mettre à jour les détails de l'utilisateur
                    existingUser.setNom(userDetails.getNom());
                    existingUser.setPrenom(userDetails.getPrenom());
                    existingUser.setMotDePasse(userDetails.getMotDePasse());
                    existingUser.setTelephone(userDetails.getTelephone());
                    Userr updatedUser = userRepository.save(existingUser); // Sauvegarder les changements
                    return ResponseEntity.ok(updatedUser); // Retourner l'utilisateur mis à jour
                })
                .orElse(ResponseEntity.notFound().build()); // Si non trouvé, retourner 404
    }

    @DeleteMapping("/{id}") // Point de terminaison pour supprimer un utilisateur par ID
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) { // Si l'utilisateur existe
            userRepository.deleteById(id); // Supprimer l'utilisateur
            return ResponseEntity.noContent().build(); // Retourner 204 (sans contenu)
        } else {
            return ResponseEntity.notFound().build(); // Si non trouvé, retourner 404
        }
    }
}

