package com.prueba.jair.benavides.clientsaccounts.clients_accounts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.jair.benavides.clientsaccounts.clients_accounts.entities.Account;
import com.prueba.jair.benavides.clientsaccounts.clients_accounts.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository repository;

    @Override
    public List<Account> findAll() {
        return (List<Account>) repository.findAll();
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public Optional<Account> update(Long accountId, Account account) {
        Optional<Account> accountOptional = repository.findById(accountId);
        if(accountOptional.isPresent()){
            Account accountDb = accountOptional.orElseThrow();
            accountDb.setAccountType(account.getAccountType());
            accountDb.setAccountNumber(account.getAccountNumber());
            return Optional.of(repository.save(accountDb));
        }
        
        return accountOptional;
    }

    @Override
    public Optional<Account> delete(Long accountId) {
        Optional<Account> accountOptional = repository.findById(accountId);
        accountOptional.ifPresent(account -> {
            repository.delete(account);
        });

        return accountOptional;
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        return repository.findById(accountId);
    }


}
