package com.ashish.E_Commerce_Mono_App.Repository;

import com.ashish.E_Commerce_Mono_App.Entity.addressInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<addressInfo,Integer> {

    @Query(value = "SELECT * FROM address_info a where a.address_id= :addressId AND a.user_id= :userId",nativeQuery = true)
    addressInfo findAddByUserId(@Param("addressId") int addressId, @Param("userId") int userId);
}
