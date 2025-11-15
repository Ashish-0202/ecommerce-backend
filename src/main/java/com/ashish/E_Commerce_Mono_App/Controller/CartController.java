package com.ashish.E_Commerce_Mono_App.Controller;

import com.ashish.E_Commerce_Mono_App.DTO.UserCartResponseDTO;
import com.ashish.E_Commerce_Mono_App.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/addToCart")
    public ResponseEntity<String> addItemCart(@RequestParam("userId")int userId,@RequestParam("productId")int productId){
        return ResponseEntity.ok(cartService.addToCart(userId,productId));
    }

    @GetMapping("/viewCart/{userId}")
    public ResponseEntity<UserCartResponseDTO> getCartByUser(@PathVariable("userId") int userId){
        return ResponseEntity.ok(cartService.getUserCart(userId));
    }
}
