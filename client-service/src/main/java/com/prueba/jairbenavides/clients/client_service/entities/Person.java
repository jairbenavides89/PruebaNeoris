package com.prueba.jairbenavides.clients.client_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Representa a una persona dentro del sistema.
 * <p>
 * Esta entidad se mapea a la tabla "person" en la base de datos y contiene
 * información personal como nombre, género, edad, identificación, dirección y teléfono.
 * </p>
 */
@Entity
@Table(name = "person")
public class Person {

    /**
     * Identificador único de la persona.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    /**
     * Nombre completo de la persona.
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Género de la persona.
     */
    @Column(nullable = false, length = 15)
    private String gender;

    /**
     * Edad de la persona.
     */
    @Column(nullable = false)
    private Integer age;

    /**
     * Número de identificación único de la persona.
     */
    @Column(nullable = false, unique = true, length = 20)
    private String identification;

    /**
     * Dirección de residencia de la persona.
     */
    @Column(nullable = false, length = 200)
    private String address;

    /**
     * Número de teléfono de la persona.
     */
    @Column(nullable = false, length = 15)
    private String telephone;

    /**
     * Constructor por defecto.
     */
    public Person() {
    }

    /**
     * Constructor que inicializa todos los atributos de la persona.
     *
     * @param name           el nombre completo de la persona.
     * @param gender         el género de la persona.
     * @param age            la edad de la persona.
     * @param identification el número de identificación único de la persona.
     * @param address        la dirección de residencia de la persona.
     * @param telephone      el número de teléfono de la persona.
     */
    public Person(String name, String gender, Integer age, String identification, String address,
            String telephone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.identification = identification;
        this.address = address;
        this.telephone = telephone;
    }

    /**
     * Obtiene el identificador único de la persona.
     *
     * @return el ID de la persona.
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * Establece el identificador único de la persona.
     *
     * @param personId el nuevo ID de la persona.
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * Obtiene el nombre completo de la persona.
     *
     * @return el nombre de la persona.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre completo de la persona.
     *
     * @param name el nuevo nombre de la persona.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el género de la persona.
     *
     * @return el género de la persona.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Establece el género de la persona.
     *
     * @param gender el nuevo género de la persona.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Obtiene la edad de la persona.
     *
     * @return la edad de la persona.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Establece la edad de la persona.
     *
     * @param age la nueva edad de la persona.
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Obtiene el número de identificación único de la persona.
     *
     * @return el número de identificación de la persona.
     */
    public String getIdentification() {
        return identification;
    }

    /**
     * Establece el número de identificación único de la persona.
     *
     * @param identification el nuevo número de identificación de la persona.
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Obtiene la dirección de residencia de la persona.
     *
     * @return la dirección de la persona.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece la dirección de residencia de la persona.
     *
     * @param address la nueva dirección de la persona.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Obtiene el número de teléfono de la persona.
     *
     * @return el número de teléfono de la persona.
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Establece el número de teléfono de la persona.
     *
     * @param telephone el nuevo número de teléfono de la persona.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
