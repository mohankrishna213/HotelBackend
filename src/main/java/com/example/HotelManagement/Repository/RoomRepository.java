package com.example.HotelManagement.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.HotelManagement.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
	
	Page<Room> findByAvailable(boolean available,Pageable pageable);
}