package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.data.Index;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array)
                .hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("zero", "first", "second", "three", "four", "five");
        assertThat(list)
                .hasSize(6)
                .contains("second")
                .contains("three", Index.atIndex(3))
                .containsAnyOf("one", "first", "two")
                .doesNotContain("second", Index.atIndex(0))
                .startsWith("zero")
                .endsWith("five")
                .isNotNull()
                .isNotEmpty()
                .first().isEqualTo("zero");

        assertThat(list)
                .element(4)
                .isNotNull()
                .isEqualTo("four");

        assertThat(list)
                .last()
                .isNotNull()
                .isEqualTo("five");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "first", "second", "three", "four", "five", "five");
        assertThat(set)
                .hasSize(5)
                .contains("second")
                .containsAnyOf("one", "first", "two")
                .isNotNull()
                .isNotEmpty()
                .first().isEqualTo("four");

        assertThat(set)
                .element(3)
                .isNotNull()
                .isEqualTo("first");

        assertThat(set)
                .last()
                .isNotNull()
                .isEqualTo("second");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map)
                .hasSize(5)
                .doesNotContainKey("fifty")
                .containsValues(1, 4)
                .containsEntry("five", 4);

    }
}