package com.mcp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public String getTxnId() { return txnId; }
    public void setTxnId(String txnId) { this.txnId = txnId; }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public LocalDate getTxnDate() { return txnDate; }
    public void setTxnDate(LocalDate txnDate) { this.txnDate = txnDate; }
    public LocalTime getTxnTime() { return txnTime; }
    public void setTxnTime(LocalTime txnTime) { this.txnTime = txnTime; }
    public String getMerchantName() { return merchantName; }
    public void setMerchantName(String merchantName) { this.merchantName = merchantName; }
    public String getMccCode() { return mccCode; }
    public void setMccCode(String mccCode) { this.mccCode = mccCode; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getDrCrFlag() { return drCrFlag; }
    public void setDrCrFlag(String drCrFlag) { this.drCrFlag = drCrFlag; }

    // Getters and setters...
}
