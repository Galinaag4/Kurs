package com.skypro.kurs.service;

import com.skypro.kurs.exception.BadRequestException;
import com.skypro.kurs.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final List<QuestionService> questionServices;
    private final UtilService utilService;

    public ExaminerServiceImpl(List<QuestionService> questionServices, UtilService utilService) {
        this.questionServices = questionServices;
        this.utilService = utilService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 || calculateAmountOfQuestions() < amount) {
            throw new BadRequestException("Incorrect" + amount);
        }
        Set<Question> result = new HashSet<>();
        while (result.size() < amount) {
            var serviceNumber = utilService.getRandomInt(questionServices.size());
            var questionService = questionServices.get(serviceNumber);
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }

    private int calculateAmountOfQuestions() {
        return questionServices.stream()
                .mapToInt(s -> s.getAll().size())
                .sum();
    }
}
