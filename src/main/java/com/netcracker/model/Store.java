package com.netcracker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "store")
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(name = "location_area",nullable = false)
    private String locationArea;
    @Column(name = "commission_fee",nullable = false)
    private float commissionFee;

}
