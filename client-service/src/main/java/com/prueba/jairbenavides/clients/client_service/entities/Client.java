package com.prueba.jairbenavides.clients.client_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Representa un cliente en el sistema.
 * <p>
 * La entidad Client se asocia de manera uno a uno con la entidad {@link Person} para almacenar
 * información adicional de autenticación y estado del cliente.
 * </p>
 */
@Entity
@Table(name = "clients")
public class Client {

    /**
     * Identificador único del cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteid;

    /**
     * Contraseña del cliente.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Estado del cliente (activo/inactivo).
     */
    @Column(nullable = false)
    private Boolean status;

    /**
     * Información personal asociada al cliente.
     * <p>
     * Relación uno a uno con la entidad {@link Person}.
     * </p>
     */
    @OneToOne
    @JoinColumn(name = "person_id", unique = true, nullable = false)
    private Person person;

    /**
     * Constructor que inicializa un cliente con los atributos especificados.
     *
     * @param password la contraseña del cliente.
     * @param status   el estado del cliente.
     * @param person   la información personal asociada al cliente.
     */
    public Client(String password, Boolean status, Person person) {
        this.password = password;
        this.status = status;
        this.person = person;
    }

    /**
     * Constructor por defecto.
     */
    public Client() {
    }

    /**
     * Obtiene el identificador único del cliente.
     *
     * @return el ID del cliente.
     */
    public Long getClienteid() {
        return clienteid;
    }

    /**
     * Establece el identificador único del cliente.
     *
     * @param clienteid el nuevo ID del cliente.
     */
    public void setClienteid(Long clienteid) {
        this.clienteid = clienteid;
    }

    /**
     * Obtiene la contraseña del cliente.
     *
     * @return la contraseña del cliente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del cliente.
     *
     * @param password la nueva contraseña del cliente.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el estado del cliente.
     *
     * @return {@code true} si el cliente está activo; de lo contrario, {@code false}.
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * Establece el estado del cliente.
     *
     * @param status el nuevo estado del cliente.
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Obtiene la información personal asociada al cliente.
     *
     * @return la entidad {@link Person} vinculada al cliente.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Establece la información personal asociada al cliente.
     *
     * @param person la nueva información personal del cliente.
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
