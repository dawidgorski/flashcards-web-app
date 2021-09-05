package com.dawidgorski.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table
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

    public LocalDate getLastUse() {
        this.lastUse = LocalDate.now();
        return lastUse;
    }



    public Flashcard(String english, String polish,String description) {
        this.english = english;
        this.polish = polish;
        this.description = description;

    }

    public Flashcard() {

    }
}
