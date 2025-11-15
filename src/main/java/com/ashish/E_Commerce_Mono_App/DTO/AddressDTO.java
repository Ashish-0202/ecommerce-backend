package com.ashish.E_Commerce_Mono_App.DTO;

public class AddressDTO {
	
	private String door;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private int pincode;

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

	public AddressDTO(String door, String street, String city, String state, String country, int pincode) {
		super();
		this.door = door;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public AddressDTO() {
		super();
	}
	
	
}
