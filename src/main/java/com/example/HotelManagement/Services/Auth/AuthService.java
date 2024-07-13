package com.example.HotelManagement.Services.Auth;

import org.springframework.stereotype.Service;

import com.example.HotelManagement.dto.SignupRequest;
import com.example.HotelManagement.dto.UserDto;
@Service
public interface AuthService {
	UserDto createUser(SignupRequest signupRequest);

}
