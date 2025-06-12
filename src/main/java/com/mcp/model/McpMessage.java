package com.mcp.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class McpMessage {
    private String messageId;
    private String type;  // QUERY, RESPONSE, ERROR
    private String content;
    private Map<String, Object> context;
    private LocalDateTime timestamp;
    private String status;  // PENDING, PROCESSING, COMPLETED, FAILED
    private Map<String, Object> metadata;
} 