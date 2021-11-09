package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import com.dawidgorski.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class FlashcardAPIController {
    private final FlashcardService flashcardService;
    private final LessonService lessonService;

    public FlashcardAPIController(FlashcardService flashcardService, LessonService lessonService) {
        this.flashcardService = flashcardService;
        this.lessonService = lessonService;
    }

    @PostMapping("api/add")
    public void addFlashcard(@RequestBody Flashcard flashcard) {
        flashcardService.createFlashcard(flashcard);
    }
    @GetMapping("api/lessons")
    public List<Lesson> getLessons() {
        return lessonService.getLessons();
    }
    @DeleteMapping("api/delete/lesson/{id}")
    public void deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }
    @GetMapping("api/flashcards")
    public List<Flashcard> getFlashcardsJSON() {
        return flashcardService.getFlashcards();
    }

    @GetMapping("api/flashcard/{id}")
    public Flashcard getFlashcardJSON(@PathVariable Long id) {
        log.info("get Flashcard id= {}",id);
        return flashcardService.getFlashcard(id);
    }
    @DeleteMapping("api/delete/flashcard/{id}")
    public void deleteFlashcard(@PathVariable Long id){
        flashcardService.deleteFlashcard(id);
    }
    @PatchMapping("api/edit/{id}")
    public void editFlashcard(@PathVariable Long id,@RequestBody Flashcard correctFlashcard){
        flashcardService.editFlashcard(id,correctFlashcard);
    }
}
