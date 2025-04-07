package com.prueba.jairbenavides.transaction.transaction_service.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false, length = 10)
    private String accountNumber;

    @Column(nullable = false, length = 10)
    private String accountType;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal initialBalance;

    @Column(nullable = false)
    private Long clientId;

    @JsonIgnoreProperties({"account", "handler", "hibernateLazyInitializer"})
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account")
    private Set<Transaction> transactions;

    public Account(String accountName, String accountType, BigDecimal initialBalance, Long clientId,
            Set<Transaction> transactions) {
        this();
        this.accountNumber = accountName;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.clientId = clientId;
        this.transactions = transactions;
    }

    public Account() {
        transactions = new HashSet<>();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountName) {
        this.accountNumber = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Account addTransaction(Transaction transaction){
        transactions.add(transaction);
        transaction.setAccount(this);
        return this;
    }

}
