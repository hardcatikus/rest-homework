package com.netcracker.dto;

import java.util.Date;

public class PurchaseAndCustomerOfMonthDTO {

    private Integer purchaseNumber;
    private String surname;
    private String area;
    private Date date;

    public PurchaseAndCustomerOfMonthDTO(Integer purchaseNumber, String surname, String area, Date date) {
        this.purchaseNumber = purchaseNumber;
        this.surname = surname;
        this.area = area;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
