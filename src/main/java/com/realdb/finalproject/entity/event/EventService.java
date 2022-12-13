package com.realdb.finalproject.entity.event;

import com.realdb.finalproject.exception.domain.EventNotFoundException;
import com.realdb.finalproject.exception.domain.ExhibitionNotFoundException;
import com.realdb.finalproject.exception.domain.SeminarNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author jeremy on 2022/12/11
 */
@Service
public class EventService {

    private final EventRepo eventRepo;
    private final SeminarRepo seminarRepo;
    private final ExhibitionRepo exhibitionRepo;

    @Autowired
    public EventService(EventRepo eventRepo,
                        SeminarRepo seminarRepo,
                        ExhibitionRepo exhibitionRepo) {
        this.eventRepo = eventRepo;
        this.seminarRepo = seminarRepo;
        this.exhibitionRepo = exhibitionRepo;
    }

    public List<Event> getEventByTopic(String topic) {
        return eventRepo.getEventByTopic(topic);
    }

    public List<Seminar> getSeminarByTopic(String topic) {
        return seminarRepo.getSeminarByTopic(topic);
    }

    public List<Exhibition> getExhibitionByTopic(String topic) {
        return exhibitionRepo.getExhibitionByTopic(topic);
    }

    public void addSeminar(Seminar seminar) {
        eventRepo.save(seminar.getEvent());
        seminarRepo.save(seminar);
    }

    public void addExhibition(Exhibition exhibition) {
        eventRepo.save(exhibition.getEvent());
        exhibitionRepo.save(exhibition);
    }

    public void deleteSeminar(Integer id) throws SeminarNotFoundException {
        Optional<Seminar> seminarOpt = seminarRepo.findById(id);
        if (seminarOpt.isEmpty()) {
            throw new SeminarNotFoundException("Seminar with id: " + id + " not found");
        }
        seminarRepo.deleteById(id);
        eventRepo.deleteById(id);
    }

    public void deleteExhibition(Integer id) throws ExhibitionNotFoundException {
        Optional<Exhibition> exhibitionOpt = exhibitionRepo.findById(id);
        if (exhibitionOpt.isEmpty()) {
            throw new ExhibitionNotFoundException("Exhibition with id: " + id + " not found");
        }
        exhibitionRepo.deleteById(id);
        eventRepo.deleteById(id);
    }

    @Transactional
    public void updateEvent(Integer eventId,
                            LocalDate startDate,
                            LocalDate endDate,
                            String name,
                            String topic,
                            BigDecimal expense) throws EventNotFoundException, ExhibitionNotFoundException {
        Optional<Event> eventOpt = eventRepo.findById(eventId);
        if (eventOpt.isEmpty()) {
            throw new EventNotFoundException("Event with id: " + eventId + " not found");
        }
        Event event = eventOpt.get();

        if (startDate != null && !startDate.equals(event.getStartAt())) {
            event.setStartAt(startDate);
        }
        if (endDate != null && !endDate.equals(event.getStopAt())) {
            event.setStopAt(endDate);
        }
        if (StringUtils.isBlank(name) && !name.equals(event.getName())) {
            event.setName(name);
        }
        if (StringUtils.isBlank(topic) && !topic.equals(event.getTopic())) {
            event.setTopic(topic);
        }
        eventRepo.save(event);

        if (expense != null) {
            Optional<Exhibition> exhibitionOpt = exhibitionRepo.findById(eventId);
            if (exhibitionOpt.isEmpty()) {
                throw new ExhibitionNotFoundException(
                        "Exhibition with id: " + eventId + " not found");
            }
            Exhibition exhibition = exhibitionOpt.get();
            exhibition.setExpense(expense);
            exhibitionRepo.save(exhibition);
        }
    }

    public List<Seminar> getAllSeminar() {
        return seminarRepo.findAll();
    }

    public List<Exhibition> getAllExhibition() {
        return exhibitionRepo.findAll();
    }

    public List<Event> getAllEvent() {
        return eventRepo.findAll();
    }
}
