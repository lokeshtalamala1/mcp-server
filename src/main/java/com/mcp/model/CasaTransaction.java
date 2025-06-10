package com.mcp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public String getTxnId() { return txnId; }
    public void setTxnId(String txnId) { this.txnId = txnId; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public LocalDate getTxnDate() { return txnDate; }
    public void setTxnDate(LocalDate txnDate) { this.txnDate = txnDate; }
    public LocalTime getTxnTime() { return txnTime; }
    public void setTxnTime(LocalTime txnTime) { this.txnTime = txnTime; }
    public String getTxnCode() { return txnCode; }
    public void setTxnCode(String txnCode) { this.txnCode = txnCode; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getDrCrFlag() { return drCrFlag; }
    public void setDrCrFlag(String drCrFlag) { this.drCrFlag = drCrFlag; }
    public String getReference1() { return reference1; }
    public void setReference1(String reference1) { this.reference1 = reference1; }
    public String getReference2() { return reference2; }
    public void setReference2(String reference2) { this.reference2 = reference2; }
    public String getReference3() { return reference3; }
    public void setReference3(String reference3) { this.reference3 = reference3; }
    public String getTxnDescription() { return txnDescription; }
    public void setTxnDescription(String txnDescription) { this.txnDescription = txnDescription; }

    // Getters and setters...
}