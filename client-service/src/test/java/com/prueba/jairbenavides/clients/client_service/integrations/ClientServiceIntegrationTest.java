package com.prueba.jairbenavides.clients.client_service.integrations;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void shouldReturnCreatedClient() {
        String requestBody = """
                {
                "password": "789789798",
                "status": true,
                "person": {
                	"name": "Cliente Prueba",
                	"gender": "Masculino",
                	"age": 35,
                	"identification": "445566774",
                	"address": "Calle A",
                	"telephone": "0991234567"
                }
                }
                                """;
        webTestClient
                .post()
                .uri("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.person.name").isEqualTo("Cliente Prueba")
                .jsonPath("$.person.identification").isEqualTo("445566774")
                .jsonPath("$.status").isEqualTo(true);
    }

}
