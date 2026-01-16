package com.forensic.engine.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "forensics.weights")
public class DetectionWeights {
    private double grammarPerfection;
    private double logicDensity;
}
