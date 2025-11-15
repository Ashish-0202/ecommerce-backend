package com.ashish.E_Commerce_Mono_App.DTO;

import com.ashish.E_Commerce_Mono_App.Constant.Roles;
import com.ashish.E_Commerce_Mono_App.Entity.addressInfo;

public class UserDTO {
	
	private String username;
	
	private String email;
	
	private long phone;
	
	private Roles role;
	
	private AddressDTO addressDTO;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public AddressDTO getAddressDTO() {
		return addressDTO;
	}

	public void setAddressDTO(AddressDTO addressDTO) {
		this.addressDTO = addressDTO;
	}

	public UserDTO() {
		super();
	}
	
	public UserDTO(String username, String email, long phone, Roles role, AddressDTO addressDTO) {
		super();
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.addressDTO = addressDTO;
	}
	
}
