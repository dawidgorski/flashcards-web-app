package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class FlashcardAPIController {
    private final FlashcardService service;

    public FlashcardAPIController(FlashcardService service) {
        this.service = service;
    }

    @PostMapping("add")
    public void addFlashcard(@RequestBody Flashcard flashcard) {
        service.createFlashcard(flashcard);
    }

    @GetMapping("flashcards")
    public List<Flashcard> getFlashcards() {
        return service.getFlashcards();
    }

    /*@GetMapping("/flashcard")
    public Flashcard getFlashcard(@RequestParam Long id) {
        log.info("getflashcard id= {}",id);
        return service.getFlashcard(id);
    }*/
    @GetMapping("flashcard/{id}")
    public Flashcard getFlashcard(@PathVariable Long id) {
        log.info("getflashcard id= {}",id);
        return service.getFlashcard(id);
    }
    @DeleteMapping("delete/{id}")
    public void deleteFlashcard(@PathVariable Long id){
        service.deleteFlashcard(id);
    }
    @PatchMapping("edit/{id}")
    public void editFlashcard(@PathVariable Long id,@RequestBody Flashcard correctFlashcard){
        service.editFlashcard(id,correctFlashcard);
    }
}
