package com.ashish.E_Commerce_Mono_App.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.E_Commerce_Mono_App.DTO.UserDTO;
import com.ashish.E_Commerce_Mono_App.DTO.UserMapper;
import com.ashish.E_Commerce_Mono_App.Entity.users;
import com.ashish.E_Commerce_Mono_App.Repository.UserRepository;

@Slf4j
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> getAllUsers(){
        log.debug("Fetching all users");
		return userRepository.findAll()
				.stream()
				.map(UserMapper::toDTO)
				.toList();
	}
}
