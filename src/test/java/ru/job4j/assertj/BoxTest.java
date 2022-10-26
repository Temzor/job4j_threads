package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isUNKNOWN() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void getNumberNegative() {
        Box box = new Box(5, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1).isNegative().isOdd().isNotZero();
    }

    @Test
    void getNumberPositive() {
        Box box = new Box(4, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(4).isPositive().isEven().isNotZero();
    }

    @Test
    void whenBooleanThenTrue() {
        Box box = new Box(4, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenBooleanThenFalse() {
        Box box = new Box(-1, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void whenDoubleThenTetrahedronArea() {
        Box box = new Box(4, 10);
        assertThat(box.getArea()).isEqualTo(Math.sqrt(3) * Math.pow(10, 2));
    }

    @Test
    void whenDoubleThenTetrahedronAreaWithPrecision() {
        Box box = new Box(4, 10);
        assertThat(box.getArea()).isEqualTo(173.205d, withPrecision(0.10d))
                .isCloseTo(173.2d, withPrecision(0.01d))
                .isGreaterThan(173.1d)
                .isLessThan(173.43d);
    }
}