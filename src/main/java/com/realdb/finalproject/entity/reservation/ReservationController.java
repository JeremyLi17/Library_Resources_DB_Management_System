package com.realdb.finalproject.entity.reservation;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.entity.studyroom.StudyRoom;
import com.realdb.finalproject.exception.domain.CustomerNotFoundException;
import com.realdb.finalproject.exception.domain.ReservationNotFoundException;
import com.realdb.finalproject.exception.domain.StudyRoomNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.realdb.finalproject.utility.BuildResponse.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/reservation")
@AllArgsConstructor
public class ReservationController {
    public static final String UPDATE_SUCCESSFULLY = "Update reservation Successfully";
    public static final String DELETE_SUCCESSFULLY = "Delete Reservation successfully";

    private final ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<Reservation> addReservation (@RequestBody Reservation reservation)
            throws StudyRoomNotFoundException, CustomerNotFoundException {
        Reservation newReservation = reservationService.addReservation(
                reservation.getCustomer().getUsername(),
                reservation.getDate(),
                reservation.getTimeslot(),
                reservation.getStudyRoom().getId()
        );
        return new ResponseEntity<>(newReservation, CREATED);
    }

    @GetMapping(path = "/list/{customerId}")
    public ResponseEntity<List<Reservation>> findAllResByUserID(
            @PathVariable("customerId") Integer customerId){
        return new ResponseEntity<>(reservationService.findAllResByCustomerID(customerId), OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<HttpResponse> updateReservation(
            @RequestParam("id") Long id,
            @RequestParam(name = "resDate", required = false) LocalDate resDate,
            @RequestParam(name = "resTimeSlot", required =  false) String resTimeSlot,
            @RequestParam(name = "studyRoomId", required = false) Integer studyRoomId)
            throws ReservationNotFoundException, StudyRoomNotFoundException {
        reservationService.updateReservation(id, resDate, resTimeSlot, studyRoomId);
        return build(ACCEPTED, UPDATE_SUCCESSFULLY);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteReservation(@RequestParam("id") Long id) {
        reservationService.deleteById(id);
        return build(NO_CONTENT, DELETE_SUCCESSFULLY);
    }
}
