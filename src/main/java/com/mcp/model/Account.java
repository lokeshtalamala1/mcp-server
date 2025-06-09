package com.mcp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "account_subtype")
    private String accountSubtype;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "opening_date")
    private LocalDate openingDate;

    @Column(name = "current_balance")
    private Double currentBalance;

    @Column(name = "joint_flag")
    private Boolean jointFlag;

    @Column(name = "parent_account")
    private String parentAccount;

    @Column(name = "primary_flag")
    private Boolean primaryFlag;

    // Getters and setters...
}
