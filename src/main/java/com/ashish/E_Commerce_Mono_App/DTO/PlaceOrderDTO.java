package com.ashish.E_Commerce_Mono_App.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDTO {
    private int user_id;
    private List<PlaceOrderItemsDTO> orderItemsDTOS;
    private int shipment_address_id;
}
