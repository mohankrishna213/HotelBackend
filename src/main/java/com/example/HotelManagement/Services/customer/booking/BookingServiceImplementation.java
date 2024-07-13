package com.example.HotelManagement.Services.customer.booking;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.HotelManagement.Enum.ReservationStatus;
import com.example.HotelManagement.Repository.ReservationRepository;
import com.example.HotelManagement.Repository.RoomRepository;
import com.example.HotelManagement.Repository.UserRepository;
import com.example.HotelManagement.dto.ReservationDto;
import com.example.HotelManagement.dto.ReservationResponseDto;
import com.example.HotelManagement.entity.Reservation;
import com.example.HotelManagement.entity.Room;
import com.example.HotelManagement.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class BookingServiceImplementation implements BookingService {
	private final UserRepository userRepository;
	private final RoomRepository roomRepository;
	private final ReservationRepository reservationRepository;
	public static final int SEARCH_RESULT_PER_PAGE=4;
	public boolean postReservation(ReservationDto reservationDto)
	{
		Optional<User> optionalUser=userRepository.findById(reservationDto.getUserId());
		Optional<Room> optionalRoom=roomRepository.findById(reservationDto.getUserId());
		
		if(optionalRoom.isPresent() && optionalUser.isPresent())
		{
			Reservation reservation =new Reservation();
			reservation.setRoom(optionalRoom.get());
			reservation.setUser(optionalUser.get());
			reservation.setCheckInDate(reservationDto.getCheckInDate());
			reservation.setCheckOutDate(reservationDto.getCheckOutDate());
			reservation.setReservationStatus(ReservationStatus.PENDING);	
			Long days =ChronoUnit.DAYS.between(reservationDto.getCheckInDate(),reservationDto.getCheckOutDate());
			reservation.setPrice(Long.parseLong(optionalRoom.get().getPrice())* days);
			reservationRepository.save(reservation);
			return true;
			
			
		}
		return false;
		
		
		}
	public ReservationResponseDto getAllReservationByUserId(Long userId,int pageNumber)
	{
		Pageable pageable =PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
		Page<Reservation> reservationPage=reservationRepository.findAllByUserId(pageable,userId);
		ReservationResponseDto reservationResponseDto=new ReservationResponseDto();
		reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
		reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
		reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
		return reservationResponseDto;
	}

}