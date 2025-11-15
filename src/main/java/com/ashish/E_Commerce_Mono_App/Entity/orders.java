package com.ashish.E_Commerce_Mono_App.Entity;

import com.ashish.E_Commerce_Mono_App.Constant.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class orders {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private users user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private BigDecimal total_amount;

    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name = "shipment_address_id",nullable = false)
    private addressInfo shipment_address;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<order_items> orderItemsList;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<order_tracking>  orderTrackingList;
}
