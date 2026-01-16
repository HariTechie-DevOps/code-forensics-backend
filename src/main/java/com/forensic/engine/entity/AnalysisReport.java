package com.forensic.engine.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class AnalysisReport {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob private String codeSnippet;
    private Double aiProbability;
    private String verdict;
    private LocalDateTime timestamp = LocalDateTime.now();
}
