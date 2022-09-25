package ru.ru.job4j.monitor;

import org.junit.jupiter.api.Test;
import ru.job4j.monitor.CountIncrement;

import static org.assertj.core.api.Assertions.assertThat;

class CountIncrementTest {

    @Test
    public void whenExecuteThreadThen2() throws InterruptedException {
        CountIncrement count = new CountIncrement();
        Thread first = new Thread(count::increment);
        Thread second = new Thread(count::increment);

        /* Start Threads */
        first.start();
        second.start();

        /* Queue of Threads in line */
        first.join();
        second.join();

        /* Test equals Threads */
        assertThat(count.getValue()).isEqualTo(2);
    }
}