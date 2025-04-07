package com.prueba.jairbenavides.transaction.transaction_service.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.jairbenavides.transaction.transaction_service.entities.Account;
import com.prueba.jairbenavides.transaction.transaction_service.entities.Transaction;
import com.prueba.jairbenavides.transaction.transaction_service.events.ClientCreatedEvent;
import com.prueba.jairbenavides.transaction.transaction_service.repositories.AccountRepository;

import static com.prueba.jairbenavides.transaction.transaction_service.config.MessageQueueConfig.CLIENT_CREATED_QUEUE;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.HashSet;

@Service
public class ClientEventListenerService {

    @Autowired
    private AccountRepository accountRepository;

    private static final SecureRandom random = new SecureRandom();

    @RabbitListener(queues = CLIENT_CREATED_QUEUE)
    @Transactional
    public void handleClientCreatedEvent(ClientCreatedEvent event) {
        String accountNumber = generateAccountNumber();

        Account account = new Account(accountNumber,
                "Ahorros",
                BigDecimal.ZERO, event.id(),
                new HashSet<Transaction>());

        accountRepository.save(account);
    }

    private String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        do {
            for (int i = 0; i < 10; i++) {
                int digito = random.nextInt(10);
                accountNumber.append(digito);
            }
        } while (accountRepository.existsByAccountNumber(accountNumber.toString()));
        
        return accountNumber.toString();
    }

}
