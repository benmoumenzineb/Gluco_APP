package com.example.Gluco_APP.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import com.example.Gluco_APP.Model.Userr;
import com.example.Gluco_APP.Repository.UserRepository;

@Service // Cette classe est un service Spring
public class UserrServiceImpl implements UserrService { // Implémente l'interface

    private final UserRepository userRepository; // Dépendance vers le repository

    @Autowired
    public UserrServiceImpl(UserRepository userRepository) { // Injection du repository
        this.userRepository = userRepository;
    }

    @Override
    public List<Userr> getAllUsers() { // Implémentation de la méthode
        return userRepository.findAll();
    }

    @Override
    public Optional<Userr> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Userr createUser(Userr user) {
        return userRepository.save(user);
    }

    @Override
    public Userr updateUser(Long id, Userr userDetails) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setNom(userDetails.getNom());
                    existingUser.setPrenom(userDetails.getPrenom());
                    existingUser.setMotDePasse(userDetails.getMotDePasse());
                    existingUser.setTelephone(userDetails.getTelephone());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID: " + id));
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec ID: " + id);
        }
    }
}


