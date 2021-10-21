package com.dawidgorski.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lessons")
@Data
public class Lesson {
    @Id
    @SequenceGenerator(name = "sequence_gen", sequenceName = "sequence_gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    private Long id;
    private String name;
    private LocalDate lastUse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lesson")
    private List<Flashcard> flashcards;

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
}
