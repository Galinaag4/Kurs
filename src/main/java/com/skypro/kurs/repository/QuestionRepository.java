package com.skypro.kurs.repository;

import com.skypro.kurs.model.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
