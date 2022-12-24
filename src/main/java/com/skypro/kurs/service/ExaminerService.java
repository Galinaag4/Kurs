package com.skypro.kurs.service;

import com.skypro.kurs.model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
