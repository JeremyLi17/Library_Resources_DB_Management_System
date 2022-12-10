package com.realdb.finalproject.studyroom;

import com.realdb.finalproject.entity.StudyRoom;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/studyroom")
@AllArgsConstructor
public class StudyroomController {
    private final StudyroomService studyroomService;
    @GetMapping("/all")
    public List<StudyRoom> getStudyrooms() {
        return studyroomService.getStudyrooms();
    }
    @DeleteMapping()
    public void deleteStudyroom(@RequestParam Integer id) {
        studyroomService.deleteStudyroom(id);
    }


}
