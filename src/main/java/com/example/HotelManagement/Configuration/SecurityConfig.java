package com.example.HotelManagement.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.HotelManagement.Enum.UserRole;
import com.example.HotelManagement.Services.jwt.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final UserService userService;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/admin/**").hasAnyAuthority(UserRole.ADMIN.name())
				.requestMatchers("/api/customer/**").hasAnyAuthority(UserRole.CUSTOMER.name())
				.anyRequest().authenticated())
            	.sessionManagement(manager->manager.sessionCreationPolicy(STATELESS))
            	.authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter,
            			UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
    @Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}
    
    @Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService.userDetailsService());
		authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
		return authProvider;
	}
    
}
