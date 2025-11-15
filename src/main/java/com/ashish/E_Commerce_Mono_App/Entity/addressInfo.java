package com.ashish.E_Commerce_Mono_App.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="address_info")
public class addressInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int address_id;
	
	private String door;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private int pincode;
	
	@OneToOne
	@JoinColumn(name = "user_id",nullable = false)
	private users user;

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}

	public addressInfo(int address_id, String door, String street, String city, String state, String country,
			int pincode, users user) {
		super();
		this.address_id = address_id;
		this.door = door;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
		this.user = user;
	}

	public addressInfo() {
		super();
	}
	
	

}
