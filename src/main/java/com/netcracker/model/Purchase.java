package com.netcracker.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase")
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_number")
    private int purchaseNumber;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private int seller;
    @Column(nullable = false)
    private int customer;
    @Column(nullable = false)
    private int book;
    @Column(nullable = false)
    private int quantity;
    @Column(name = "purchase_amount",nullable = false)
    private float purchaseAmount;

}
