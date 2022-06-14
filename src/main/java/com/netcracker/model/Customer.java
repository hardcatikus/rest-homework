package com.netcracker.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String surname;
    @Column(name = "area_of_residence", nullable = false)
    private String areaOfResidence;
    @Column(nullable = true)
    private float discount;

}
