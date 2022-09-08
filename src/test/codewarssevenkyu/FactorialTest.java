package codewarssevenkyu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FactorialTest {
    @Test
    void testSomething() {
        Assertions.assertEquals(1L, Factorial.factorial(0));
        Assertions.assertEquals(1L, Factorial.factorial(1));
        Assertions.assertEquals(24L, Factorial.factorial(4));
        Assertions.assertEquals(5040L, Factorial.factorial(7));
    }
}