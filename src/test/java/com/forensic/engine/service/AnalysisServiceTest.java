package com.forensic.engine.service;

import com.forensic.engine.entity.AnalysisReport;
import com.forensic.engine.repository.AnalysisRepository;
import com.forensic.engine.service.impl.AnalysisServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnalysisServiceTest {

    @Mock
    private AnalysisRepository repository;

    @InjectMocks
    private AnalysisServiceImpl service;

    @Test
    void testServiceSavesToDatabase() {
        String code = "int x = 10;";
        
        // Mock the repository to return a report when saved
        when(repository.save(any(AnalysisReport.class))).thenReturn(new AnalysisReport());

        service.analyze(code);

        // Verify that the repository's save method was called exactly once
        verify(repository, times(1)).save(any(AnalysisReport.class));
    }
}
