package com.forensic.engine.service.impl;

import com.forensic.engine.engines.*;
import com.forensic.engine.entity.AnalysisReport;
import com.forensic.engine.repository.AnalysisRepository;
import com.forensic.engine.service.AnalysisService;
import com.forensic.engine.config.DetectionWeights;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    private final BehaviorAnalyzer behaviorAnalyzer;
    private final LogicJumpDetector logicJumpDetector;
    private final AnalysisRepository repository;
    private final DetectionWeights weights;

    @Override
    public AnalysisReport analyze(String code) {
        double grammar = behaviorAnalyzer.analyzeLinguisticStyle(code);
        double logic = logicJumpDetector.checkComplexityLeap(code);
        
        double finalScore = (grammar * weights.getGrammarPerfection()) + 
                           (logic * weights.getLogicDensity());

        AnalysisReport report = new AnalysisReport();
        report.setCodeSnippet(code);
        report.setAiProbability(finalScore);
        report.setVerdict(finalScore > 0.7 ? "AI ORIGIN" : "HUMAN ORIGIN");
        return repository.save(report);
    }
}
