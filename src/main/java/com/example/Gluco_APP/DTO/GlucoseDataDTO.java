package com.example.Gluco_APP.DTO;

public class GlucoseDataDTO {
    private Long patientId;
    private Double glucoseLevel;
    private String recordedDate; // Représenté comme String pour simplicité

    public GlucoseDataDTO(Long patientId, Double glucoseLevel, String recordedDate) {
        this.patientId = patientId;
        this.glucoseLevel = glucoseLevel;
        this.recordedDate = recordedDate;
    }

    // Getters et Setters
    public Long getPatientId() {
        return patientId;
    }

    public Double getGlucoseLevel() {
        return glucoseLevel;
    }

    public String getRecordedDate() {
        return recordedDate;
    }
}
