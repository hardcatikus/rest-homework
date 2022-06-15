package com.netcracker.dto;

import java.util.Objects;

public class CustomerSurnameDiscountDTO {

    private String surname;
    private Float discount;

    public CustomerSurnameDiscountDTO(String surname, Float discount) {
        this.surname = surname;
        this.discount = discount;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSurnameDiscountDTO that = (CustomerSurnameDiscountDTO) o;
        return Objects.equals(surname, that.surname) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, discount);
    }
}
