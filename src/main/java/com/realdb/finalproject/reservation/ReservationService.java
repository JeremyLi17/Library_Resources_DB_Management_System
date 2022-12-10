package com.realdb.finalproject.reservation;
import com.realdb.finalproject.entity.Customer;
import com.realdb.finalproject.entity.Reservation;
import com.realdb.finalproject.entity.StudyRoom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepo reservationRepo;
    public void addReservation(Reservation reservation){
        reservationRepo.save(reservation);
    }
    public List<Reservation> findAllResByUserID (Integer id){
        return reservationRepo.findAllResByUserID(id);
    }
    public Optional<Reservation> getReservation(Integer id){
        return reservationRepo.findById(id);
    }
    public void updateReservation (
            @PathVariable("id") Integer id,
            @RequestParam(required = false) LocalDate resDate,
            @RequestParam(required = false) String resTimeslot,
            @RequestParam(required = false) StudyRoom studyRoomRoom
    ){
        Reservation reservation = reservationRepo.findById(id).orElseThrow(() -> new IllegalStateException(
                "Author with id " + id + " does not exist"
        ));
        if (resDate != null && !Objects.equals(reservation.getResDate(), resDate)) {
            reservation.setResDate(resDate);
        }
        if(resTimeslot != null && !Objects.equals(reservation.getResTimeslot(), resTimeslot)){
            reservation.setResTimeslot(resTimeslot);
        }
        if(studyRoomRoom != null && !Objects.equals(reservation.getStudyRoomRoom(), studyRoomRoom)){
            reservation.setStudyRoomRoom(studyRoomRoom);
        }

    }
}
