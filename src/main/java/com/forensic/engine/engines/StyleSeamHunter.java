package com.forensic.engine.engines;

import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class StyleSeamHunter {

    public double detectStyleDrift(String code) {
        String[] lines = code.split("\n");
        if (lines.length < 6) return 0.0;

        // Compare the first half of the code with the second half
        double firstHalfDensity = calculateDensity(Arrays.copyOfRange(lines, 0, lines.length / 2));
        double secondHalfDensity = calculateDensity(Arrays.copyOfRange(lines, lines.length / 2, lines.length));

        // If the densities are wildly different, it suggests a "pasted" AI block
        double drift = Math.abs(firstHalfDensity - secondHalfDensity);
        return drift > 0.5 ? 0.8 : 0.0; 
    }

    private double calculateDensity(String[] lines) {
        if (lines.length == 0) return 0;
        long tokens = Arrays.stream(lines).filter(l -> l.contains(".") || l.contains("(")).count();
        return (double) tokens / lines.length;
    }
}
