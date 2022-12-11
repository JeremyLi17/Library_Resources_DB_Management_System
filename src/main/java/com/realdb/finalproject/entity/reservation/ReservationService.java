package com.realdb.finalproject.entity.reservation;
import com.realdb.finalproject.entity.studyroom.StudyRoom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
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

    public void updateReservation (
            @PathVariable("id") Integer id,
            @RequestParam(required = false) LocalDate resDate,
            @RequestParam(required = false) String resTimeslot,
            @RequestParam(required = false) StudyRoom studyRoomRoom
    ){
        Reservation reservation = reservationRepo.findById(id).orElseThrow(() -> new IllegalStateException(
                "Author with id " + id + " does not exist"
        ));
        if (resDate != null && !Objects.equals(reservation.getDate(), resDate)) {
            reservation.setDate(resDate);
        }
        if(resTimeslot != null && !Objects.equals(reservation.getTimeslot(), resTimeslot)){
            reservation.setTimeslot(resTimeslot);
        }
        if(studyRoomRoom != null && !Objects.equals(reservation.getStudyRoom(), studyRoomRoom)){
            reservation.setStudyRoom(studyRoomRoom);
        }

    }

    public void deleteById(Integer id) {
        reservationRepo.deleteById(id);
    }
}
