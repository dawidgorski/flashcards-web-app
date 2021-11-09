package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import com.dawidgorski.model.Lesson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Slf4j
@Controller
public class FlashcardWebController {
    private final FlashcardService service;
    private final LessonService lessonService;
    long lesson_id;
    Lesson lesson= new Lesson();

    @ModelAttribute(value = "lesson_id")
    public String getLessonId()
    {
        return ""+lesson_id;
    }

    @ModelAttribute(value = "flashcard")
    public Flashcard newFlashcard()
    {
        return new Flashcard();
    }

    public FlashcardWebController(FlashcardService service, LessonService lessonService) {
        this.service = service;
        this.lessonService = lessonService;
    }

    @ModelAttribute(value = "message")
    public String newMessage() {
        return "";
    }

    @GetMapping(MappingNames.FLASHCARDS + "/{id}")
    public String getFlashcards(Model model, @PathVariable Long id) {
        lesson_id = id;
        lesson = lessonService.getLesson(id);

        model.addAttribute("lesson_name", lessonService.getLesson(id).getName());
        model.addAttribute("lesson_list", service.getFlashcardsByLessonId(id));
        return ViewNames.FLASHCARDS;
    }


    @GetMapping("flashcards/all")
    public String getAllFlashcards(Model model) {
        model.addAttribute("lesson_list", service.getFlashcards());
        return ViewNames.FLASHCARDS;
    }
    @GetMapping("flashcards/" + MappingNames.ADD_FLASHCARD)
    public String showFlashcards(){
        return MappingNames.FLASHCARDS + "/" + lesson_id;
    }
    @PostMapping("flashcards/" + MappingNames.ADD_FLASHCARD)
    public String addFlashcard(@Valid Flashcard flashcard,
                               BindingResult bindingResult,
                               RedirectAttributes attributes,
                               @RequestParam String english,
                               @RequestParam String polish,
                               @RequestParam String description

                               ) {
        if (bindingResult.hasErrors()){
            log.info("flashcard has errors");
            attributes.addFlashAttribute("message", "Please enter english and polish word.");
            return MappingNames.REDIRECT_FLASHCARDS + "/" + lesson_id;
        }
        Flashcard flashcard1 = new Flashcard(english, polish, description, lesson);
        service.createFlashcard(flashcard1);
        log.info("flashcard created: " + flashcard1.getDescription());
        return MappingNames.REDIRECT_FLASHCARDS + "/" + lesson_id;
    }

    @PostMapping("/upload")
    public String addFlashcards(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return MappingNames.REDIRECT_FLASHCARDS + "/" + lesson_id;
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if(service.createFlashcards(file, lesson)){
            log.info("file uploaded: " + fileName);
            attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');
        }else{
            attributes.addFlashAttribute("message", "Wrong data in file " + fileName + '!');
            log.info("wrong data in file: " + fileName);
        }

        return MappingNames.REDIRECT_FLASHCARDS + "/" + lesson_id;
    }

    @GetMapping("flashcard/{id}")
    public Flashcard getFlashcard(@PathVariable Long id) {
        log.info("getflashcard id= {}", id);
        return service.getFlashcard(id);
    }

    @GetMapping("flashcards/" + MappingNames.DELETE_FLASHCARD + "{id}")
    public String deleteFlashcard(@PathVariable Long id) {
        service.deleteFlashcard(id);
        return MappingNames.REDIRECT_FLASHCARDS + "/" + lesson_id;
    }

    @PatchMapping("edit/{id}")
    public void editFlashcard(@PathVariable Long id, @RequestBody Flashcard correctFlashcard) {
        service.editFlashcard(id, correctFlashcard);
    }
}
