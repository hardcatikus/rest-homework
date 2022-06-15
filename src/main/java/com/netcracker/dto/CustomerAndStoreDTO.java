package com.netcracker.dto;

public class CustomerAndStoreDTO {

    String surname;
    String shop;

    public CustomerAndStoreDTO(String surname, String shop) {
        this.surname = surname;
        this.shop = shop;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
