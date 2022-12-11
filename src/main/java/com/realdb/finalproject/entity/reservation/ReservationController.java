package com.realdb.finalproject.entity.reservation;
import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.entity.studyroom.StudyRoom;
import com.realdb.finalproject.utility.BuildResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@AllArgsConstructor
public class ReservationController {
    public static final String UPDATE_SUCCESSFULLY = "Update reservation Successfully";
    public static final String DELETE_SUCCESSFULLY = "Delete Reservation successfully";

    private final ReservationService reservationService;

    @PostMapping("/add")
    public void addReservation (@RequestBody Reservation reservation){
        reservationService.addReservation(reservation);
    }

    @GetMapping(path = "/find/{userid}")
    public List<Reservation> findAllResByUserID(@PathVariable("userid") Integer userid){
        return reservationService.findAllResByUserID(userid);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<HttpResponse> updateReservation(
            @RequestParam("id") Integer id,
            @RequestParam(required = false) LocalDate resDate,
            @RequestParam(required =  false) String resTimeslot,
            @RequestParam(required = false) StudyRoom studyRoomRoom
           ) {

        reservationService.updateReservation(id, resDate, resTimeslot, studyRoomRoom);
        return BuildResponse.build(HttpStatus.ACCEPTED, UPDATE_SUCCESSFULLY);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteReservation(@RequestParam("id") Integer id) {
        reservationService.deleteById(id);
        return BuildResponse.build(HttpStatus.NO_CONTENT, DELETE_SUCCESSFULLY);
    }
}
