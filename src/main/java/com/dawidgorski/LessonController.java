package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import com.dawidgorski.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LessonController {
    private final LessonService lessonService;
    private final FlashcardService flashcardService;
    private final LessonRepository lessonRepository;

    public LessonController(LessonService lessonService, com.dawidgorski.FlashcardService flashcardService,LessonRepository lessonRepository) {
        this.lessonService = lessonService;
        this.flashcardService = flashcardService;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping(MappingNames.LESSONS_MAPPING)
    public String getLessons(Model model) {
        model.addAttribute("lessonsList",lessonService.getLessons());
        return ViewNames.LESSONS;
    }
    @PostMapping(MappingNames.ADD_LESSON)
    public String addLesson(@RequestParam String name) {
        Lesson lesson = new Lesson(name);
        lessonService.createLesson(lesson);
        return MappingNames.REDIRECT_LESSONS;
    }

    @GetMapping(MappingNames.DELETE_LESSON+"{id}")
    public String deleteLesson(@PathVariable Long id){
        lessonRepository.deleteById(id);
        return MappingNames.REDIRECT_LESSONS;
    }

}