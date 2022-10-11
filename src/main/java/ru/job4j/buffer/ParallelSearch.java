package ru.job4j.buffer;

import ru.job4j.boundedblockingqueue.SimpleBlockingQueue;

public class ParallelSearch {
    public static void main(String[] args) {
        int capacity = 10;
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(capacity);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != capacity; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Consumer finished work. WARNING: Handled exception");
                    consumer.interrupt();
                }
        ).start();
    }
}