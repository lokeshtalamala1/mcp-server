package com.mcp.repository;

import com.mcp.entity.CasaTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CasaTransactionRepository extends JpaRepository<CasaTransaction, Long> {
    List<CasaTransaction> findByAccountNumber(String accountNumber);
    
    List<CasaTransaction> findByTransactionId(String transactionId);
    
    @Query("SELECT ct FROM CasaTransaction ct WHERE ct.accountNumber = :accountNumber AND ct.transactionDate BETWEEN :startDate AND :endDate")
    List<CasaTransaction> findTransactionsByDateRange(
        @Param("accountNumber") String accountNumber,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    @Query("SELECT ct FROM CasaTransaction ct WHERE ct.accountNumber = :accountNumber AND ct.transactionType = :type AND ct.transactionDate BETWEEN :startDate AND :endDate")
    List<CasaTransaction> findTransactionsByTypeAndDateRange(
        @Param("accountNumber") String accountNumber,
        @Param("type") String type,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
    
    @Query("SELECT SUM(ct.amount) FROM CasaTransaction ct WHERE ct.accountNumber = :accountNumber AND ct.transactionDate BETWEEN :startDate AND :endDate")
    BigDecimal getTotalTransactionsAmount(
        @Param("accountNumber") String accountNumber,
        @Param("startDate") LocalDateTime startDate,
        @Param("endDate") LocalDateTime endDate
    );
} 