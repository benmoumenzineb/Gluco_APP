package com.example.Gluco_APP.Repository;
import com.example.Gluco_APP.Model.Doctor;
import com.example.Gluco_APP.Model.Userr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<Userr, Long> {

    Userr findByTelephone(String telephone);
    Doctor findDoctorByTelephone(String telephone);
}