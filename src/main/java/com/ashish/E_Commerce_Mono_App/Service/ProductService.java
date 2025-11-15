package com.ashish.E_Commerce_Mono_App.Service;

import com.ashish.E_Commerce_Mono_App.DTO.ViewProductsDTO;
import com.ashish.E_Commerce_Mono_App.Entity.products;
import com.ashish.E_Commerce_Mono_App.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<ViewProductsDTO> getAll(Pageable pageable){
        log.info("Fetching all products");
        return productRepository.findAll(pageable)
                .map(ProductService::mapProduct);
    }

    public static ViewProductsDTO mapProduct(products product){
        log.info("Mapping product to DTO");
        ViewProductsDTO productsDTO = new ViewProductsDTO();

        productsDTO.setProduct_name(product.getProduct_name());
        productsDTO.setProduct_price(product.getPrice());
        productsDTO.setDescription(product.getProduct_desc());
        productsDTO.setStock_Available(product.getStock_avail());
        productsDTO.setCategory(product.getCat().getCategory_name());

        return productsDTO;
    }
}
