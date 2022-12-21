package com.skypro.kurs.service;

import com.skypro.kurs.model.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Random;

public class UtilService {
    private Random random;

    public int getRandomInt(int bound) {
        return random.nextInt(bound);
    }

    @Autowired
    public void setRandom(Random random) {
        this.random = random;
    }

    public Question getRandomQuestion(Collection<Question> questions) {
        int questionNum = getRandomInt(questions.size());
        int questionCur = 0;
        for (Question question : questions) {
            if (questionCur == questionNum) {
                return question;
            }
            questionCur++;
        }
        return null;
    }
}
