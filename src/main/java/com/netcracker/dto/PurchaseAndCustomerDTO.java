package com.netcracker.dto;

import java.util.Date;

public class PurchaseAndCustomerDTO {

    private Date date;
    private String surname;
    private Float discount;
    private String bookName;
    private Integer quantity;

    public PurchaseAndCustomerDTO(Date date, String surname, Float discount, String bookName, Integer quantity) {
        this.date = date;
        this.surname = surname;
        this.discount = discount;
        this.bookName = bookName;
        this.quantity = quantity;
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

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
