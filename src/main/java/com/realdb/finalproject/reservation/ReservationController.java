package com.realdb.finalproject.reservation;
import com.realdb.finalproject.entity.StudyRoom;
import com.realdb.finalproject.entity.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping()
    public void addReservation (@RequestBody Reservation reservation){
        reservationService.addReservation(reservation);
    }
    @GetMapping()
    public Optional<Reservation> getReservation(@RequestParam Integer id) {
        return reservationService.getReservation(id);
    }
    @GetMapping(path = "{userid}")
    public List<Reservation> findAllResByUserID(@RequestParam Integer userid){
        return reservationService.findAllResByUserID(userid);
    }
    @PutMapping(path = "{reservationid}")
    public void updateReservation(
            @PathVariable("id") Integer id,
            @RequestParam(required = false) LocalDate resDate,
            @RequestParam(required =  false) String resTimeslot,
            @RequestParam(required = false) StudyRoom studyRoomRoom
           ) {

        reservationService.updateReservation(id, resDate, resTimeslot, studyRoomRoom);
    }

}
