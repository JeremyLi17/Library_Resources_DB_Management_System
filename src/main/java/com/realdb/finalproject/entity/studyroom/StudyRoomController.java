package com.realdb.finalproject.entity.studyroom;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.exception.domain.StudyRoomNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.realdb.finalproject.utility.BuildResponse.build;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/studyroom")
@AllArgsConstructor
public class StudyRoomController {
    public static final String DELETE_SUCCESSFULLY = "Delete study room successfully";
    public static final String CAPACITY_UPDATE_SUCCESSFULLY = "Capacity update successfully";

    private final StudyRoomService studyroomService;

    @GetMapping("/list")
    public ResponseEntity<List<StudyRoom>> findAvailableRoomByDate(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestParam("timeslot") String timeslot) {
        List<StudyRoom> rooms = studyroomService.findAvailableRoomByDate(date, timeslot);
        return new ResponseEntity<>(rooms, OK);
    }

    @GetMapping ("/find/{id}")
    ResponseEntity<StudyRoom> findRoomById(@PathVariable("id") Integer id)
            throws StudyRoomNotFoundException {
        StudyRoom room = studyroomService.findRoomById(id);
        return new ResponseEntity<>(room, OK);
    }

    @PostMapping("/add")
    public ResponseEntity<StudyRoom> addStudyRoom(@RequestBody StudyRoom studyRoom) {
        StudyRoom room = studyroomService.addStudyRoom(studyRoom.getCapacity());
        return new ResponseEntity<>(room, CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateCapacity(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "capacity") Integer capacity)
            throws StudyRoomNotFoundException {
        studyroomService.updateCapacity(id, capacity);
        return build(ACCEPTED, CAPACITY_UPDATE_SUCCESSFULLY);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpResponse> deleteStudyRoom(@RequestParam("id") Integer id)
            throws StudyRoomNotFoundException {
        studyroomService.deleteStudyRoom(id);
        return build(NO_CONTENT, DELETE_SUCCESSFULLY);
    }
}
