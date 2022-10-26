package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SimpleModelTest {
    @Test
    void checkGetName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(sm::getName)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkSetName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(() -> sm.setName("name", 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkMessage() {
        SimpleModel sm = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> sm.setName(word, number))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*проверяем факт наличия сообщения*/
                .message()
                .isNotEmpty();
    }

    @Test
    void checkWordMessage() {
        SimpleModel sm = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> sm.setName(word, number))
                /*проверяем класс исключения: */
                .isInstanceOf(IllegalArgumentException.class)
                /*с помощью регулярного выражения проверяем факт наличия сообщения*/
                .hasMessageMatching("^.+")
                /*проверяем, что в сообщении есть соответствующие параметры:*/
                .hasMessageContaining(word, number)
                /*проверяем наличие конкретного слова в сообщении:*/
                .hasMessageContaining("name");
    }

}