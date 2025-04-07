package com.prueba.jair.benavides.clientsaccounts.clients_accounts.services;

import java.util.List;
import java.util.Optional;

import com.prueba.jair.benavides.clientsaccounts.clients_accounts.entities.Account;

public interface AccountService {

    List<Account> findAll();

    Account save (Account account);

    Optional<Account> update(Long accountId, Account account);

    Optional<Account> delete(Long accountId);

    Optional<Account> getAccountById(Long accountId);

}