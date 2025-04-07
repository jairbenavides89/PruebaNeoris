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

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

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
                throw new RuntimeException("El monto a retirar es menor al saldo de la cuenta");
            }
            account.setInitialBalance(account.getInitialBalance().subtract(transaction.getAmount()));
            transaction.setBalance(account.getInitialBalance());
        } else {
            throw new RuntimeException("Tipo de transacción invalida!");
        }

        account.addTransaction(transaction);

        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findByAccountAndTransactionDateBetween(Long accountId, Date startDate, Date endDate) {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        Account account = new Account();
        if(accountOptional.isPresent()){
            account = accountOptional.orElseThrow();
        } else {
            throw new RuntimeException("La cuenta con id: " + accountId + " No existe!");
        }

        List<Transaction> transactions = transactionRepository.findByAccountAndTransactionDateBetween(account, startDate, endDate);

        if(transactions.isEmpty()){
            throw new RuntimeException("No existen transacciones en el rango de fechas solicitado!");
        }

        return transactions;
    }
    
}
