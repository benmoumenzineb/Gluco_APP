package com.example.Gluco_APP.Controller;

import com.example.Gluco_APP.Service.GlucoseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DataController {

    private final GlucoseDataService dataService;

    @Autowired
    public DataController(GlucoseDataService dataService) {
        this.dataService = dataService;
    }

    // Point de terminaison pour recevoir les niveaux de glucose via GET
    @GetMapping("/glucose")
    public ResponseEntity<String> receiveGlucoseData(@RequestParam("glucoseLevel") double glucoseLevel) {
        // Enregistrer les données de glucose dans la base de données
        System.out.println(glucoseLevel+ "*************************************************");
        dataService.saveGlucoseData(glucoseLevel);
        System.out.println(glucoseLevel+ "*************************************************");
        return ResponseEntity.ok("Niveau de glucose reçu : " + glucoseLevel);

    }


    // Point de terminaison pour obtenir des données de glucose actuelles via GET
    @GetMapping("/current")
    public ResponseEntity<Map<String, Object>> getCurrentGlucoseData() {
        // Obtenir les données de glucose actuelles depuis le service
        Map<String, Object> glucoseData = dataService.getCurrentGlucoseData();
        return ResponseEntity.ok(glucoseData);
    }

    // Point de terminaison pour enregistrer des données de glucose via POST

    }

