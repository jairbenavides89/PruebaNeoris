package com.prueba.jairbenavides.transaction.transaction_service.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;
import com.prueba.jairbenavides.transaction.transaction_service.entities.Transaction;

/**
 * Repositorio para la entidad {@link Transaction} que extiende de {@link CrudRepository}.
 * <p>
 * Proporciona operaciones CRUD para la entidad {@link Transaction} y métodos personalizados
 * para consultas específicas.
 * </p>
 */
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    
    /**
     * Busca y retorna una lista de transacciones para una cuenta específica dentro de un rango de fechas.
     *
     * @param account   la cuenta para la cual se buscan las transacciones.
     * @param startDate la fecha de inicio del rango en el que se buscan las transacciones.
     * @param endDate   la fecha de fin del rango en el que se buscan las transacciones.
     * @return una lista de transacciones que corresponden a la cuenta y se encuentran entre las fechas especificadas.
     */
    List<Transaction> findByAccountAndTransactionDateBetween(Account account, Date startDate, Date endDate);
}
