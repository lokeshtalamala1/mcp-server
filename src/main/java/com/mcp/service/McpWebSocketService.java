package com.mcp.service;

import com.mcp.model.McpMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class McpWebSocketService {
    private final SimpMessagingTemplate messagingTemplate;
    private final AccountService accountService;
    private final CasaTransactionService casaTransactionService;
    private final CardTransactionService cardTransactionService;
    private final CustomerRelationshipService customerRelationshipService;

    public void handleMcpMessage(McpMessage message) {
        try {
            message.setStatus("PROCESSING");
            message.setTimestamp(LocalDateTime.now());
            
            // Process the message based on its type
            switch (message.getType()) {
                case "QUERY":
                    processQuery(message);
                    break;
                case "RESPONSE":
                    processResponse(message);
                    break;
                case "ERROR":
                    processError(message);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown message type: " + message.getType());
            }
            
            message.setStatus("COMPLETED");
        } catch (Exception e) {
            log.error("Error processing MCP message", e);
            message.setStatus("FAILED");
            message.setContent("Error processing message: " + e.getMessage());
        }
        
        // Send the response back to the client
        messagingTemplate.convertAndSend("/topic/mcp-responses", message);
    }

    private void processQuery(McpMessage message) {
        // Extract query parameters from context
        String queryType = (String) message.getContext().get("queryType");
        String customerId = (String) message.getContext().get("customerId");
        
        // Process different types of queries
        switch (queryType) {
            case "ACCOUNT_BALANCE":
                processAccountBalanceQuery(message, customerId);
                break;
            case "TRANSACTION_HISTORY":
                processTransactionHistoryQuery(message, customerId);
                break;
            case "SPENDING_ANALYSIS":
                processSpendingAnalysisQuery(message, customerId);
                break;
            case "CUSTOMER_RELATIONSHIPS":
                processCustomerRelationshipsQuery(message, customerId);
                break;
            default:
                throw new IllegalArgumentException("Unknown query type: " + queryType);
        }
    }

    private void processAccountBalanceQuery(McpMessage message, String customerId) {
        var accounts = accountService.getActiveAccountsByCustomerId(customerId);
        message.setContent("Account balances retrieved successfully");
        message.getMetadata().put("accounts", accounts);
    }

    private void processTransactionHistoryQuery(McpMessage message, String customerId) {
        // Implementation for transaction history query
        // This would combine both CASA and card transactions
    }

    private void processSpendingAnalysisQuery(McpMessage message, String customerId) {
        // Implementation for spending analysis
        // This would analyze card transactions by category
    }

    private void processCustomerRelationshipsQuery(McpMessage message, String customerId) {
        var relationships = customerRelationshipService.getActiveRelationshipsByCustomerId(customerId);
        message.setContent("Customer relationships retrieved successfully");
        message.getMetadata().put("relationships", relationships);
    }

    private void processResponse(McpMessage message) {
        // Handle response messages from the LLM
        log.info("Received response from LLM: {}", message.getContent());
    }

    private void processError(McpMessage message) {
        // Handle error messages
        log.error("Error in MCP communication: {}", message.getContent());
    }

    public void sendMessageToLLM(McpMessage message) {
        message.setMessageId(UUID.randomUUID().toString());
        message.setTimestamp(LocalDateTime.now());
        messagingTemplate.convertAndSend("/topic/mcp-queries", message);
    }
} 