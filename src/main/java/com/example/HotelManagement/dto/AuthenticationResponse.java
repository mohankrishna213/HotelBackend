package com.example.HotelManagement.dto;

import com.example.HotelManagement.Enum.UserRole;

import lombok.Data;

@Data

public class AuthenticationResponse {
	
	private String jwt;
	private Long userId;
	private UserRole userRole;

}