package com.mcp.controller;

import com.mcp.entity.Account;
import com.mcp.entity.CardTransaction;
import com.mcp.entity.CasaTransaction;
import com.mcp.entity.CustomerRelationship;
import com.mcp.model.McpMessage;
import com.mcp.repository.AccountRepository;
import com.mcp.repository.CardTransactionRepository;
import com.mcp.repository.CasaTransactionRepository;
import com.mcp.repository.CustomerRelationshipRepository;
import com.mcp.service.McpWebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mcp")
@RequiredArgsConstructor
public class McpController {

    private final AccountRepository accountRepository;
    private final CustomerRelationshipRepository customerRelationshipRepository;
    private final CasaTransactionRepository casaTransactionRepository;
    private final CardTransactionRepository cardTransactionRepository;
    private final McpWebSocketService mcpWebSocketService;

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to MCP Server! Available endpoints:\n" +
                "- GET /api/mcp/health\n" +
                "- POST /api/mcp/query");
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("MCP Server is running");
    }

    @PostMapping("/query")
    public ResponseEntity<McpMessage> sendQuery(@RequestBody McpMessage message) {
        mcpWebSocketService.sendMessageToLLM(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/accounts/count")
    public ResponseEntity<String> getAccountsCount() {
        long count = accountRepository.count();
        return ResponseEntity.ok("Number of records in accounts: " + count);
    }

    @GetMapping("/customer-relationships/count")
    public ResponseEntity<String> getCustomerRelationshipsCount() {
        long count = customerRelationshipRepository.count();
        return ResponseEntity.ok("Number of records in customer_relationships: " + count);
    }

    @GetMapping("/casa-transactions/count")
    public ResponseEntity<String> getCasaTransactionsCount() {
        long count = casaTransactionRepository.count();
        return ResponseEntity.ok("Number of records in casa_transactions: " + count);
    }

    @GetMapping("/card-transactions/count")
    public ResponseEntity<String> getCardTransactionsCount() {
        long count = cardTransactionRepository.count();
        return ResponseEntity.ok("Number of records in card_transactions: " + count);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @GetMapping("/customer-relationships")
    public ResponseEntity<List<CustomerRelationship>> getAllCustomerRelationships() {
        return ResponseEntity.ok(customerRelationshipRepository.findAll());
    }

    @GetMapping("/casa-transactions")
    public ResponseEntity<List<CasaTransaction>> getAllCasaTransactions() {
        return ResponseEntity.ok(casaTransactionRepository.findAll());
    }

    @GetMapping("/card-transactions")
    public ResponseEntity<List<CardTransaction>> getAllCardTransactions() {
        return ResponseEntity.ok(cardTransactionRepository.findAll());
    }

    @GetMapping("/database-connection")
    public ResponseEntity<String> getDatabaseConnectionDetails() {
        return ResponseEntity.ok("Successfully connected to PostgreSQL database 'mcp_db' with the following details:\n" +
                "URL: jdbc:postgresql://localhost:5432/mcp_db\n" +
                "Username: postgres\n" +
                "Password: lokit@181903");
    }

    @GetMapping("/status/{messageId}")
    public ResponseEntity<McpMessage> getMessageStatus(@PathVariable String messageId) {
        // Implementation for checking message status
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
} 