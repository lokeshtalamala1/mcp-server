package com.mcp.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "status")
    private String status;

    @Column(name = "opening_date")
    private LocalDateTime openingDate;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
} 