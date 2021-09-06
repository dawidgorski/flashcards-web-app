package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FlashcardService {
    private final FlashcardRepository repository;

    public FlashcardService(FlashcardRepository repository) {
        this.repository = repository;
    }

    public void createFlashcard(Flashcard flashcard){
        repository.save(flashcard);
    }

    public List<Flashcard> getFlashcards() {
        return repository.findAll();
    }

    public Flashcard getFlashcard(Long id) {
        return repository.findById(id).get();
    }

    public void deleteFlashcard(Long id) {
        Flashcard flashcard =findFlashcardById(id);
        repository.delete(flashcard);
    }

    public Flashcard findFlashcardById(Long id) {
        Optional<Flashcard> flashcard = repository.findById(id);
        if(flashcard.isEmpty()){
            throw new IllegalStateException();
        }
        return flashcard.get();
    }

    public void editFlashcard(Long id, Flashcard correctFlashcard) {
        Flashcard flashcard =findFlashcardById(id);
        flashcard.setEnglish(correctFlashcard.getEnglish());
        flashcard.setPolish(correctFlashcard.getPolish());
        flashcard.setDescription(correctFlashcard.getDescription());
    }
}
