package com.dawidgorski;

import com.dawidgorski.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlashcardRepository extends JpaRepository<Flashcard,Long>{
    List<Flashcard> findAllByLessonId(Long id);
}


