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
        log.info("flashcard from service: {}",repository.findById(id).get());
        return repository.findById(id).get();
    }

    public void deleteFlashcard(Long id) {
        Optional<Flashcard> flashcard =findFlashcardById(id);
        if(flashcard.isEmpty()){
            throw new IllegalStateException();
        }
        repository.delete(flashcard.get());
    }

    public Optional<Flashcard> findFlashcardById(Long id) {
        return repository.findById(id);
    }

    public void editFlashcard(Long id, Flashcard correctFlashcard) {
        Optional<Flashcard> flashcard =findFlashcardById(id);
        if(flashcard.isEmpty()){
            throw new IllegalStateException();
        }
        repository.delete(flashcard.get());
        repository.save(correctFlashcard);
    }
}
