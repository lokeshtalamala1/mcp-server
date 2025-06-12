package com.mcp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "card_transactions")
@Data
public class CardTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_category")
    private String merchantCategory;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
} 