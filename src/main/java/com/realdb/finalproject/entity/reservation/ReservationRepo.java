package com.realdb.finalproject.entity.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {
    @Transactional
    @Query("SELECT r FROM Reservation r WHERE r.customer.id = ?1")
    List<Reservation> findAllResByUserID(Integer id);
}
