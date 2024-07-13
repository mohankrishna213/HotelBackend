package com.example.HotelManagement.entity;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.HotelManagement.Enum.UserRole;
import com.example.HotelManagement.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "user")
public class User implements UserDetails{
/**
	 * 
	 */
	private static final long serialVersionUID = 2923095348891362949L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private String name;
private String designation;
@Column(nullable = false, unique = true)
private String email;
@Column(nullable = false)
private String password;
@Enumerated(EnumType.STRING)
@Column(nullable = false)
private UserRole userrole;
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return List.of(new SimpleGrantedAuthority(userrole.name()));
}
@Override
public String getUsername() {
	// TODO Auto-generated method stub
	return email;
}
@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
}
public UserDto getUserDto()
{
	UserDto dto=new UserDto();
	dto.setId(id);
	dto.setName(name);
	dto.setEmail(email);
	dto.setUserRole(userrole);
	dto.setDesignation(designation);
	return dto;
}

}
