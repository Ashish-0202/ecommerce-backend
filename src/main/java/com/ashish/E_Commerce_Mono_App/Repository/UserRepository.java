package com.ashish.E_Commerce_Mono_App.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashish.E_Commerce_Mono_App.Entity.users;

@Repository
public interface UserRepository extends JpaRepository<users, Integer>{

}
