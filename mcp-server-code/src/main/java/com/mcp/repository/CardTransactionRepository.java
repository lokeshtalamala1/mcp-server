package com.mcp.repository;

import com.mcp.entity.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {
    List<CardTransaction> findByCardNumber(String cardNumber);
    
    List<CardTransaction> findByCustomerId(String customerId);
    
    @Query("SELECT ct FROM CardTransaction ct WHERE ct.cardNumber = :cardNumber AND ct.transactionDate BETWEEN :startDate AND :endDate")
    List<CardTransaction> findTransactionsByDateRange(
        @Param("cardNumber") String cardNumber,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    @Query("SELECT ct FROM CardTransaction ct WHERE ct.customerId = :customerId AND ct.merchantCategory = :category AND ct.transactionDate BETWEEN :startDate AND :endDate")
    List<CardTransaction> findTransactionsByCategoryAndDateRange(
        @Param("customerId") String customerId,
        @Param("category") String category,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    @Query("SELECT SUM(ct.amount) FROM CardTransaction ct WHERE ct.customerId = :customerId AND ct.merchantCategory = :category AND ct.transactionDate BETWEEN :startDate AND :endDate")
    BigDecimal getTotalSpendingByCategory(
        @Param("customerId") String customerId,
        @Param("category") String category,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    @Query("SELECT ct.merchantCategory, SUM(ct.amount) FROM CardTransaction ct WHERE ct.customerId = :customerId AND ct.transactionDate BETWEEN :startDate AND :endDate GROUP BY ct.merchantCategory")
    List<Object[]> getSpendingByCategory(
        @Param("customerId") String customerId,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
} 