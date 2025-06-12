package com.mcp.service;

import com.mcp.entity.CasaTransaction;
import com.mcp.repository.CasaTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CasaTransactionService {
    private final CasaTransactionRepository transactionRepository;
    private final AccountService accountService;

    @Transactional(readOnly = true)
    public List<CasaTransaction> getTransactionsByAccountNumber(String accountNumber) {
        return transactionRepository.findByAccountNumber(accountNumber);
    }

    @Transactional(readOnly = true)
    public List<CasaTransaction> getTransactionsByDateRange(
            String accountNumber,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return transactionRepository.findTransactionsByDateRange(accountNumber, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<CasaTransaction> getTransactionsByTypeAndDateRange(
            String accountNumber,
            String type,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return transactionRepository.findTransactionsByTypeAndDateRange(accountNumber, type, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public BigDecimal getTotalTransactionsAmount(
            String accountNumber,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return transactionRepository.getTotalTransactionsAmount(accountNumber, startDate, endDate);
    }

    @Transactional
    public CasaTransaction createTransaction(CasaTransaction transaction) {
        // Set transaction creation time
        transaction.setCreatedAt(LocalDateTime.now());
        
        // Update account balance
        accountService.getAccountByNumber(transaction.getAccountNumber())
            .ifPresent(account -> {
                BigDecimal currentBalance = BigDecimal.valueOf(account.getBalance());
                BigDecimal newBalance = currentBalance.add(transaction.getAmount());
                account.setBalance(newBalance.doubleValue());
                account.setLastUpdated(LocalDateTime.now());
                accountService.updateAccount(account);
                
                // Set balance after transaction
                transaction.setBalanceAfter(newBalance);
            });

        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public CasaTransaction getTransactionById(String transactionId) {
        return transactionRepository.findByTransactionId(transactionId)
            .stream()
            .findFirst()
            .orElse(null);
    }

    @Transactional
    public void reverseTransaction(String transactionId) {
        transactionRepository.findByTransactionId(transactionId)
            .stream()
            .findFirst()
            .ifPresent(transaction -> {
                // Create reversal transaction
                CasaTransaction reversal = new CasaTransaction();
                reversal.setTransactionId(transactionId + "_REVERSAL");
                reversal.setAccountNumber(transaction.getAccountNumber());
                reversal.setTransactionType("REVERSAL");
                reversal.setAmount(transaction.getAmount().negate());
                reversal.setCurrency(transaction.getCurrency());
                reversal.setDescription("Reversal of transaction " + transactionId);
                reversal.setStatus("COMPLETED");
                reversal.setCreatedAt(LocalDateTime.now());
                
                createTransaction(reversal);
            });
    }
} 