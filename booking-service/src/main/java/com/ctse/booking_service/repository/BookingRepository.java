package com.ctse.booking_service.repository;

import com.ctse.booking_service.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
	List<Booking> findByUserId(String userId);
}
