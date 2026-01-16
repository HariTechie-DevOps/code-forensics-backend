package com.forensic.engine.controller;

import com.forensic.engine.entity.AnalysisReport;
import com.forensic.engine.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/forensics")
@RequiredArgsConstructor
public class CodeAnalysisController {
    private final AnalysisService service;

    @PostMapping("/analyze")
    public AnalysisReport postAnalysis(@RequestBody String code) {
        return service.analyze(code);
    }
}
