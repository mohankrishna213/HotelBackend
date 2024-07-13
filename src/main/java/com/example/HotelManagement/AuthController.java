package com.example.HotelManagement;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelManagement.Repository.UserRepository;
import com.example.HotelManagement.Services.Auth.AuthService;
import com.example.HotelManagement.Services.jwt.UserService;
import com.example.HotelManagement.dto.AuthenticationRequest;
import com.example.HotelManagement.dto.AuthenticationResponse;
import com.example.HotelManagement.dto.SignupRequest;
import com.example.HotelManagement.dto.UserDto;
import com.example.HotelManagement.entity.User;
import com.example.HotelManagement.util.JwtUtil;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final UserService userService;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        try {
            UserDto createdUser = authService.createUser(signupRequest);
            return new ResponseEntity<>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException entityExistException) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>("User not created, try again later", HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
	{
		try
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
			
		}
		catch(BadCredentialsException e)
		{
			throw new BadCredentialsException("Incorrect username or password");
		}
		final UserDetails userDetails=userService.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails);
		AuthenticationResponse authenticationResponse=new AuthenticationResponse();
		if(optionalUser.isPresent())
		{
			authenticationResponse.setJwt(jwt);
			authenticationResponse.setUserRole(optionalUser.get().getUserrole());
			authenticationResponse.setUserId(optionalUser.get().getId());
		}
		return authenticationResponse;
		
	}
    
}
