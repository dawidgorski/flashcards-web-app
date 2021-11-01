package com.dawidgorski.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name= "flashcards")
@Data
public class Flashcard {
    @Id
    @SequenceGenerator(name="sequence_gen",sequenceName ="sequence_gen",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    private Long id;
    private String english;
    private String polish;
    private String description;
    @Transient
    private LocalDate lastUse;
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;
    public LocalDate getLastUse() {
        this.lastUse = LocalDate.now();
        return lastUse;
    }

    public Flashcard(String english, String polish, String description) {
        this(english,polish,description,null);
    }

    public Flashcard(String english, String polish, String description, Lesson lesson) {
        this.english = english;
        this.polish = polish;
        this.description = description;
        if (lesson != null){
            this.lesson = lesson;
        }
    }

    public Flashcard() {

    }
}
