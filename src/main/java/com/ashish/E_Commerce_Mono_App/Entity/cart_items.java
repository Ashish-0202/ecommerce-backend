package com.ashish.E_Commerce_Mono_App.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class cart_items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_item_id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private cart ct;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private products product;

    private int quantity;

    private BigDecimal price;
}
