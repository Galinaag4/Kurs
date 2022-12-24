package com.skypro.kurs;

import com.skypro.kurs.exception.BadRequestException;
import com.skypro.kurs.model.Question;
import com.skypro.kurs.repository.QuestionRepository;
import com.skypro.kurs.service.JavaQuestionService;
import com.skypro.kurs.service.UtilService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    @InjectMocks
    private JavaQuestionService out;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private UtilService utilService;

    @BeforeEach
    private void setup() {
        out = new JavaQuestionService(questionRepository, utilService);
    }

    @Test
    void addNewQuestion() {
        Question expected = new Question("Question", "Answer");
        Mockito.when(questionRepository.add(any())).thenReturn(expected);
        Question actual = out.add(expected);
        assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only().add(expected));
    }

    @Test
    void addWithQuestionAndAnswer() {
        Question expected = new Question("Question", "Answer");
        Mockito.when(questionRepository.add(any())).thenReturn(expected);
        Question actual = out.add(expected.getQuestion(), expected.getAnswer());
        assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only().add(expected));

    }

    @Test
    void addWithNullQuestion() {
        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> {
            out.add(null, "Answer");
        });
        Mockito.verify(questionRepository, Mockito.never()).add(any());
    }

    @Test
    void addWithBlankQuestion() {
        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> {
            out.add("", "Answer");
        });
        Mockito.verify(questionRepository, Mockito.never()).add(any());
    }

    @Test
    void addWithNullAnswer() {
        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> {
            out.add("Question", null);
        });
        Mockito.verify(questionRepository, Mockito.never()).add(any());
    }

    @Test
    void addWithBlankAnswer() {
        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> {
            out.add("Question", "");
        });
        Mockito.verify(questionRepository, Mockito.never()).add(any());
    }

    @Test
    void removeQuestion() {
        Question expected = new Question("Question", "Answer");
        Mockito.when(questionRepository.remove(any())).thenReturn(expected);
        Question actual = out.remove(expected);
        assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only()).remove(expected);
    }

    @Test
    void getAllQuestion() {
        Collection<Question> expected = List.of(new Question("Question", "Answer"));
        Mockito.when(questionRepository.getAll()).thenReturn(expected);
        Collection<Question> actual = out.getAll();
        assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only()).getAll();
    }

    @Test
    void getRandomQuestion() {
        Question expected = new Question("Question", "Answer");
        Collection<Question> expectedList = List.of(expected);
        Mockito.when(questionRepository.getAll()).thenReturn(expectedList);
        Mockito.when(utilService.getRandomQuestion(expectedList)).thenReturn(expected);
        Question actual = out.getRandomQuestion();
        assertThat(actual).isEqualTo(expected);
        Mockito.verify(questionRepository, Mockito.only()).getAll();
        Mockito.verify(utilService, Mockito.only()).getRandomQuestion(expectedList);
    }
}
