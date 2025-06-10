package com.mcp.controller;

import com.mcp.model.Account;
import com.mcp.model.CardTransaction;
import com.mcp.model.CasaTransaction;
import com.mcp.model.CustomerRelationship;
import com.mcp.repository.AccountRepository;
import com.mcp.repository.CardTransactionRepository;
import com.mcp.repository.CasaTransactionRepository;
import com.mcp.repository.CustomerRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mcp")
public class McpController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRelationshipRepository customerRelationshipRepository;
    @Autowired
    private CasaTransactionRepository casaTransactionRepository;
    @Autowired
    private CardTransactionRepository cardTransactionRepository;

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
    public ResponseEntity<?> handleQuery(@RequestBody String query) {
        // TODO: Implement query handling logic
        return ResponseEntity.ok("Query received: " + query);
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

    @GetMapping("/database-connection")
    public ResponseEntity<String> getDatabaseConnectionDetails() {
        return ResponseEntity.ok("Successfully connected to PostgreSQL database 'mcp_db' with the following details:\n" +
                "URL: jdbc:postgresql://localhost:5432/mcp_db\n" +
                "Username: postgres\n" +
                "Password: lokit@181903");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
    }
} 