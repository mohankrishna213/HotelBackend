package com.example.HotelManagement.entity;

import com.example.HotelManagement.dto.RoomDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Room {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String type;
	private String price;
	private boolean available;
	public RoomDto getRoomDto()
	{
		RoomDto roomDto=new RoomDto();
		roomDto.setId(id);
		roomDto.setName(name);
		roomDto.setType(type);
		roomDto.setAvailable(available);
		roomDto.setPrice(price);
		return roomDto;
	}

}