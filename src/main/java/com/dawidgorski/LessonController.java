package com.dawidgorski;

import com.dawidgorski.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService, com.dawidgorski.FlashcardService flashcardService, LessonRepository lessonRepository) {
        this.lessonService = lessonService;
    }

    @GetMapping(MappingNames.LESSONS)
    public String getLessons(Model model) {
        model.addAttribute("lessonsList", lessonService.getLessons());
        return ViewNames.LESSONS;
    }

    @ModelAttribute(value = "lesson")
    public Lesson newLesson()
    {
        return new Lesson();
    }

    @PostMapping(MappingNames.ADD_LESSON)
    public String addLesson(@Valid Lesson lesson, BindingResult result,Model model) {
        if (result.hasErrors()) {
            model.addAttribute("lesson",lesson);
            model.addAttribute("lessonsList", lessonService.getLessons());
            log.info(""+result.hasErrors());
            return ViewNames.LESSONS;
        }
        lessonService.createLesson(lesson);
        return MappingNames.REDIRECT_LESSONS;
    }

    @GetMapping(MappingNames.DELETE_LESSON + "{id}")
    public String deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return MappingNames.REDIRECT_LESSONS;
    }

}
