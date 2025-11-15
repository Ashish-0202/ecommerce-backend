package com.ashish.E_Commerce_Mono_App.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    private String message;

    private Timestamp orderDate;

    private BigDecimal TotalAmount;

    private List<OrderItemResponseDTO> orderItemResponseDTOS;

    private String shipment_address;
}
