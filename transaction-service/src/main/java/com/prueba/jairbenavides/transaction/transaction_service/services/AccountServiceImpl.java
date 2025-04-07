package com.prueba.jairbenavides.transaction.transaction_service.services;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;
import com.prueba.jairbenavides.transaction.transaction_service.repositories.AccountRepository;

/**
 * Implementación del servicio para la entidad {@link Account}.
 * <p>
 * Esta clase implementa la interfaz {@link AccountService} y proporciona la lógica de negocio para
 * la gestión de cuentas, incluyendo operaciones CRUD y la generación de números de cuenta únicos.
 * </p>
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    private static final SecureRandom random = new SecureRandom();

    /**
     * Recupera una lista de todas las cuentas.
     *
     * @return una lista de cuentas.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return (List<Account>) repository.findAll();
    }

    /**
     * Guarda una nueva cuenta. Antes de guardar, se genera un número de cuenta único.
     *
     * @param account la cuenta a guardar.
     * @return la cuenta guardada.
     */
    @Override
    @Transactional
    public Account save(Account account) {
        account.setAccountNumber(generateAccountNumber());
        return repository.save(account);
    }

    /**
     * Actualiza la cuenta identificada por el ID proporcionado con la información de la cuenta dada.
     *
     * @param accountId el identificador de la cuenta a actualizar.
     * @param account   la cuenta con la nueva información.
     * @return un {@link Optional} que contiene la cuenta actualizada, o vacío si no se encontró la cuenta.
     */
    @Override
    @Transactional
    public Optional<Account> update(Long accountId, Account account) {
        Optional<Account> accountOptional = repository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account accountDb = accountOptional.orElseThrow();
            accountDb.setAccountType(account.getAccountType());
            accountDb.setAccountNumber(account.getAccountNumber());
            return Optional.of(repository.save(accountDb));
        }
        return accountOptional;
    }

    /**
     * Elimina la cuenta identificada por el ID proporcionado.
     *
     * @param accountId el identificador de la cuenta a eliminar.
     * @return un {@link Optional} que contiene la cuenta eliminada, o vacío si no se encontró la cuenta.
     */
    @Override
    @Transactional
    public Optional<Account> delete(Long accountId) {
        Optional<Account> accountOptional = repository.findById(accountId);
        accountOptional.ifPresent(account -> {
            repository.delete(account);
        });
        return accountOptional;
    }

    /**
     * Recupera la cuenta identificada por el ID proporcionado.
     *
     * @param accountId el identificador de la cuenta.
     * @return un {@link Optional} que contiene la cuenta encontrada, o vacío si no se encontró la cuenta.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Account> getAccountById(Long accountId) {
        return repository.findById(accountId);
    }

    /**
     * Genera un número de cuenta único compuesto por 10 dígitos.
     * <p>
     * Se utiliza un ciclo para generar un número de cuenta y se verifica que el número generado no
     * exista ya en la base de datos mediante {@link AccountRepository#existsByAccountNumber(String)}.
     * </p>
     *
     * @return un número de cuenta único.
     */
    private String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        do {
            accountNumber.setLength(0); // Reiniciar el StringBuilder para cada intento
            for (int i = 0; i < 10; i++) {
                int digito = random.nextInt(10);
                accountNumber.append(digito);
            }
        } while (repository.existsByAccountNumber(accountNumber.toString()));
        
        return accountNumber.toString();
    }
}
