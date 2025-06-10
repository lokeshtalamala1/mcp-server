package com.mcp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

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

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public String getAccountSubtype() { return accountSubtype; }
    public void setAccountSubtype(String accountSubtype) { this.accountSubtype = accountSubtype; }
    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }
    public LocalDate getOpeningDate() { return openingDate; }
    public void setOpeningDate(LocalDate openingDate) { this.openingDate = openingDate; }
    public Double getCurrentBalance() { return currentBalance; }
    public void setCurrentBalance(Double currentBalance) { this.currentBalance = currentBalance; }
    public Boolean getJointFlag() { return jointFlag; }
    public void setJointFlag(Boolean jointFlag) { this.jointFlag = jointFlag; }
    public String getParentAccount() { return parentAccount; }
    public void setParentAccount(String parentAccount) { this.parentAccount = parentAccount; }
    public Boolean getPrimaryFlag() { return primaryFlag; }
    public void setPrimaryFlag(Boolean primaryFlag) { this.primaryFlag = primaryFlag; }

    // Getters and setters...
}
