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
        // 1. Create the DTO
        com.forensic.engine.dto.RequestDTO request = new com.forensic.engine.dto.RequestDTO();
        request.setSnippet("int x = 10;"); // Assuming 'snippet' is the field name
    
        when(repository.save(any(AnalysisReport.class))).thenReturn(new AnalysisReport());

        // 2. Pass the DTO instead of the String
        service.analyze(request);

        verify(repository, times(1)).save(any(AnalysisReport.class));
    }
}
