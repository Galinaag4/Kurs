package com.skypro.kurs;

import com.skypro.kurs.service.UtilService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class UtilServiseTest {
    @InjectMocks
    private UtilService out;
    @Mock
    Random random;
    @Test
    void getRandomQuestionTest(){

    }
}
