package com.ashish.E_Commerce_Mono_App.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.E_Commerce_Mono_App.DTO.UserDTO;
import com.ashish.E_Commerce_Mono_App.Entity.users;
import com.ashish.E_Commerce_Mono_App.Service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<UserDTO> getAll(){
		return userService.getAllUsers();
	}
}
