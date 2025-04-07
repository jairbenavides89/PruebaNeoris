package com.prueba.jairbenavides.transaction.transaction_service.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;
import com.prueba.jairbenavides.transaction.transaction_service.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    
    List<Transaction> findByAccountAndTransactionDateBetween(Account account, Date startDate, Date endDate);
}
