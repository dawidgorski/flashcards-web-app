package com.dawidgorski.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name= "flashcards")
@Data
public class Flashcard {
    @Id
    @SequenceGenerator(name="sequence_gen",sequenceName ="sequence_gen",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    private Long id;
    @NotEmpty(message = "english word can't be empty")
    private String english;
    @NotEmpty(message = "polish word can't be empty")
    private String polish;
    private String description;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;
//    private boolean isHard;

    public Flashcard(String english, String polish, String description, Lesson lesson) {
        this.english = english;
        this.polish = polish;
        this.description = description;
        this.lesson = lesson;
//        isHard = false;
    }

    public Flashcard() {

    }
}
