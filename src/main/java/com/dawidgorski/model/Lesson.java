package com.dawidgorski.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @SequenceGenerator(name = "sequence_gen", sequenceName = "sequence_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    private Long id;
    private String name;
    private LocalDate lastUse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    public List<Flashcard> flashcards;

    public LocalDate getLastUse() {
        this.lastUse = LocalDate.now();
        return lastUse;
    }

    public Lesson() {
    }

    public Lesson(String name) {
        this(name,null);

    }
    public Lesson(String name, LocalDate lastUse) {
        this.name = name;
        if (lastUse!= null){
            this.lastUse = lastUse;
        }else{
            this.lastUse = LocalDate.now();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastUse(LocalDate lastUse) {
        this.lastUse = lastUse;
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }
}
