package com.forensic.engine.service.impl;

import com.forensic.engine.entity.AnalysisReport;
import com.forensic.engine.repository.AnalysisRepository;
import com.forensic.engine.service.AnalysisService;
import com.forensic.engine.config.DetectionWeights;
import com.forensic.engine.dto.RequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalysisServiceImpl implements AnalysisService {
    private final AnalysisRepository repository;
    private final DetectionWeights weights;

    @Override
    public AnalysisReport analyze(RequestDTO request) {
        String code = request.getCode();
        String lang = (request.getLanguage() != null) ? request.getLanguage().toLowerCase() : "generic";

        // 1. Fetch Dynamic Rules from Config (Zero Hard-Coding)
        DetectionWeights.LanguageRule rule = weights.getRules().getOrDefault(lang, weights.getRules().get("generic"));

        // 2. Logic: Calculate Linguistic Score (Formal Keywords)
        double grammar = calculatePatternMatch(code, rule.getFormalKeywords());
        
        // 3. Logic: Calculate Logic Density (Complexity Patterns)
        double logic = calculatePatternMatch(code, rule.getComplexityPatterns());

        // 4. Calculate Final Weighted Score
        double finalScore = (grammar * weights.getGrammarPerfection()) + 
                           (logic * weights.getLogicDensity());

        // 5. Build and Save the Report
        AnalysisReport report = new AnalysisReport();
        report.setCodeSnippet(code);
        report.setAiProbability(Math.min(finalScore, 1.0));
        report.setVerdict(finalScore > 0.7 ? "AI ORIGIN" : "HUMAN ORIGIN");
        
        return repository.save(report);
    }

    // Helper method to scan code without hard-coded strings
    private double calculatePatternMatch(String code, List<String> patterns) {
        if (patterns == null || patterns.isEmpty()) return 0.0;
        long matches = patterns.stream().filter(code::contains).count();
        // Returns a percentage (e.g., 0.5 if half the keywords are found)
        return (double) matches / patterns.size();
    }
}
