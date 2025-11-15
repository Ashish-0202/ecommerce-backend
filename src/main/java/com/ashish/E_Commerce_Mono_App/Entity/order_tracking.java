package com.ashish.E_Commerce_Mono_App.Entity;

import com.ashish.E_Commerce_Mono_App.Constant.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_tracking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class order_tracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int track_id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private orders order;

    @Enumerated(EnumType.STRING)
    private OrderStatus order_status;

    private String description;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt=LocalDateTime.now();
}
