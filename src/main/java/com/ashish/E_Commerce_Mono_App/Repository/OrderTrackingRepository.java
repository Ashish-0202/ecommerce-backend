package com.ashish.E_Commerce_Mono_App.Repository;

import com.ashish.E_Commerce_Mono_App.Entity.order_tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTrackingRepository extends JpaRepository<order_tracking, Integer> {

    @Query(value = "SELECT * FROM order_tracking WHERE order_id= :id",nativeQuery = true)
    List<order_tracking> findByOrderId(@Param("id") int orderId);
}
