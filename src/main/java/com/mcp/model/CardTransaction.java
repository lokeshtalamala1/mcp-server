package com.mcp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "card_transactions")
public class CardTransaction {
    @Id
    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "txn_date")
    private LocalDate txnDate;

    @Column(name = "txn_time")
    private LocalTime txnTime;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "mcc_code")
    private String mccCode;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "dr_cr_flag")
    private String drCrFlag;

    // Getters and setters...
}
