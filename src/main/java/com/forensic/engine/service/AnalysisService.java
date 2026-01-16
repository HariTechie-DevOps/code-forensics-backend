package com.forensic.engine.service;

import com.forensic.engine.entity.AnalysisReport;

public interface AnalysisService {
    /**
     * Analyzes the provided code using behavioral forensics 
     * and saves the result to the MySQL database.
     */
    AnalysisReport analyze(String code);
}
