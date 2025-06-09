package com.mcp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "casa_transactions")
public class CasaTransaction {
    @Id
    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "account_id")
    private String accountId;

    @Column(name = "txn_date")
    private LocalDate txnDate;

    @Column(name = "txn_time")
    private LocalTime txnTime;

    @Column(name = "txn_code")
    private String txnCode;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "dr_cr_flag")
    private String drCrFlag;

    @Column(name = "reference_1")
    private String reference1;

    @Column(name = "reference_2")
    private String reference2;

    @Column(name = "reference_3")
    private String reference3;

    @Column(name = "txn_description")
    private String txnDescription;
}