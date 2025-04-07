package com.prueba.jairbenavides.transaction.transaction_service.services;

import java.util.Date;
import java.util.List;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Transaction;

public interface TransactionService {

    Transaction save(Long accountId, Transaction transaction) throws Exception;

    List<Transaction> findByAccountAndTransactionDateBetween(Long accountId, Date startDate, Date endDate);
}
