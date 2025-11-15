package com.ashish.E_Commerce_Mono_App.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashish.E_Commerce_Mono_App.Entity.products;

@Repository
public interface ProductRepository extends JpaRepository<products, Integer>{

    Page<products> findAll(Pageable pageable);

}
