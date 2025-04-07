package com.prueba.jairbenavides.transaction.transaction_service.repositories;

import org.springframework.data.repository.CrudRepository;
import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;

/**
 * Interfaz para el repositorio de {@link Account} que extiende de {@link CrudRepository}.
 * <p>
 * Proporciona operaciones CRUD para la entidad {@link Account} y un método adicional
 * para verificar la existencia de una cuenta por su número.
 * </p>
 *
 * @author 
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

    /**
     * Verifica si existe una cuenta con el número de cuenta especificado.
     *
     * @param accountNumber el número de cuenta a verificar.
     * @return {@code true} si existe una cuenta con el número indicado; de lo contrario, {@code false}.
     */
    boolean existsByAccountNumber(String accountNumber);
}
