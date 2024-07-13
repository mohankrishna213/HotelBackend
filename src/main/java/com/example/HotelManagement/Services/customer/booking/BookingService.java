package com.example.HotelManagement.Services.customer.booking;

import com.example.HotelManagement.dto.ReservationDto;
import com.example.HotelManagement.dto.ReservationResponseDto;

public interface BookingService {
	boolean postReservation(ReservationDto reservationDto);
	ReservationResponseDto getAllReservationByUserId(Long userId,int pageNumber);

}