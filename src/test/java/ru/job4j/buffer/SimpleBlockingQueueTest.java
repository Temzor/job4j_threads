package ru.job4j.buffer;

import org.junit.jupiter.api.Test;
import ru.job4j.boundedblockingqueue.SimpleBlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


public class SimpleBlockingQueueTest {
    @Test
    public void whenOfferAndPoll() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(6);
        List<Integer> expected = new ArrayList<>(List.of(1, 2, 3, 4));
        List<Integer> actual = new ArrayList<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        for (Integer integer : expected) {
                            simpleBlockingQueue.offer(integer);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }

                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        actual.add(simpleBlockingQueue.poll());
                        actual.add(simpleBlockingQueue.poll());
                        actual.add(simpleBlockingQueue.poll());
                        actual.add(simpleBlockingQueue.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> IntStream.range(0, 10).forEach(
                        (value) -> {
                            try {
                                queue.offer(value);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                )
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).isEqualTo(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
    }
}