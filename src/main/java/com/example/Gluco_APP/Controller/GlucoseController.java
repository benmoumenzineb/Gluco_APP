package com.example.Gluco_APP.Controller;

import com.example.Gluco_APP.DTO.GlucoseDataDTO;
import com.example.Gluco_APP.Model.GlucoseRecord;
import com.example.Gluco_APP.Repository.GlucoseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/glucose")
@CrossOrigin(origins = "http://localhost:8088")
public class GlucoseController {

    @Autowired
    private GlucoseRecordRepository glucoseRecordRepository;

    // Obtenir le niveau de glucose le plus récent pour un patient donné
    @GetMapping("/current")
    public ResponseEntity<GlucoseDataDTO> getCurrentGlucoseData(
            @RequestParam("patientId") Long patientId) {
        GlucoseRecord latestRecord = glucoseRecordRepository.findFirstByPatientIdOrderByRecordedDateDesc(patientId);

        if (latestRecord == null) {
            return ResponseEntity.badRequest().body(null); // Aucun record trouvé
        }

        GlucoseDataDTO dto = new GlucoseDataDTO(
                patientId,
                latestRecord.getGlucoseLevel(),
                latestRecord.getRecordedDate().toString() // Convertir en String
        );

        return ResponseEntity.ok(dto); // Retourne le DTO
    }

    // Obtenir l'historique des niveaux de glucose pour un patient donné
    @GetMapping("/history")
    public ResponseEntity<List<GlucoseDataDTO>> getGlucoseHistory(
            @RequestParam("patientId") Long patientId) {
        List<GlucoseRecord> records = glucoseRecordRepository.findAllByPatientIdOrderByRecordedDateDesc(patientId);

        if (records.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Si aucun record n'est trouvé
        }

        List<GlucoseDataDTO> dtos = new ArrayList<>();
        for (GlucoseRecord record : records) {
            dtos.add(new GlucoseDataDTO(
                    patientId,
                    record.getGlucoseLevel(),
                    record.getRecordedDate().toString() // Convertir en String
            ));
        }

        return ResponseEntity.ok(dtos); // Retourne la liste de DTOs
    }
}
