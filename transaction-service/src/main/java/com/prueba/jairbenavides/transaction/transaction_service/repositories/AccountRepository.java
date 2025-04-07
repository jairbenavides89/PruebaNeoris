package com.prueba.jairbenavides.transaction.transaction_service.repositories;

import org.springframework.data.repository.CrudRepository;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

    boolean existsByAccountNumber(String accountNumber);

}
