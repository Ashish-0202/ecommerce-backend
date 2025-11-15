package com.ashish.E_Commerce_Mono_App.DTO;

import com.ashish.E_Commerce_Mono_App.Entity.users;

public class UserMapper {
	
	public static UserDTO toDTO(users user) {
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUsername(user.getUsername());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		userDTO.setRole(user.getRole());
		
		if(user.getAddressInfo()!=null) {
			AddressDTO addressDTO = new AddressDTO();
			addressDTO.setDoor(user.getAddressInfo().getDoor());
			addressDTO.setStreet(user.getAddressInfo().getStreet());
			addressDTO.setCity(user.getAddressInfo().getCity());
			addressDTO.setState(user.getAddressInfo().getState());
			addressDTO.setCountry(user.getAddressInfo().getCountry());
			addressDTO.setPincode(user.getAddressInfo().getPincode());
			userDTO.setAddressDTO(addressDTO);
		}
		
		return userDTO;
	}
}
