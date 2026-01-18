package com.forensic.engine.service;
import com.forensic.engine.dto.RequestDTO;
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
        String codeSnippet = "int x = 10;";
    
        // 1. Logic: Wrap the string in the required object
        RequestDTO request = new RequestDTO();
        request.setSnippet(codeSnippet); // Note: verify if the field is 'snippet', 'code', or 'content'

        when(repository.save(any(AnalysisReport.class))).thenReturn(new AnalysisReport());

        // 2. Logic: Pass the object, not the string
        service.analyze(request);

        verify(repository, times(1)).save(any(AnalysisReport.class));
    }
}
