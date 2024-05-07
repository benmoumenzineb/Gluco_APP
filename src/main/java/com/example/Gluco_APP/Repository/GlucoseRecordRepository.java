package com.example.Gluco_APP.Repository;

import com.example.Gluco_APP.Model.GlucoseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlucoseRecordRepository extends JpaRepository<GlucoseRecord, Long> {
    // Trouver le premier enregistrement par patientId, ordonné par date descendante
    GlucoseRecord findFirstByPatientIdOrderByRecordedDateDesc(Long patientId);

    // Trouver tous les enregistrements par patientId, ordonnés par date descendante
    List<GlucoseRecord> findAllByPatientIdOrderByRecordedDateDesc(Long patientId);

    // Trouver tous les enregistrements, ordonnés par date descendante
    List<GlucoseRecord> findAllByOrderByRecordedDateDesc();
}
