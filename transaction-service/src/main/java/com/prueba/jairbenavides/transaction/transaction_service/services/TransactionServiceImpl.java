package com.prueba.jairbenavides.transaction.transaction_service.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;
import com.prueba.jairbenavides.transaction.transaction_service.entities.Transaction;
import com.prueba.jairbenavides.transaction.transaction_service.repositories.AccountRepository;
import com.prueba.jairbenavides.transaction.transaction_service.repositories.TransactionRepository;

/**
 * Implementación del servicio para la gestión de transacciones.
 * <p>
 * Esta clase implementa la interfaz {@link TransactionService} y proporciona la lógica de negocio
 * para guardar transacciones en una cuenta y consultar transacciones dentro de un rango de fechas.
 * </p>
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Guarda una transacción para la cuenta especificada y actualiza el saldo de la cuenta.
     * <p>
     * Dependiendo del tipo de transacción ("Deposito" o "Retiro"), se incrementa o decrementa el saldo
     * de la cuenta. Se lanza una excepción si la cuenta no existe, si el monto de retiro excede el saldo
     * disponible o si el tipo de transacción es inválido.
     * </p>
     *
     * @param accountId   el identificador de la cuenta a la que se asociará la transacción.
     * @param transaction la transacción a procesar y guardar.
     * @return la transacción guardada con el saldo actualizado.
     * @throws RuntimeException si la cuenta no existe, el tipo de transacción es inválido o el saldo es insuficiente.
     */
    @Override
    @Transactional
    public Transaction save(Long accountId, Transaction transaction) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Account account = new Account();

        if (accountOptional.isPresent()) {
            account = accountOptional.orElseThrow();
        } else {
            throw new RuntimeException("La cuenta con id: " + accountId + " no existe!");
        }

        if (transaction.getTransactionType().equalsIgnoreCase("Deposito")) {
            account.setInitialBalance(account.getInitialBalance().add(transaction.getAmount()));
            transaction.setBalance(account.getInitialBalance());
        } else if (transaction.getTransactionType().equalsIgnoreCase("Retiro")) {
            if (account.getInitialBalance().compareTo(transaction.getAmount()) < 0) {
                throw new RuntimeException("El monto a retirar es mayor que el saldo de la cuenta");
            }
            account.setInitialBalance(account.getInitialBalance().subtract(transaction.getAmount()));
            transaction.setBalance(account.getInitialBalance());
        } else {
            throw new RuntimeException("Tipo de transacción inválido!");
        }

        account.addTransaction(transaction);
        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    /**
     * Recupera una lista de transacciones para la cuenta especificada dentro de un rango de fechas.
     * <p>
     * Se lanza una excepción si la cuenta no existe o si no se encuentran transacciones en el rango de fechas indicado.
     * </p>
     *
     * @param accountId el identificador de la cuenta de la cual se recuperan las transacciones.
     * @param startDate la fecha de inicio del rango de búsqueda.
     * @param endDate   la fecha de fin del rango de búsqueda.
     * @return una lista de transacciones asociadas a la cuenta dentro del rango especificado.
     * @throws RuntimeException si la cuenta no existe o si no existen transacciones en el rango indicado.
     */
    @Override
    public List<Transaction> findByAccountAndTransactionDateBetween(Long accountId, Date startDate, Date endDate) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Account account = new Account();
        if (accountOptional.isPresent()) {
            account = accountOptional.orElseThrow();
        } else {
            throw new RuntimeException("La cuenta con id: " + accountId + " no existe!");
        }

        List<Transaction> transactions = transactionRepository.findByAccountAndTransactionDateBetween(account, startDate, endDate);

        if (transactions.isEmpty()) {
            throw new RuntimeException("No existen transacciones en el rango de fechas solicitado!");
        }

        return transactions;
    }
    
}
