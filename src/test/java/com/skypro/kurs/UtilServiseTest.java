package com.skypro.kurs;

import com.skypro.kurs.model.Question;
import com.skypro.kurs.service.UtilService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class UtilServiseTest {
    @InjectMocks
    private UtilService out;

    @Test
    void getRandomQuestinIfResNull(){
        Collection<Question> collection = new ArrayList<>();
        Assertions.assertEquals(null, out.getRandomQuestion(collection));
    }
    @Test
    void getRandomQuestinIfResNotNull(){
        ArrayList<Question> collection = new ArrayList<>();
        collection.add(new Question("1", "1"));
        collection.add(new Question("2", "2"));
        collection.add(new Question("3", "3"));

        Mockito.when(out.getRandomQuestion(collection)).thenReturn(collection.get(1));
        Assertions.assertEquals(collection.get(2), out.getRandomQuestion(collection));
    }
}
