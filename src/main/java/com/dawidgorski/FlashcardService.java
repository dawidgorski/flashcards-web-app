package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final LessonRepository lessonRepository;

    public FlashcardService(FlashcardRepository flashcardRepository, LessonRepository lessonRepository) {
        this.flashcardRepository = flashcardRepository;
        this.lessonRepository = lessonRepository;
    }

    public void createFlashcard(Flashcard flashcard){
        flashcardRepository.save(flashcard);
    }

    public List<Flashcard> getFlashcards() {
        return flashcardRepository.findAll();
    }
    public List<Flashcard> getFlashcardsByLessonId(Long id){
        List<Flashcard> list = flashcardRepository.findAllByLessonId(id);
        log.info("flashcards with id = "+id+" : "+list.size());
        return flashcardRepository.findAllByLessonId(id);
    }
    public Flashcard getFlashcard(Long id) {
        return flashcardRepository.findById(id).get();
    }

    public void deleteFlashcard(Long id) {
        Flashcard flashcard =findFlashcardById(id);
        flashcardRepository.delete(flashcard);
    }

    public Flashcard findFlashcardById(Long id) {
        Optional<Flashcard> flashcard = flashcardRepository.findById(id);
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
