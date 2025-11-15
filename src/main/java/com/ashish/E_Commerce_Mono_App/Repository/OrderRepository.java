package com.ashish.E_Commerce_Mono_App.Repository;

import com.ashish.E_Commerce_Mono_App.Entity.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<orders,Integer> {

    @Query(value = "select * from orders o where o.user_id= :userid",nativeQuery = true)
    List<orders> getOrderByUserId(@Param("userid") int user_id);
}
