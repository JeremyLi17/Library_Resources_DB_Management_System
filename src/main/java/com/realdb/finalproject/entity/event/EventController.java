package com.realdb.finalproject.entity.event;

import com.realdb.finalproject.domain.HttpResponse;
import com.realdb.finalproject.exception.domain.EventNotFoundException;
import com.realdb.finalproject.exception.domain.ExhibitionNotFoundException;
import com.realdb.finalproject.exception.domain.SeminarNotFoundException;
import com.realdb.finalproject.utility.BuildResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * @author jeremy on 2022/12/11
 */
@RestController
@RequestMapping("/api/event")
public class EventController {

    public static final String SEMINAR_DELETED_SUCCESSFULLY = "Seminar deleted successfully";
    public static final String EXHIBITION_DELETED_SUCCESSFULLY = "Exhibition deleted successfully";
    public static final String EVENT_UPDATED_SUCCESSFULLY = "Event updated successfully";
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/list/event")
    public ResponseEntity<List<Event>> getEventByTopic(@RequestParam("topic") String topic) {
        List<Event> events = eventService.getEventByTopic(topic);
        return new ResponseEntity<>(events, OK);
    }

    @GetMapping("/list/event/all")
    public ResponseEntity<List<Event>> getAllEvent() {
        List<Event> events = eventService.getAllEvent();
        return new ResponseEntity<>(events, OK);
    }

    @GetMapping("/list/seminar")
    public ResponseEntity<List<Seminar>> getSeminarByTopic(@RequestParam("topic") String topic) {
        List<Seminar> seminars = eventService.getSeminarByTopic(topic);
        return new ResponseEntity<>(seminars, OK);
    }

    @GetMapping("/list/seminar/all")
    public ResponseEntity<List<Seminar>> getAllSeminar() {
        List<Seminar> seminars = eventService.getAllSeminar();
        return new ResponseEntity<>(seminars, OK);
    }

    @GetMapping("/list/exhibition/all")
    public ResponseEntity<List<Exhibition>> getAllExhibition() {
        List<Exhibition> exhibitions = eventService.getAllExhibition();
        return new ResponseEntity<>(exhibitions, OK);
    }

    @GetMapping("/list/exhibition")
    public ResponseEntity<List<Exhibition>> getExhibitionByTopic(@RequestParam("topic") String topic) {
        List<Exhibition> exhibitions = eventService.getExhibitionByTopic(topic);
        return new ResponseEntity<>(exhibitions, OK);
    }

    @PostMapping("/add/seminar")
    public ResponseEntity<Seminar> addSeminar(@RequestBody Seminar seminar) {
        eventService.addSeminar(seminar);
        return new ResponseEntity<>(seminar, CREATED);
    }

    @PostMapping("/add/exhibition")
    public ResponseEntity<Exhibition> addSeminar(@RequestBody Exhibition exhibition) {
        eventService.addExhibition(exhibition);
        return new ResponseEntity<>(exhibition, CREATED);
    }

    @DeleteMapping("/delete/seminar")
    public ResponseEntity<HttpResponse> deleteSeminar(@RequestParam("id") Integer id)
            throws SeminarNotFoundException {
        eventService.deleteSeminar(id);
        return BuildResponse.build(NO_CONTENT, SEMINAR_DELETED_SUCCESSFULLY);
    }

    @DeleteMapping("/delete/exhibition")
    public ResponseEntity<HttpResponse> deleteExhibition(@RequestParam("id") Integer id)
            throws ExhibitionNotFoundException {
        eventService.deleteExhibition(id);
        return BuildResponse.build(NO_CONTENT, EXHIBITION_DELETED_SUCCESSFULLY);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateEvent(
            @RequestParam("id") Integer eventId,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "topic",required = false) String topic,
            @RequestParam(value = "expense", required = false) BigDecimal expense)
            throws EventNotFoundException, ExhibitionNotFoundException {
        eventService.updateEvent(eventId, startDate, endDate, name, topic, expense);
        return BuildResponse.build(ACCEPTED, EVENT_UPDATED_SUCCESSFULLY);
    }
}
