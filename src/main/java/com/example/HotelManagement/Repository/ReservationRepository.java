package com.example.HotelManagement.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HotelManagement.entity.Reservation;

@Repository

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	Page<Reservation> findAllByUserId(Pageable pageable,Long userId);

}