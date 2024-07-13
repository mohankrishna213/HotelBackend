package com.example.HotelManagement.Services.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.HotelManagement.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserServiceImplementation implements UserService{
	private final UserRepository userRepository;
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsService()
				{

					@Override
					public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
						// TODO Auto-generated method stub
						return userRepository.findFirstByEmail(username).orElseThrow((() -> new UsernameNotFoundException("user not found")));
					}
			
				
	};

	}
}