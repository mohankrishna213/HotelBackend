package com.example.HotelManagement.Services.admin.rooms;

import com.example.HotelManagement.dto.RoomDto;
import com.example.HotelManagement.dto.RoomsResponseDto;

public interface RoomsService {
	boolean postRoom(RoomDto roomDto);
	RoomsResponseDto getAllRooms(int pageNumber);
	RoomDto getRoomById(Long id);
	boolean updateRoom(Long id,RoomDto roomDto);
	void deleteRoom(Long id);
}