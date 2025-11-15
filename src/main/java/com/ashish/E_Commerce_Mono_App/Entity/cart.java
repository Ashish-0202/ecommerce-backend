package com.ashish.E_Commerce_Mono_App.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cart_id;

    @OneToOne
    @JoinColumn(name="user_id",nullable = false)
    private users user;

    private Timestamp last_modified;

    private BigDecimal total_amount;

    @OneToMany(mappedBy = "ct",cascade =CascadeType.ALL)
    private List<cart_items> items;
}
