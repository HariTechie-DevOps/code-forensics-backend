package com.forensic.engine.engines;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class BehaviorAnalyzer {
    // Detects AI-typical grammatical perfection (e.g., isUserAuthenticated)
    public double analyzeLinguisticStyle(String code) {
        long formalCount = Pattern.compile("validated|processed|calculated|verified").matcher(code).results().count();
        return Math.min(formalCount * 0.2, 1.0);
    }
}
