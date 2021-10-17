package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import com.dawidgorski.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Slf4j
@Controller
public class FlashcardWebController {
    private final FlashcardService service;

    public FlashcardWebController(FlashcardService service) {
        this.service = service;
    }

    @GetMapping(MappingNames.FLASHCARDS)
    public String getFlashcards(Model model) {
        model.addAttribute("flashcardsList",service.getFlashcards());
        return ViewNames.FLASHCARDS;
    }

    @PostMapping(MappingNames.ADD_FLASHCARD)
    public String addFlashcard(@RequestParam String english,
                             @RequestParam String polish,
                             @RequestParam String description ) {
        Flashcard flashcard = new Flashcard(english,polish,description);
        service.createFlashcard(flashcard);
        return MappingNames.REDIRECT_FLASHCARDS;
    }

    @GetMapping("flashcard/{id}")
    public Flashcard getFlashcard(@PathVariable Long id) {
        log.info("getflashcard id= {}",id);
        return service.getFlashcard(id);
    }
    @GetMapping(MappingNames.DELETE_FLASHCARD+"{id}")
    public String deleteFlashcard(@PathVariable Long id){
        service.deleteFlashcard(id);
        return MappingNames.REDIRECT_FLASHCARDS;
    }
    @PatchMapping("edit/{id}")
    public void editFlashcard(@PathVariable Long id,@RequestBody Flashcard correctFlashcard){
        service.editFlashcard(id,correctFlashcard);
    }
}
