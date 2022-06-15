package com.netcracker.dto;

import java.util.Objects;

public class CustomerDTO {

    private String areaOfResidence;

    public CustomerDTO(String areaOfResidence) {
        this.areaOfResidence = areaOfResidence;
    }

    public String getAreaOfResidence() {
        return areaOfResidence;
    }

    public void setAreaOfResidence(String areaOfResidence) {
        this.areaOfResidence = areaOfResidence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(areaOfResidence, that.areaOfResidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaOfResidence);
    }
}
