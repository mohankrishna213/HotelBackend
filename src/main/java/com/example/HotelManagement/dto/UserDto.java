package com.example.HotelManagement.dto;

import com.example.HotelManagement.Enum.UserRole;

import lombok.Data;
@Data
public class UserDto {
	private Long id;
	private String email;
	private String name;
	private UserRole userRole;
	private String Designation;
	

}
