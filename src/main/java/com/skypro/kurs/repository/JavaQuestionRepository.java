package com.skypro.kurs.repository;

import com.skypro.kurs.exception.BadRequestException;
import com.skypro.kurs.exception.NotFoundException;
import com.skypro.kurs.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();

    @PostConstruct
    private void init() {
        questions.add(new Question("Question1", "Answer1"));
        questions.add(new Question("Question2", "Answer2"));
        questions.add(new Question("Question3", "Answer3"));
        questions.add(new Question("Question4", "Answer4"));
        questions.add(new Question("Question5", "Answer5"));

    }


    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new BadRequestException("Define question to add");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new NotFoundException("Question is not found");
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }
}
