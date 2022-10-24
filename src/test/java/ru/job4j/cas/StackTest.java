package ru.job4j.cas;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StackTest {

    @Test
    public void whenThreePushThenThreePoll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll()).isEqualTo(3);
        assertThat(stack.poll()).isEqualTo(2);
        assertThat(stack.poll()).isEqualTo(1);
    }

    @Test
    public void whenOnePushThenOnePoll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        assertThat(stack.poll()).isEqualTo(1);
    }

    @Test
    public void whenTwoPushThenTwoPoll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        assertThat(stack.poll()).isEqualTo(2);
        assertThat(stack.poll()).isEqualTo(1);
    }

}