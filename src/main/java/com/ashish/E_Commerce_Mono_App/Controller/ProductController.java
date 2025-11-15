package com.ashish.E_Commerce_Mono_App.Controller;

import com.ashish.E_Commerce_Mono_App.DTO.ViewProductsDTO;
import com.ashish.E_Commerce_Mono_App.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/viewAll")
    public Page<ViewProductsDTO> getAll(@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAll(pageable);
    }
}
