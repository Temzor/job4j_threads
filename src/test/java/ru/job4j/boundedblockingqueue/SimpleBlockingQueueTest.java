package ru.job4j.boundedblockingqueue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class SimpleBlockingQueueTest {

    @Test
    public void whenTestTwoThread() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(10);
        List<Integer> expected = new ArrayList<>();
        List<Integer> resultList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 1; i <= 10; i++) {
                        simpleBlockingQueue.offer(i);
                    }
                }, "Thread first"

        );
        Thread thread2 = new Thread(
                () -> {
                    for (int i = 1; i <= 10; i++) {
                        expected.add(simpleBlockingQueue.poll());
                    }
                }, "Thread second"
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(expected).containsAll(resultList);
    }
}