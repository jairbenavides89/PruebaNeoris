package com.prueba.jairbenavides.transaction.transaction_service.services;

/*import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
import java.util.HashSet;}*/

/**
 * Servicio que escucha eventos de creación de clientes a través de RabbitMQ y 
 * genera una cuenta bancaria para cada cliente nuevo.
 */
public class ClientEventListenerService {

    /*@Autowired
    private AccountRepository accountRepository;

    private static final SecureRandom random = new SecureRandom();
    */
    /**
     * Maneja el evento de creación de un cliente recibido desde la cola RabbitMQ.
     * <p>
     * Cuando se recibe un {@link ClientCreatedEvent}, se genera un número de cuenta único
     * y se crea una nueva cuenta con tipo "Ahorros", saldo cero y se asocia con el ID del cliente.
     * </p>
     *
     * @param event el evento de creación de cliente que contiene la información del cliente.
     */
    /*@RabbitListener(queues = CLIENT_CREATED_QUEUE)
    @Transactional
    public void handleClientCreatedEvent(ClientCreatedEvent event) {
        String accountNumber = generateAccountNumber();

        Account account = new Account(accountNumber,
                "Ahorros",
                BigDecimal.ZERO, event.id(),
                new HashSet<Transaction>());

        accountRepository.save(account);
    }
    */
    /**
     * Genera un número de cuenta único compuesto por 10 dígitos.
     * <p>
     * El método utiliza un ciclo para generar un número de cuenta aleatorio y verifica que
     * el número generado no exista en la base de datos.
     * </p>
     *
     * @return un número de cuenta único representado como una cadena de 10 dígitos.
     */
    /*private String generateAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        do {
            accountNumber.setLength(0); // Reinicia el StringBuilder para cada intento
            for (int i = 0; i < 10; i++) {
                int digito = random.nextInt(10);
                accountNumber.append(digito);
            }
        } while (accountRepository.existsByAccountNumber(accountNumber.toString()));
        
        return accountNumber.toString();
    }*/
}
