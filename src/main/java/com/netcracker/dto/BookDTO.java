package com.netcracker.dto;

import java.util.Objects;

public class BookDTO {

    private String name;
    private Float price;

    public BookDTO(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(name, bookDTO.name) && Objects.equals(price, bookDTO.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
