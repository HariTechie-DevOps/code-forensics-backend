package com.forensic.engine.repository;

import com.forensic.engine.entity.AnalysisReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<AnalysisReport, Long> {
    // Standard CRUD (Save, Find, Delete) is handled by JpaRepository
}
