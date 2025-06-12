package com.mcp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "casa_transactions")
@Data
public class CasaTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "balance_after")
    private BigDecimal balanceAfter;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
} 