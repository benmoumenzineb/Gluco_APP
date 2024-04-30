package com.example.Gluco_APP.Service;

import java.util.Optional; // Pour gérer les valeurs optionnelles
import java.util.List; // Pour gérer les collections
import com.example.Gluco_APP.Model.Userr; // Importation de l'entité

// Suppression de @Service, car il ne s'agit plus d'une classe de service
public interface UserrService {

    // Signature des méthodes
    List<Userr> getAllUsers(); // Obtenir tous les utilisateurs

    Optional<Userr> getUserById(Long id); // Obtenir un utilisateur par ID

    Userr createUser(Userr user); // Créer un nouvel utilisateur

    Userr updateUser(Long id, Userr userDetails); // Mettre à jour un utilisateur

    void deleteUser(Long id); // Supprimer un utilisateur par ID
}

