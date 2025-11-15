package com.ashish.E_Commerce_Mono_App.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewProductsDTO {

    private String product_name;

    private String description;

    private BigDecimal product_price;

    private int stock_Available;

    private String category;
}
