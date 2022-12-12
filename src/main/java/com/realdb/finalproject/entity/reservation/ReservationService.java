package com.realdb.finalproject.entity.reservation;
import com.realdb.finalproject.customer.Customer;
import com.realdb.finalproject.customer.CustomerRepo;
import com.realdb.finalproject.entity.studyroom.StudyRoom;
import com.realdb.finalproject.entity.studyroom.StudyRoomRepo;
import com.realdb.finalproject.exception.domain.CustomerNotFoundException;
import com.realdb.finalproject.exception.domain.ReservationNotFoundException;
import com.realdb.finalproject.exception.domain.StudyRoomNotFoundException;
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
    private final CustomerRepo customerRepo;
    private final StudyRoomRepo studyRoomRepo;

    public Reservation addReservation(String username, LocalDate date, String timeSlot, Integer roomId)
            throws StudyRoomNotFoundException, CustomerNotFoundException {
        Optional<Customer> customerOpt = customerRepo.findCustomerByUsername(username);
        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("Customer: " + username + " not found");
        }
        Optional<StudyRoom> studyRoomOpt = studyRoomRepo.findById(roomId);
        if (studyRoomOpt.isEmpty()) {
            throw new StudyRoomNotFoundException("Study room: " + roomId + " not found");
        }
        Reservation reservation = new Reservation();
        reservation.setDate(date);
        reservation.setTimeslot(timeSlot);
        reservation.setStudyRoom(studyRoomOpt.get());
        reservation.setCustomer(customerOpt.get());
        reservationRepo.save(reservation);
        return reservation;
    }

    public List<Reservation> findAllResByCustomerID (Integer id){
        return reservationRepo.findAllResByCustomerID(id);
    }

    @Transactional
    public void updateReservation(Long id, LocalDate resDate, String resTimeslot, Integer studyRoomId)
            throws ReservationNotFoundException, StudyRoomNotFoundException {
        Optional<Reservation> reservationOpt = reservationRepo.findById(id);
        if (reservationOpt.isEmpty()) {
            throw new ReservationNotFoundException("Reservation: " + id + " not found");
        }
        Reservation reservation = reservationOpt.get();

        if (studyRoomId != null) {
            Optional<StudyRoom> roomOpt = studyRoomRepo.findById(studyRoomId);
            if (roomOpt.isEmpty()) {
                throw new StudyRoomNotFoundException("Study room: " + studyRoomId + " not found");
            }
            if (Objects.equals(reservation.getStudyRoom().getId(), studyRoomId)) {
                reservation.setStudyRoom(roomOpt.get());
            }
        }

        if (resDate != null && !Objects.equals(reservation.getDate(), resDate)) {
            reservation.setDate(resDate);
        }

        if ( resTimeslot != null && !Objects.equals(reservation.getTimeslot(), resTimeslot)){
            reservation.setTimeslot(resTimeslot);
        }

    }

    public void deleteById(Long id) {
        reservationRepo.deleteById(id);
    }
}
