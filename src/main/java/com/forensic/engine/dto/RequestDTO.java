// RequestDTO.java
package com.forensic.engine.dto;
import lombok.Data;

@Data
public class RequestDTO {
    private String code;
    private String language; // e.g., "java", "python"
}

// ResponseDTO.java
package com.forensic.engine.dto;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class ResponseDTO {
    private double aiProbability;
    private String verdict;
    private String forensicDetails; // Detailed explanation of why
}
