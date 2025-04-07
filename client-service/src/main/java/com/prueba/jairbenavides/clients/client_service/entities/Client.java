package com.prueba.jairbenavides.clients.client_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "person_id", unique = true, nullable = false)
    private Person person;

    public Client(Long clienteid, String password, Boolean status, Person person) {
        this.clienteid = clienteid;
        this.password = password;
        this.status = status;
        this.person = person;
    }

    public Client() {
    }

    public Long getClienteid() {
        return clienteid;
    }

    public void setClienteid(Long clienteid) {
        this.clienteid = clienteid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

