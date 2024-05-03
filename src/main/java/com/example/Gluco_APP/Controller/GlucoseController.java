package com.example.Gluco_APP.Controller;

import com.example.Gluco_APP.Model.GlucoseRecord;
import com.example.Gluco_APP.Model.Patient;
import com.example.Gluco_APP.Repository.GlucoseRecordRepository;
import com.example.Gluco_APP.Service.GlucoseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/glucose")
@CrossOrigin(origins = "http://localhost:8088")
public class GlucoseController {

    @Autowired
    private GlucoseRecordRepository glucoseRecordRepository;

    @Autowired
    private GlucoseDataService glucoseDataService;

    // Récupère le niveau de glucose le plus récent pour un patient donné
    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> getCurrentGlucoseDataForPatient(
            @RequestParam("patientId") Long patientId) {
        try {
            GlucoseRecord record = glucoseRecordRepository.findFirstByPatientIdOrderByRecordedDateDesc(patientId);
            if (record == null) {
                throw new IllegalArgumentException("Aucune donnée de glycémie trouvée pour le patient avec l'ID : " + patientId);
            }

            Map<String, Object> glucoseData = new HashMap<>();
            glucoseData.put("glucoseLevel", record.getGlucoseLevel());
            glucoseData.put("recordedDate", record.getRecordedDate());
            glucoseData.put("patientId", patientId);

            return ResponseEntity.ok(glucoseData); // Retourne les données si le record est trouvé
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage())); // Retourne une erreur si aucune donnée n'est trouvée
        }
    }

    // Enregistre un nouveau niveau de glucose pour un patient donné
    @PostMapping("/record")
    public ResponseEntity<String> recordGlucoseLevel(
            @RequestParam("patientId") Long patientId,
            @RequestParam("glucoseLevel") double glucoseLevel) {
        // Crée un nouveau record de glucose
        Patient patient = new Patient(); // Vous devrez définir comment obtenir un patient
        patient.setIdPatient(String.valueOf(patientId));

        GlucoseRecord newRecord = new GlucoseRecord(glucoseLevel, patient);

        glucoseRecordRepository.save(newRecord); // Sauvegarde le record dans le repository

        return ResponseEntity.ok("Niveau de glucose enregistré avec succès."); // Retourne un message de succès
    }

    // Obtenir des niveaux de glucose pour un patient donné
    @GetMapping("/history")
    public ResponseEntity<List<GlucoseRecord>> getGlucoseHistory(
            @RequestParam("patientId") Long patientId) {
        List<GlucoseRecord> records = glucoseRecordRepository.findAllByPatientIdOrderByRecordedDateDesc(patientId);

        return ResponseEntity.ok(records); // Retourne l'historique de glucose pour un patient
    }
}
