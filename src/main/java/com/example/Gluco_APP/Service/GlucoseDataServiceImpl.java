package com.example.Gluco_APP.Service;

import com.example.Gluco_APP.Model.GlucoseRecord;
import com.example.Gluco_APP.Repository.GlucoseRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GlucoseDataServiceImpl implements GlucoseDataService {

    private final GlucoseRecordRepository glucoseRecordRepository;

    @Autowired
    public GlucoseDataServiceImpl(GlucoseRecordRepository glucoseRecordRepository) {
        this.glucoseRecordRepository = glucoseRecordRepository;
    }

    @Override
    public String processGlucoseData(double glucoseLevel) {
        // Traitement de données de glycémie
        return "Processed glucose data";
    }

    @Override
    public Map<String, Object> getCurrentGlucoseData() {
        // Trouver le premier enregistrement par date descendante
        GlucoseRecord glucoseRecord = glucoseRecordRepository.findFirstByPatientIdOrderByRecordedDateDesc(1L); // Exemple avec patientId = 1
        if (glucoseRecord == null) {
            throw new IllegalArgumentException("Aucune donnée de glycémie trouvée.");
        }

        Map<String, Object> glucoseData = new HashMap<>();
        glucoseData.put("glucoseLevel", glucoseRecord.getGlucoseLevel());
        glucoseData.put("recordedDate", glucoseRecord.getRecordedDate());
        return glucoseData;
    }

    @Override
    public List<Map<String, Object>> getCurrentGlucoseDataList() {
        // Récupérer tous les enregistrements de glycémie
        List<GlucoseRecord> glucoseRecords = glucoseRecordRepository.findAllByOrderByRecordedDateDesc();
        List<Map<String, Object>> glucoseDataList = new ArrayList<>();

        for (GlucoseRecord record : glucoseRecords) {
            Map<String, Object> glucoseData = new HashMap<>();
            glucoseData.put("glucoseLevel", record.getGlucoseLevel());
            glucoseData.put("recordedDate", record.getRecordedDate());
            glucoseDataList.add(glucoseData);
        }

        return glucoseDataList; // Retourne la liste de données
    }

    @Override
    public void saveGlucoseData(double glucoseLevel) {
        GlucoseRecord newRecord = new GlucoseRecord(glucoseLevel);
        glucoseRecordRepository.save(newRecord);
    }

    @Override
    public Map<String, Object> getCurrentGlucoseDataForPatient(String Id_Patient) {
        GlucoseRecord glucoseRecord = glucoseRecordRepository.findFirstByPatientIdOrderByRecordedDateDesc(Long.valueOf(Id_Patient));
        if (glucoseRecord == null) {
            throw new IllegalArgumentException("Aucune donnée de glycémie trouvée pour le patient avec l'ID : " + Id_Patient);
        }

        Map<String, Object> glucoseData = new HashMap<>();
        glucoseData.put("glucoseLevel", glucoseRecord.getGlucoseLevel());
        glucoseData.put("recordedDate", glucoseRecord.getRecordedDate());
        return glucoseData;
    }
}
