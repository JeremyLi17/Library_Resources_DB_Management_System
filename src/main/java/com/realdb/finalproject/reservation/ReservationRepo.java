package com.realdb.finalproject.reservation;

import com.realdb.finalproject.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {
    @Transactional
    @Query("SELECT r FROM Reservation r WHERE r.customerC.id = ?1")
    List<Reservation> findAllResByUserID(Integer id);
}