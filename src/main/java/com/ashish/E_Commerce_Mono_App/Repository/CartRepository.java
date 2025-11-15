package com.ashish.E_Commerce_Mono_App.Repository;

import com.ashish.E_Commerce_Mono_App.Entity.cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<cart,Integer> {

    @Query(value = "SELECT * FROM cart c where c.user_id= :userId",nativeQuery = true)
    Optional<cart> findCartByUserId(@Param("userId") int userId);
}
