package com.example.HotelManagement.Services.customer.room;

import com.example.HotelManagement.dto.RoomsResponseDto;

public interface RoomService {
	RoomsResponseDto getAvailableRooms(int pageNumber);

}