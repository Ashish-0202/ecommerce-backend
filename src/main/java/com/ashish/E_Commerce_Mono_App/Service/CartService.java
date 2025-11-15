package com.ashish.E_Commerce_Mono_App.Service;

import com.ashish.E_Commerce_Mono_App.DTO.UserCartItemResponseDTO;
import com.ashish.E_Commerce_Mono_App.DTO.UserCartResponseDTO;
import com.ashish.E_Commerce_Mono_App.Entity.cart;
import com.ashish.E_Commerce_Mono_App.Entity.cart_items;
import com.ashish.E_Commerce_Mono_App.Entity.products;
import com.ashish.E_Commerce_Mono_App.Repository.CartItemRepository;
import com.ashish.E_Commerce_Mono_App.Repository.CartRepository;
import com.ashish.E_Commerce_Mono_App.Repository.ProductRepository;
import com.ashish.E_Commerce_Mono_App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    int initialQuantity=1;

    public String addToCart(int userId, int product_id){
        //Check if user has associated a cart
        cart c1 = cartRepository.findCartByUserId(userId)
                .orElseGet(()-> {
                    cart c2 = new cart();
                    c2.setUser(userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found.!")));
                    c2.setTotal_amount(BigDecimal.ZERO);
                    c2.setLast_modified(Timestamp.valueOf(LocalDateTime.now()));
                    return cartRepository.save(c2);
                });
        //Get the product detail using iD
        products p1 = productRepository.findById(product_id)
                .orElseThrow(()-> new RuntimeException(product_id+" Product ID is invalid..!"));

        //Adding item into cart
        cart_items item = new cart_items();
        item.setCt(c1);
        item.setQuantity(initialQuantity);
        item.setProduct(p1);
        item.setPrice(p1.getPrice().multiply(BigDecimal.valueOf(initialQuantity)));
        cartItemRepository.save(item);

        //update amount in cart
        BigDecimal total= c1.getTotal_amount().add(p1.getPrice());
        c1.setTotal_amount(total);
        cartRepository.save(c1);

        return "It has been successfully added to cart..!";
    }

    public UserCartResponseDTO getUserCart(int userId){
        cart c1 = cartRepository.findCartByUserId(userId)
                .orElseThrow(()-> new RuntimeException("Cart is empty for user: "+userId));

        UserCartResponseDTO userCartResponseDTO = new UserCartResponseDTO();
        userCartResponseDTO.setTotal_amount(c1.getTotal_amount());

        List<UserCartItemResponseDTO> cartItemResponseDTOS = new ArrayList<>();

        for(cart_items items: c1.getItems()){
            products p1 = productRepository.findById(items.getProduct().getProduct_id())
                    .orElseThrow(()-> new RuntimeException(" Product ID is invalid..!"));

            UserCartItemResponseDTO responseDTO = new UserCartItemResponseDTO();
            responseDTO.setProduct_name(p1.getProduct_name());
            responseDTO.setQuantity(items.getQuantity());
            responseDTO.setPrice(items.getPrice());
            cartItemResponseDTOS.add(responseDTO);
        }
        userCartResponseDTO.setCartItemResponseDTOS(cartItemResponseDTOS);

        return userCartResponseDTO;
    }
}
