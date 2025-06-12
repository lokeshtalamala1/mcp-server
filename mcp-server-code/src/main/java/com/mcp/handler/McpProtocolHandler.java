package com.mcp.handler;

import com.mcp.entity.Account;
import com.mcp.entity.CardTransaction;
import com.mcp.entity.CasaTransaction;
import com.mcp.entity.CustomerRelationship;
import com.mcp.service.AccountService;
import com.mcp.service.CardTransactionService;
import com.mcp.service.CasaTransactionService;
import com.mcp.service.CustomerRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class McpProtocolHandler {
    private final AccountService accountService;
    private final CasaTransactionService casaTransactionService;
    private final CardTransactionService cardTransactionService;
    private final CustomerRelationshipService customerRelationshipService;

    public Map<String, Object> processQuery(String queryType, Map<String, Object> context) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            switch (queryType) {
                case "ACCOUNT_BALANCE":
                    response = handleAccountBalanceQuery(context);
                    break;
                case "TRANSACTION_HISTORY":
                    response = handleTransactionHistoryQuery(context);
                    break;
                case "SPENDING_ANALYSIS":
                    response = handleSpendingAnalysisQuery(context);
                    break;
                case "CUSTOMER_RELATIONSHIPS":
                    response = handleCustomerRelationshipsQuery(context);
                    break;
                default:
                    response.put("error", "Unknown query type: " + queryType);
            }
        } catch (Exception e) {
            response.put("error", "Error processing query: " + e.getMessage());
        }
        
        return response;
    }

    private Map<String, Object> handleAccountBalanceQuery(Map<String, Object> context) {
        String customerId = (String) context.get("customerId");
        List<Account> accounts = accountService.getActiveAccountsByCustomerId(customerId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("accounts", accounts);
        response.put("totalBalance", calculateTotalBalance(accounts));
        response.put("accountCount", accounts.size());
        
        return response;
    }

    private Map<String, Object> handleTransactionHistoryQuery(Map<String, Object> context) {
        String customerId = (String) context.get("customerId");
        LocalDateTime startDate = parseDateTime((String) context.get("startDate"));
        LocalDateTime endDate = parseDateTime((String) context.get("endDate"));
        
        // Get CASA transactions
        List<CasaTransaction> casaTransactions = casaTransactionService.getTransactionsByDateRange(
            customerId, startDate, endDate);
        
        // Get card transactions
        List<CardTransaction> cardTransactions = cardTransactionService.getTransactionsByDateRange(
            customerId, startDate, endDate);
        
        Map<String, Object> response = new HashMap<>();
        response.put("casaTransactions", casaTransactions);
        response.put("cardTransactions", cardTransactions);
        response.put("totalTransactions", casaTransactions.size() + cardTransactions.size());
        
        return response;
    }

    private Map<String, Object> handleSpendingAnalysisQuery(Map<String, Object> context) {
        String customerId = (String) context.get("customerId");
        String period = (String) context.get("period");
        
        LocalDateTime startDate = calculateStartDate(period);
        LocalDateTime endDate = LocalDateTime.now();
        
        Map<String, Object> response = new HashMap<>();
        
        // Get spending by category
        Map<String, BigDecimal> spendingByCategory = cardTransactionService.getSpendingByCategory(
            customerId, startDate, endDate);
        
        // Convert BigDecimal to Double for response
        Map<String, Double> spendingByCategoryDouble = new HashMap<>();
        spendingByCategory.forEach((category, amount) -> 
            spendingByCategoryDouble.put(category, amount.doubleValue()));
        
        response.put("spendingByCategory", spendingByCategoryDouble);
        response.put("totalSpending", calculateTotalSpending(spendingByCategory));
        response.put("period", period);
        
        return response;
    }

    private Map<String, Object> handleCustomerRelationshipsQuery(Map<String, Object> context) {
        String customerId = (String) context.get("customerId");
        List<CustomerRelationship> relationships = 
            customerRelationshipService.getActiveRelationshipsByCustomerId(customerId);
        
        Map<String, Object> response = new HashMap<>();
        response.put("relationships", relationships);
        response.put("relationshipCount", relationships.size());
        
        return response;
    }

    private Double calculateTotalBalance(List<Account> accounts) {
        return accounts.stream()
            .mapToDouble(Account::getBalance)
            .sum();
    }

    private Double calculateTotalSpending(Map<String, BigDecimal> spendingByCategory) {
        return spendingByCategory.values().stream()
            .mapToDouble(BigDecimal::doubleValue)
            .sum();
    }

    private LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
    }

    private LocalDateTime calculateStartDate(String period) {
        LocalDateTime now = LocalDateTime.now();
        switch (period) {
            case "LAST_WEEK":
                return now.minusWeeks(1);
            case "LAST_MONTH":
                return now.minusMonths(1);
            case "LAST_QUARTER":
                return now.minusMonths(3);
            case "LAST_YEAR":
                return now.minusYears(1);
            default:
                return now.minusMonths(1); // Default to last month
        }
    }
} 