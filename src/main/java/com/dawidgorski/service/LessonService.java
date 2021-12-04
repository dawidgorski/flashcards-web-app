package com.dawidgorski.service;

import com.dawidgorski.repository.LessonRepository;
import com.dawidgorski.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LessonService {
    private final LessonRepository repository;

    public LessonService(LessonRepository repository) {
        this.repository = repository;
    }
    public void createLesson(Lesson lesson){
//        lesson.setLastUse();
        repository.save(lesson);
    }

    public List<Lesson> getLessons() {
        return repository.findAll();
    }

    public Lesson getLesson(Long id) {
        return repository.findById(id).get();
    }

    public void deleteLesson(Long id) {
        Lesson lesson =findLessonById(id);
        repository.delete(lesson);
    }
    public void editLesson(Long id, Lesson correctLesson) {
        Lesson lesson =findLessonById(id);
        lesson.setName(correctLesson.getName());
        lesson.setLastUse(correctLesson.getLastUse());
    }
    public void updateLessonLastUse(Long id) {
        log.info("lesson last use with id: "+id +"updated");
        Lesson lessonToUpdate = repository.getById(id);
        lessonToUpdate.setLastUse();
        repository.save(lessonToUpdate);

    }
    public Lesson findLessonById(Long id) {
        Optional<Lesson> lesson = repository.findById(id);
        if(lesson.isEmpty()){
            throw new IllegalStateException();
        }
        return lesson.get();
    }
    
}
