package com.ashish.E_Commerce_Mono_App.Entity;

import com.ashish.E_Commerce_Mono_App.Constant.Roles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private long phone;
	
	@Enumerated(EnumType.STRING)
	private Roles role;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private addressInfo addressInfo;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public users() {
		
	}

	public addressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(addressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}

	public users(int user_id, String username, String password, String email, long phone, Roles role,
			com.ashish.E_Commerce_Mono_App.Entity.addressInfo addressInfo) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.addressInfo = addressInfo;
	}

	
	
	
}
