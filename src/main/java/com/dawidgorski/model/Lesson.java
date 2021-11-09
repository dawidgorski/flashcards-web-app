package com.dawidgorski.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @SequenceGenerator(name = "sequence_gen", sequenceName = "sequence_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    private Long id;
    @NotEmpty(message = "The lesson name can't be empty")
    private String name;
    private LocalDateTime lastUse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    public List<Flashcard> flashcards;
    @Transient
    @JsonIgnore
    private long daysAgo;
    public long getDaysAgo(){
        if (lastUse!=null){
            long days = ChronoUnit.DAYS.between(lastUse,LocalDateTime.now());
            return days;
        }
        return 0;

    }
    public LocalDateTime getLastUse() {
        return lastUse;
    }

    public Lesson() {
    }

    public Lesson(String name) {
        this(name,null);

    }
    public Lesson(String name, LocalDateTime lastUse) {
        this.name = name;
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

    public void setLastUse() {
        this.lastUse = LocalDateTime.now();
    }
    public void setLastUse(LocalDateTime dateTime) {
        this.lastUse = dateTime;
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }
}
