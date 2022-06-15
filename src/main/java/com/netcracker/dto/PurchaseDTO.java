package com.netcracker.dto;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class PurchaseDTO {

    private Integer month;

    public PurchaseDTO(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        this.month = cal.get(Calendar.MONTH);
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseDTO that = (PurchaseDTO) o;
        return Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month);
    }
}
