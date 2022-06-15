package com.netcracker.dto;

public class BookSoldInWarehouseAreaDTO {

    private String name;
    private String warehouse;
    private Integer quantity;
    private Float price;

    public BookSoldInWarehouseAreaDTO(String name, String warehouse, Integer quantity, Float price) {
        this.name = name;
        this.warehouse = warehouse;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
