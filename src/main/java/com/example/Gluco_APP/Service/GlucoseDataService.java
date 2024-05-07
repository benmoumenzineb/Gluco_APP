package com.example.Gluco_APP.Service;

import java.util.List;
import java.util.Map;

public interface GlucoseDataService {
    // Reçoit des données de glycémie provenant d'un glucometre
    String processGlucoseData(double glucoseLevel);

    // Récupère les données de glycémie actuelles
    Map<String, Object> getCurrentGlucoseData();

    // Récupère les données actuelles de glycémie pour un patient donné
    Map<String, Object> getCurrentGlucoseDataForPatient(String Id_Patient);

    // Enregistre les données de glycémie dans la base de données
    void saveGlucoseData(double glucoseLevel);

    // Nouvelle méthode pour récupérer plusieurs données de glycémie
    List<Map<String, Object>> getCurrentGlucoseDataList();
}
