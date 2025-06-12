package com.mcp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class McpConfig {
    
    @Bean(name = "mcpTaskExecutor")
    public Executor mcpTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("mcp-");
        executor.initialize();
        return executor;
    }
    
    @Bean
    public McpProperties mcpProperties() {
        return new McpProperties();
    }
    
    public static class McpProperties {
        private int maxResponseTime = 5000; // 5 seconds
        private int maxRetries = 3;
        private boolean enableCaching = true;
        private int cacheTimeout = 300; // 5 minutes
        
        // Getters and setters
        public int getMaxResponseTime() {
            return maxResponseTime;
        }
        
        public void setMaxResponseTime(int maxResponseTime) {
            this.maxResponseTime = maxResponseTime;
        }
        
        public int getMaxRetries() {
            return maxRetries;
        }
        
        public void setMaxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
        }
        
        public boolean isEnableCaching() {
            return enableCaching;
        }
        
        public void setEnableCaching(boolean enableCaching) {
            this.enableCaching = enableCaching;
        }
        
        public int getCacheTimeout() {
            return cacheTimeout;
        }
        
        public void setCacheTimeout(int cacheTimeout) {
            this.cacheTimeout = cacheTimeout;
        }
    }
} 