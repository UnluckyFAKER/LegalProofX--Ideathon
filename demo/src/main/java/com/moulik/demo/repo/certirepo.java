package com.moulik.demo.repo;

import com.moulik.demo.model.certiRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface certirepo extends JpaRepository<certiRecord,Long> {
    Optional<certiRecord> findByCertificateId(String certificateId);
    Optional<certiRecord> findByCertificateName(String certificateName);

}
