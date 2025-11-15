package com.ashish.E_Commerce_Mono_App.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTrackDTO {

    private int orderId;
    private int customerId;
    private BigDecimal totalPrice;
    private List<TrackingDetailsDTO>  trackingDetails;
}
