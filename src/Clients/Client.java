package Clients;

import java.util.UUID;

public class Client {
    private final String clientId; // Уникальный идентификатор клиента
    private String name;
    private String surname;
    private String address; // Необязательное поле, поэтому оно может быть null
    private Integer numPassport; // Необязательное поле, может быть null

    public Client(String clientId, String name, String surname) {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.address = null;
        this.numPassport = null;
    }
    public Client(String name, String surname) {
        this.clientId = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
        this.address = null;
        this.numPassport = null;
    }

    // Геттеры
    public String getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public Integer getNumPassport() {
        return numPassport;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumPassport(Integer numPassport) {
        this.numPassport = numPassport;
    }
}
