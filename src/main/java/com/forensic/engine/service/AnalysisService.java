package com.forensic.engine.service;

import com.forensic.engine.entity.AnalysisReport;
import com.forensic.engine.dto.RequestDTO; // Missing Import Added

public interface AnalysisService {
    /**
     * Analyzes the provided code using behavioral forensics 
     * and saves the result to the MySQL database.
     * Changed from String to RequestDTO to support multi-language logic.
     */
    AnalysisReport analyze(RequestDTO request); 
}
