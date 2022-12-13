package com.realdb.finalproject.entity.studyroom;
import com.realdb.finalproject.exception.domain.StudyRoomNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class StudyRoomService {
    private final StudyRoomRepo studyroomRepo;

    public List<StudyRoom> findAvailableRoomByDate(LocalDate date, String timeslot) {
        return studyroomRepo.findAvailableRoomByDate(date,timeslot);
    }

    public StudyRoom addStudyRoom(Integer capacity) {
        StudyRoom room = new StudyRoom();
        room.setCapacity(capacity);
        studyroomRepo.save(room);
        return room;
    }

    public StudyRoom findRoomById(Integer id) throws StudyRoomNotFoundException {
        return validStudyRoomId(id);
    }

    public void deleteStudyRoom(Integer id) throws StudyRoomNotFoundException {
        validStudyRoomId(id);
        studyroomRepo.deleteById(id);
    }

    @Transactional
    public void updateCapacity(Integer id, Integer capacity) throws StudyRoomNotFoundException {
        StudyRoom room = validStudyRoomId(id);
        room.setCapacity(capacity);
        studyroomRepo.save(room);
    }

    private StudyRoom validStudyRoomId(Integer id) throws StudyRoomNotFoundException {
        Optional<StudyRoom> room = studyroomRepo.findById(id);
        if (room.isEmpty()) {
            throw new StudyRoomNotFoundException("Study room not found by id: " + id);
        }
        return room.get();
    }
}
