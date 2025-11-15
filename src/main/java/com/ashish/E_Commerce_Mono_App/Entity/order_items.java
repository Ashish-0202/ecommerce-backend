package com.ashish.E_Commerce_Mono_App.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class order_items {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private orders order;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private products product;

    private int quantity;

    private BigDecimal price;
}
