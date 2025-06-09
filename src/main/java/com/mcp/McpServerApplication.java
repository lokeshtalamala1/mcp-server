package com.mcp;

import com.mcp.config.DatabaseConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class McpServerApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(McpServerApplication.class, args);
        
        // Test database connection
        DatabaseConfig dbConfig = context.getBean(DatabaseConfig.class);
        dbConfig.testConnection();
    }
} 