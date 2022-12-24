package com.skypro.kurs;

import com.skypro.kurs.exception.BadRequestException;
import com.skypro.kurs.exception.NotFoundException;
import com.skypro.kurs.model.Question;
import com.skypro.kurs.repository.JavaQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionRepositoryTest {

    private JavaQuestionRepository out;

    @BeforeEach
    void setup() {
        out = new JavaQuestionRepository();
        out.add(new Question("JavaQuestion1", "Answer1"));
        out.add(new Question("JavaQuestion2", "Answer2"));
        out.add(new Question("JavaQuestion3", "Answer3"));
    }

    @Test
    void addQuestion() {
        Question expected = new Question("JavaQuestion4", "Answer4");
        Question actual = out.add(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(out.getAll().contains(expected)).isTrue();
        assertThat(out.getAll().size()).isEqualTo(4);


    }

    @Test
    void addExistingQuestion() {
        Question expected = new Question("JavaQuestion3", "Answer3");
        assertThat(out.getAll().contains(expected)).isTrue();
        Question actual = out.add(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(out.getAll().size()).isEqualTo(3);

    }

    @Test
    void addNullQuestion() {
        assertThatExceptionOfType(BadRequestException.class).isThrownBy(() -> {
            out.add(null);
        });

    }

    @Test
    void removeExistingQuestion() {
        Question expected = new Question("JavaQuestion1", "Answer1");
        Question actual = out.remove(expected);
        assertThat(actual).isEqualTo(expected);
        assertThat(out.getAll().size()).isEqualTo(2);
    }

    @Test
    void removeNotExistingQuestion() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> {
            out.remove(null);
        });
    }

    @Test
    void getAll() {
        assertThat(out.getAll().size()).isEqualTo(3);
    }
}
