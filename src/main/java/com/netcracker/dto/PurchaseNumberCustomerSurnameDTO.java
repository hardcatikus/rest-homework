package com.netcracker.dto;

import java.util.Date;

public class PurchaseNumberCustomerSurnameDTO {

    private Integer purchaseNumber;
    private String surname;
    private Date date;

    public PurchaseNumberCustomerSurnameDTO(Integer purchaseNumber, String surname, Date date) {
        this.purchaseNumber = purchaseNumber;
        this.surname = surname;
        this.date = date;
    }

    public Integer getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Integer purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
