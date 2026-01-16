package com.forensic.engine.engines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BehaviorAnalyzerTest {

    private BehaviorAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new BehaviorAnalyzer();
    }

    @Test
    void testAIStyleNaming_ShouldReturnHighProbability() {
        // Sample with "Too Perfect" naming (AI Footprint)
        String aiCode = "public void processUserAuthenticationData() {\n" + 
                        "    boolean isUserValidated = true;\n" +
                        "    int currentRunningTotal = 100;\n" +
                        "}";
        
        double score = analyzer.analyzeLinguisticStyle(aiCode);
        
        // Assert that the score is high because it contains formal words like 'validated' and 'processed'
        assertTrue(score > 0.3, "AI-style code should have a higher forensic score");
    }

    @Test
    void testHumanStyleNaming_ShouldReturnLowProbability() {
        // Sample with "Shorthand" naming (Human Expert Footprint)
        String humanCode = "public void auth() {\n" + 
                           "    boolean ok = true;\n" +
                           "    int sum = 100;\n" +
                           "}";
        
        double score = analyzer.analyzeLinguisticStyle(humanCode);
        
        // Assert that the score is near zero for casual human shorthand
        assertEquals(0.0, score, "Human shorthand should not trigger AI grammar flags");
    }
}
