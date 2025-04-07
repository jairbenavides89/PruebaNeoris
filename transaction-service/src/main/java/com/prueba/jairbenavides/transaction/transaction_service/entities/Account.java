package com.prueba.jairbenavides.transaction.transaction_service.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

/**
 * Representa una cuenta bancaria asociada a un cliente.
 * <p>
 * Contiene información como número de cuenta, tipo, saldo inicial y transacciones asociadas.
 * </p>
 */
@Entity
@Table(name = "accounts")
public class Account {

    /**
     * ID único de la cuenta (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    /**
     * Número único de cuenta (máx. 10 caracteres).
     */
    @Column(nullable = false, length = 10)
    private String accountNumber;

    /**
     * Tipo de cuenta (por ejemplo, "Ahorros", "Corriente").
     */
    @Column(nullable = false, length = 10)
    private String accountType;

    /**
     * Saldo inicial de la cuenta.
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal initialBalance;

    /**
     * ID del cliente propietario de esta cuenta.
     */
    @Column(nullable = false)
    private Long clientId;

    /**
     * Lista de transacciones asociadas a esta cuenta.
     */
    @JsonIgnoreProperties({"account", "handler", "hibernateLazyInitializer"})
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account")
    private Set<Transaction> transactions;

    /**
     * Constructor completo para la clase Account.
     *
     * @param accountName    Número de cuenta.
     * @param accountType    Tipo de cuenta.
     * @param initialBalance Saldo inicial.
     * @param clientId       ID del cliente propietario.
     * @param transactions   Conjunto de transacciones asociadas.
     */
    public Account(String accountName, String accountType, BigDecimal initialBalance, Long clientId,
                   Set<Transaction> transactions) {
        this();
        this.accountNumber = accountName;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.clientId = clientId;
        this.transactions = transactions;
    }

    /**
     * Constructor vacío.
     * <p>
     * Inicializa la colección de transacciones.
     * </p>
     */
    public Account() {
        transactions = new HashSet<>();
    }

    /**
     * Obtiene el ID de la cuenta.
     *
     * @return el ID de la cuenta.
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Establece el ID de la cuenta.
     *
     * @param accountId el nuevo ID de la cuenta.
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Obtiene el número de la cuenta.
     *
     * @return el número de la cuenta.
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Establece el número de la cuenta.
     *
     * @param accountName el nuevo número de la cuenta.
     */
    public void setAccountNumber(String accountName) {
        this.accountNumber = accountName;
    }

    /**
     * Obtiene el tipo de cuenta.
     *
     * @return el tipo de cuenta.
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Establece el tipo de cuenta.
     *
     * @param accountType el nuevo tipo de cuenta.
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Obtiene el saldo inicial de la cuenta.
     *
     * @return el saldo inicial.
     */
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    /**
     * Establece el saldo inicial de la cuenta.
     *
     * @param initialBalance el nuevo saldo inicial.
     */
    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    /**
     * Obtiene el ID del cliente propietario de la cuenta.
     *
     * @return el ID del cliente.
     */
    public Long getClientId() {
        return clientId;
    }

    /**
     * Establece el ID del cliente propietario de la cuenta.
     *
     * @param clientId el nuevo ID del cliente.
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * Obtiene el conjunto de transacciones asociadas a la cuenta.
     *
     * @return el conjunto de transacciones.
     */
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Establece el conjunto de transacciones asociadas a la cuenta.
     *
     * @param transactions el nuevo conjunto de transacciones.
     */
    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Agrega una transacción a la cuenta y establece la relación bidireccional.
     *
     * @param transaction la transacción a agregar.
     * @return la cuenta actual (para encadenamiento).
     */
    public Account addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this);
        return this;
    }
}
