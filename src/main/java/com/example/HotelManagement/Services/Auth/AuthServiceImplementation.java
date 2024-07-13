package com.example.HotelManagement.Services.Auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HotelManagement.Enum.UserRole;
import com.example.HotelManagement.Repository.UserRepository;
import com.example.HotelManagement.dto.SignupRequest;
import com.example.HotelManagement.dto.UserDto;
import com.example.HotelManagement.entity.User;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplementation implements AuthService {
    
    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserrole(UserRole.ADMIN);
        if (adminAccount.isEmpty()) {
            User user = new User();
            user.setEmail("admin@gmail.com");
            user.setName("Admin");
            user.setUserrole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin Account Created Successfully");
        } else {
            System.out.println("Admin Account already exists");
        }
    }

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        if (userRepository.findFirstByEmail(signupRequest.getEmail()).isPresent()) {
            throw new EntityExistsException("User Already Exists with email " + signupRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserrole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setDesignation(signupRequest.getDesignation());
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }
}
