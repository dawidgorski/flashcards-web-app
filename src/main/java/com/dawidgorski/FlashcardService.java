package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import com.dawidgorski.model.Lesson;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
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


    public boolean createFlashcards(MultipartFile file, Lesson lesson) {
        List<Flashcard> flashcards =new ArrayList<>();
        try (InputStream input =file.getInputStream();
             CSVReader reader = new CSVReaderBuilder(new InputStreamReader(input)).withCSVParser(new CSVParser()).build();){
            String[] line;

            while ((line = reader.readNext()) != null) {
                log.info(""+line.toString()+"  "+line[0]);
                Flashcard flashcard = new Flashcard();
                flashcard.setEnglish(line[0]);
                flashcard.setPolish(line[1]);
                try {
                    flashcard.setDescription(line[2]);
                }catch (ArrayIndexOutOfBoundsException e){
                    flashcard.setDescription(null);
                }
                try {
                    flashcard.setImagePath(line[3]);
                }catch (ArrayIndexOutOfBoundsException e){
                    flashcard.setImagePath(null);
                }

                flashcard.setLesson(lesson);
                flashcards.add(flashcard);
            }
            flashcardRepository.saveAll(flashcards);
            return true;
        } catch (CsvValidationException | IOException csvValidationException) {
            csvValidationException.printStackTrace();
            return false;
        } catch (ArrayIndexOutOfBoundsException e){
            return false;
        }


    }
}
