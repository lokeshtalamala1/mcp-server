package com.mcp.service;

import com.mcp.entity.CardTransaction;
import com.mcp.repository.CardTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardTransactionService {
    private final CardTransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public List<CardTransaction> getTransactionsByCardNumber(String cardNumber) {
        return transactionRepository.findByCardNumber(cardNumber);
    }

    @Transactional(readOnly = true)
    public List<CardTransaction> getTransactionsByCustomerId(String customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    @Transactional(readOnly = true)
    public List<CardTransaction> getTransactionsByDateRange(
            String cardNumber,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return transactionRepository.findTransactionsByDateRange(cardNumber, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<CardTransaction> getTransactionsByCategoryAndDateRange(
            String customerId,
            String category,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return transactionRepository.findTransactionsByCategoryAndDateRange(customerId, category, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public BigDecimal getTotalSpendingByCategory(
            String customerId,
            String category,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return transactionRepository.getTotalSpendingByCategory(customerId, category, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public Map<String, BigDecimal> getSpendingByCategory(
            String customerId,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return transactionRepository.getSpendingByCategory(customerId, startDate, endDate)
            .stream()
            .collect(Collectors.toMap(
                row -> (String) row[0],
                row -> (BigDecimal) row[1]
            ));
    }

    @Transactional
    public CardTransaction createTransaction(CardTransaction transaction) {
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setStatus("COMPLETED");
        return transactionRepository.save(transaction);
    }

    @Transactional(readOnly = true)
    public List<CardTransaction> getRecentTransactions(String customerId, int limit) {
        return transactionRepository.findByCustomerId(customerId)
            .stream()
            .sorted((t1, t2) -> t2.getTransactionDate().compareTo(t1.getTransactionDate()))
            .limit(limit)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Map<String, List<CardTransaction>> getTransactionsByMerchantCategory(
            String customerId,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        return transactionRepository.findTransactionsByCategoryAndDateRange(customerId, null, startDate, endDate)
            .stream()
            .collect(Collectors.groupingBy(CardTransaction::getMerchantCategory));
    }
} 