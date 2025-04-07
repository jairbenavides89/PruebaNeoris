package com.prueba.jairbenavides.transaction.transaction_service.services;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;
import com.prueba.jairbenavides.transaction.transaction_service.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    private static final SecureRandom random = new SecureRandom();

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return (List<Account>) repository.findAll();
    }

    @Override
    @Transactional
    public Account save(Account account) {
        account.setAccountNumber(generateAccountNumber());
        return repository.save(account);
    }

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

    @Override
    @Transactional
    public Optional<Account> delete(Long accountId) {
        Optional<Account> accountOptional = repository.findById(accountId);
        accountOptional.ifPresent(account -> {
            repository.delete(account);
        });

        return accountOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> getAccountById(Long accountId) {
        return repository.findById(accountId);
    }

    private String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        do {
            for (int i = 0; i < 10; i++) {
                int digito = random.nextInt(10);
                accountNumber.append(digito);
            }
        } while (repository.existsByAccountNumber(accountNumber.toString()));
        
        return accountNumber.toString();
    }

}
