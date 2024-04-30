package com.example.Gluco_APP.Repository;
import com.example.Gluco_APP.Model.GlucoseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlucoseRecordRepository extends JpaRepository<GlucoseRecord, Long> {
    // Correction du nom de la propriété pour éviter l'erreur
    GlucoseRecord findFirstByPatientIdOrderByRecordedDateDesc(Long glucoseLevel);
}

