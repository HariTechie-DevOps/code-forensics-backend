package com.forensic.engine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "forensics") // Broadened to capture everything
public class DetectionWeights {
    
    // Weights for the final math formula
    private double grammarPerfection;
    private double logicDensity;
    private double structuralStyle;
    
    // A Map where key = "java", "python", or "cpp"
    // This allows the engine to work for ANY language added to YAML
    private Map<String, LanguageRule> rules;

    @Data
    public static class LanguageRule {
        private List<String> formalKeywords;   // e.g., "validated", "serialized"
        private List<String> complexityPatterns; // e.g., ".stream()", "lambda"
    }
}
