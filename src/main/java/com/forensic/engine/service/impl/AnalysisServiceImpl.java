package com.forensic.engine.service.impl;

import com.forensic.engine.engines.*; // Corrected package name
import com.forensic.engine.entity.AnalysisReport;
import com.forensic.engine.repository.AnalysisRepository;
import com.forensic.engine.service.AnalysisService;
import com.forensic.engine.config.DetectionWeights;
import com.forensic.engine.dto.RequestDTO; // Now using DTO
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    private final BehaviorAnalyzer behaviorAnalyzer;
    private final LogicJumpDetector logicJumpDetector;
    private final StyleSeamHunter styleSeamHunter;
    private final AnalysisRepository repository;
    private final DetectionWeights weights;

    @Override
    public AnalysisReport analyze(RequestDTO request) {
        String code = request.getCode();
        
        // 1. Run individual detections
        double grammar = behaviorAnalyzer.analyzeLinguisticStyle(code);
        double logic = logicJumpDetector.checkComplexityLeap(code);
        double drift = styleSeamHunter.detectStyleDrift(code);
        
        // 2. Calculate Weighted Score
        // We divide by 3 (or adjust weights) to keep the score between 0.0 and 1.0
        double finalScore = (grammar * weights.getGrammarPerfection()) + 
                           (logic * weights.getLogicDensity()) +
                           (drift * 0.2); // Added drift with a fixed weight

        // 3. Logic: Build the Forensic explanation
        StringBuilder details = new StringBuilder();
        if (grammar > 0.5) details.append("High use of formal variable naming. ");
        if (logic > 0.8) details.append("Complex 'One-Liner' logic detected. ");
        if (drift > 0.5) details.append("Significant style inconsistency (possible copy-paste). ");

        // 4. Persistence
        AnalysisReport report = new AnalysisReport();
        report.setCodeSnippet(code);
        report.setAiProbability(Math.min(finalScore, 1.0));
        report.setVerdict(finalScore > 0.7 ? "AI ORIGIN" : "HUMAN ORIGIN");
        // Note: You may want to add 'forensicDetails' to your Entity as well
        
        return repository.save(report);
    }
}
