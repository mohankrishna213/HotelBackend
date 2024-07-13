package com.example.HotelManagement.Services.customer.room;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.HotelManagement.Repository.RoomRepository;
import com.example.HotelManagement.dto.RoomsResponseDto;
import com.example.HotelManagement.entity.Room;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class RoomServiceImplementation implements RoomService {

	private final RoomRepository roomRepository;
	
	public RoomsResponseDto getAvailableRooms(int pageNumber)
	{
		Pageable pageable=PageRequest.of(pageNumber,6);
		Page<Room> roomPage=roomRepository.findByAvailable(true,pageable);
		RoomsResponseDto roomsResponseDto=new RoomsResponseDto();
		roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
		roomsResponseDto.setTotalPages(roomPage.getTotalPages());
		roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));
		return roomsResponseDto;
	}
}