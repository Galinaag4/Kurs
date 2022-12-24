package com.skypro.kurs.service;

import com.skypro.kurs.exception.BadRequestException;
import com.skypro.kurs.model.Question;
import com.skypro.kurs.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;
    private final UtilService utilService;

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository questionRepository, UtilService utilService) {
        this.questionRepository = questionRepository;
        this.utilService = utilService;
    }

    @Override
    public Question add(String question, String answer) {
        if (question == null || question.isBlank()) {
            throw new BadRequestException("Incorrect question");
        }
        if (answer == null || answer.isBlank()) {
            throw new BadRequestException("Incorrect answer");
        }
        return questionRepository.add(new Question(question, answer));

    }

    @Override
    public Question add(Question question) {
        return questionRepository.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questionRepository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        return utilService.getRandomQuestion(questionRepository.getAll());
    }
}
