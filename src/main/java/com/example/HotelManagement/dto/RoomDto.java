package com.example.HotelManagement.dto;

import lombok.Data;

@Data
public class RoomDto {
	private long id;
	private String name;
	private String type;
	private String price;
	private boolean available;

}