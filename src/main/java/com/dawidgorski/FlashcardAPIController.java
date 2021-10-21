package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
//@RestController
public class FlashcardAPIController {
    private final FlashcardService flashcardService;

    public FlashcardAPIController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping("add")
    public void addFlashcard(@RequestBody Flashcard flashcard) {
        flashcardService.createFlashcard(flashcard);
    }

    @GetMapping("flashcards")
    public List<Flashcard> getFlashcards() {
        return flashcardService.getFlashcards();
    }

    @GetMapping("flashcard/{id}")
    public Flashcard getFlashcard(@PathVariable Long id) {
        log.info("get Flashcard id= {}",id);
        return flashcardService.getFlashcard(id);
    }
    @DeleteMapping("delete/{id}")
    public void deleteFlashcard(@PathVariable Long id){
        flashcardService.deleteFlashcard(id);
    }
    @PatchMapping("edit/{id}")
    public void editFlashcard(@PathVariable Long id,@RequestBody Flashcard correctFlashcard){
        flashcardService.editFlashcard(id,correctFlashcard);
    }
}
