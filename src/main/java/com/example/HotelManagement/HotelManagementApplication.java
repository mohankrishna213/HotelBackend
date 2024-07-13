package com.example.HotelManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.HotelManagement"})
@EntityScan(basePackages = {"com.example.HotelManagement.entity"})
@EnableJpaRepositories(basePackages = {"com.example.HotelManagement.Repository"})
public class HotelManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
	}

}
