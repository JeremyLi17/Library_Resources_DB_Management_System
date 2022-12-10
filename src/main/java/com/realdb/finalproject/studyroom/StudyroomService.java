package com.realdb.finalproject.studyroom;
import com.realdb.finalproject.entity.StudyRoom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
