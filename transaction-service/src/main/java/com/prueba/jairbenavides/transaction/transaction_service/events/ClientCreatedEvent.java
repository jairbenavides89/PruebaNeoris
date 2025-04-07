package com.prueba.jairbenavides.transaction.transaction_service.events;

public record ClientCreatedEvent(Long id, String name, String identificaciton) {

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String identificaciton() {
        return identificaciton;
    }

}