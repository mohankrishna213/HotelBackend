package com.example.HotelManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HotelManagement.Enum.UserRole;
import com.example.HotelManagement.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findFirstByEmail(String email);
	Optional<User> findByUserrole(UserRole userrole);

}
