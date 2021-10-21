package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import com.dawidgorski.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class LessonService {
    private final LessonRepository repository;

    public LessonService(LessonRepository repository) {
        this.repository = repository;
    }
    public void createLesson(Lesson lesson){
        repository.save(lesson);
    }

    public List<Lesson> getLessons() {
        return repository.findAll();
    }

    public Lesson getLesson(Long id) {
        return repository.findById(id).get();
    }

    public void deleteLesson(Long id) {
        Lesson Lesson =findLessonById(id);
        repository.delete(Lesson);
    }
    public Lesson findLessonById(Long id) {
        Optional<Lesson> lesson = repository.findById(id);
        if(lesson.isEmpty()){
            throw new IllegalStateException();
        }
        return lesson.get();
    }
    
}
