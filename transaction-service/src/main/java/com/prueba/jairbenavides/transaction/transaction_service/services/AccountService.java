package com.prueba.jairbenavides.transaction.transaction_service.services;

import java.util.List;
import java.util.Optional;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;

/**
 * Interfaz de servicio para la entidad {@link Account} que define las operaciones
 * de negocio relacionadas con las cuentas.
 */
public interface AccountService {

    /**
     * Recupera una lista de todas las cuentas.
     *
     * @return una lista de todas las cuentas disponibles.
     */
    List<Account> findAll();

    /**
     * Guarda una nueva cuenta o actualiza una cuenta existente.
     *
     * @param account la cuenta a guardar.
     * @return la cuenta guardada.
     */
    Account save(Account account);

    /**
     * Actualiza la cuenta identificada por el ID proporcionado con la información de la cuenta dada.
     *
     * @param accountId el identificador de la cuenta a actualizar.
     * @param account   la cuenta con la nueva información.
     * @return un {@link Optional} que contiene la cuenta actualizada, o vacío si no se encontró la cuenta.
     */
    Optional<Account> update(Long accountId, Account account);

    /**
     * Elimina la cuenta identificada por el ID proporcionado.
     *
     * @param accountId el identificador de la cuenta a eliminar.
     * @return un {@link Optional} que contiene la cuenta eliminada, o vacío si no se encontró la cuenta.
     */
    Optional<Account> delete(Long accountId);

    /**
     * Recupera la cuenta identificada por el ID proporcionado.
     *
     * @param accountId el identificador de la cuenta.
     * @return un {@link Optional} que contiene la cuenta encontrada, o vacío si no se encontró la cuenta.
     */
    Optional<Account> getAccountById(Long accountId);
}
