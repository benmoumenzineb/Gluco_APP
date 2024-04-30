package com.example.Gluco_APP.Service;

import com.example.Gluco_APP.Model.GlucoseRecord;
import com.example.Gluco_APP.Repository.GlucoseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GlucoseDataServiceImpl implements GlucoseDataService {

    private GlucoseRecordRepository glucoseRecordRepository;

    @Autowired
    public GlucoseDataServiceImpl(GlucoseRecordRepository glucoseRecordRepository) {
        this.glucoseRecordRepository = glucoseRecordRepository;
    }

    @Override
    public String processGlucoseData(double glucoseLevel) {
        // Implémentation de la logique de traitement de la glycémie
        return null;
    }

    @Override
    public Map<String, Object> getCurrentGlucoseData() {
        // Implémentation pour obtenir des données de glycémie actuelles
        return null;
    }

    @Override
    public Map<String, Object> getCurrentGlucoseDataForPatient(String Id_Patient) {
        // Récupérer les données de glycémie les plus récentes pour un patient donné
        GlucoseRecord glucoseRecord = glucoseRecordRepository.findFirstByPatientIdOrderByRecordedDateDesc(Long.valueOf(Id_Patient));
        if (glucoseRecord == null) {
            throw new IllegalArgumentException("Aucune donnée de glycémie trouvée pour le patient avec l'ID : " + Id_Patient);
        }

        Map<String, Object> glucoseData = new HashMap<>();
        glucoseData.put("glucoseLevel", glucoseRecord.getGlucoseLevel());
        glucoseData.put("recorded_Date", glucoseRecord.getRecordedDate());
        glucoseData.put("Id_Patient", Id_Patient);

        return glucoseData;
    }

    @Override
    public void saveGlucoseData(double glucoseLevel) {
        // Pour enregistrer les données de glycémie, il faut créer un nouvel objet GlucoseRecord
        GlucoseRecord newRecord = new GlucoseRecord(glucoseLevel);
        System.out.println("Created new record with glucose level: " + newRecord.getGlucoseLevel());
        glucoseRecordRepository.save(newRecord); // Sauvegarder l'objet GlucoseRecord
    }
}
