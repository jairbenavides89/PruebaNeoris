package com.prueba.jairbenavides.transaction.transaction_service.services;

import java.util.Date;
import java.util.List;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Transaction;

/**
 * Interfaz que define los métodos para la gestión de transacciones.
 * <p>
 * Proporciona operaciones para guardar una transacción en una cuenta y para consultar
 * las transacciones de una cuenta en un rango de fechas.
 * </p>
 */
public interface TransactionService {

    /**
     * Guarda una transacción para la cuenta especificada.
     *
     * @param accountId   el identificador de la cuenta a la que se le asociará la transacción.
     * @param transaction la transacción a guardar.
     * @return la transacción guardada.
     * @throws Exception si ocurre un error durante el proceso de guardado.
     */
    Transaction save(Long accountId, Transaction transaction) throws Exception;

    /**
     * Recupera una lista de transacciones para una cuenta específica dentro de un rango de fechas.
     *
     * @param accountId el identificador de la cuenta de la que se recuperan las transacciones.
     * @param startDate la fecha de inicio del rango de búsqueda.
     * @param endDate   la fecha de fin del rango de búsqueda.
     * @return una lista de transacciones que corresponden a la cuenta y se encuentran dentro del rango de fechas especificado.
     */
    List<Transaction> findByAccountAndTransactionDateBetween(Long accountId, Date startDate, Date endDate);
}
