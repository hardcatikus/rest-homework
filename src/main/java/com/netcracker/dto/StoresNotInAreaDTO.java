package com.netcracker.dto;

public class StoresNotInAreaDTO {

    private String name;
    private String locationArea;

    public StoresNotInAreaDTO(String name, String locationArea) {
        this.name = name;
        this.locationArea = locationArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationArea() {
        return locationArea;
    }

    public void setLocationArea(String locationArea) {
        this.locationArea = locationArea;
    }
}
