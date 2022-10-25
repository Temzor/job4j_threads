package ru.job4j.cas;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CASCountTest {

    @Test
    public void whenJoin() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(() -> {
            count.increment();
            count.increment();
            count.increment();
        });
        Thread second = new Thread(count::increment);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get()).isEqualTo(4);
    }

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        CASCount count = new CASCount();
        var first = new Thread(count::increment);
        var second = new Thread(count::increment);
        /* Запускаем нити. */
        first.start();
        second.start();
        /* Заставляем главную нить дождаться выполнения наших нитей. */
        first.join();
        second.join();
        /* Проверяем результат. */
        assertThat(count.get()).isEqualTo(2);
    }

    @Test
    public void whenNoJoinOnSecond() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(() -> {
            count.increment();
            count.increment();
            count.increment();
        });
        Thread second = new Thread(count::increment);
        first.start();
        first.join();
        second.start();
        assertThat(count.get()).isEqualTo(3);
    }
}
