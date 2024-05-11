package com.leaseforlove.tagsmanagementservice.application.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ErrorMessageDTO {
    private String type;
    private String message;
    private String uri;
    private Map<String, Object> details;
}
