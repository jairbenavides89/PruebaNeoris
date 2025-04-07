package com.prueba.jairbenavides.transaction.transaction_service.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

/**
 * Representa una transacción realizada en una cuenta bancaria.
 * <p>
 * Contiene información sobre la fecha, tipo de transacción, monto,
 * saldo resultante y la cuenta asociada.
 * </p>
 */
@Entity
@Table(name = "transactions")
public class Transaction {

    /**
     * ID único de la transacción (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    /**
     * Fecha en la que se realizó la transacción.
     * Se inicializa con la fecha actual por defecto.
     */
    @Column(nullable = false)
    private Date transactionDate = new Date();

    /**
     * Tipo de transacción (por ejemplo, "Retiro", "Depósito").
     */
    @Column(nullable = false, length = 20)
    private String transactionType;

    /**
     * Monto involucrado en la transacción.
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    /**
     * Saldo de la cuenta después de realizar la transacción.
     */
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal balance;

    /**
     * Cuenta asociada a esta transacción.
     */
    @JsonIgnoreProperties({"transactions", "handler", "hibernateLazyInitializer"})
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    /**
     * Constructor completo para inicializar todos los atributos de la transacción.
     *
     * @param transactionId   ID de la transacción.
     * @param transactionDate Fecha de la transacción.
     * @param transactionType Tipo de transacción.
     * @param amount          Monto de la transacción.
     * @param balance         Saldo resultante después de la transacción.
     * @param account         Cuenta asociada a la transacción.
     */
    public Transaction(Long transactionId, Date transactionDate, String transactionType, BigDecimal amount,
                       BigDecimal balance, Account account) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balance = balance;
        this.account = account;
    }

    /**
     * Constructor vacío requerido por JPA.
     */
    public Transaction() {
    }

    /**
     * Obtiene el ID de la transacción.
     *
     * @return el ID de la transacción.
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Establece el ID de la transacción.
     *
     * @param transactionId el nuevo ID de la transacción.
     */
    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Obtiene la fecha en la que se realizó la transacción.
     *
     * @return la fecha de la transacción.
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Establece la fecha de la transacción.
     *
     * @param transactionDate la nueva fecha de la transacción.
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Obtiene el tipo de transacción.
     *
     * @return el tipo de transacción.
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Establece el tipo de transacción.
     *
     * @param transactionType el nuevo tipo de transacción.
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Obtiene el monto involucrado en la transacción.
     *
     * @return el monto de la transacción.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Establece el monto de la transacción.
     *
     * @param amount el nuevo monto de la transacción.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Obtiene el saldo de la cuenta después de la transacción.
     *
     * @return el saldo resultante.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Establece el saldo de la cuenta después de la transacción.
     *
     * @param balance el nuevo saldo.
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Obtiene la cuenta asociada a esta transacción.
     *
     * @return la cuenta asociada.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Establece la cuenta asociada a esta transacción.
     *
     * @param account la nueva cuenta asociada.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Compara dos objetos Transaction basándose en sus atributos principales.
     *
     * @param obj Objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Transaction other = (Transaction) obj;
        return (transactionId != null && transactionId.equals(other.transactionId)) &&
               (transactionDate != null && transactionDate.equals(other.transactionDate)) &&
               (transactionType != null && transactionType.equals(other.transactionType)) &&
               (amount != null && amount.equals(other.amount)) &&
               (balance != null && balance.equals(other.balance)) &&
               (account != null && account.equals(other.account));
    }

    /**
     * Calcula el código hash para esta transacción.
     *
     * @return el valor hash calculado.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
        result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
        result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((balance == null) ? 0 : balance.hashCode());
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        return result;
    }
}
