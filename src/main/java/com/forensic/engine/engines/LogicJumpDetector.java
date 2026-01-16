package com.forensic.engine.engines;

import org.springframework.stereotype.Component;

@Component
public class LogicJumpDetector {
    public double checkComplexityLeap(String code) {
        // AI often uses optimal "One-Liners" like complex Streams instantly
        return (code.contains(".stream()") && code.contains(".collect(")) ? 0.9 : 0.1;
    }
}
