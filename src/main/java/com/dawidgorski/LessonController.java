package com.dawidgorski;

import com.dawidgorski.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class LessonController {
    private final LessonService lessonService;
    public LessonController(LessonService lessonService) {
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

    @PostMapping(MappingNames.UPDATE_LAST_USE)
    public String updateLastUse(@RequestParam(value = "lesson_id")String lesson_id){
        Long id= Long.parseLong(lesson_id);
        if(id!=0){
            lessonService.updateLessonLastUse(id);
        }
        return MappingNames.REDIRECT_LESSONS;
    }

    @PostMapping(MappingNames.ADD_LESSON)
    public String addLesson(@Valid Lesson lesson, BindingResult result,Model model) {
        if (result.hasErrors()) {
            model.addAttribute("lesson",lesson);
            model.addAttribute("lessonsList", lessonService.getLessons());
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
