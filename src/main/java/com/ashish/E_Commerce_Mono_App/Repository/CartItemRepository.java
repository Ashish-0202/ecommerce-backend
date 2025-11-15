package com.ashish.E_Commerce_Mono_App.Repository;

import com.ashish.E_Commerce_Mono_App.Entity.cart_items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<cart_items,Integer> {
}
