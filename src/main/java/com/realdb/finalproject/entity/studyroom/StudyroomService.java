package com.realdb.finalproject.entity.studyroom;
import com.realdb.finalproject.entity.StudyRoom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudyroomService {
    private final StudyroomRepo studyroomRepo;
    public List<StudyRoom> getStudyrooms() {
        return studyroomRepo.findAll();
    }
    public void deleteStudyroom(Integer studyroomId) {
        studyroomRepo.deleteById(studyroomId);
    }

}
