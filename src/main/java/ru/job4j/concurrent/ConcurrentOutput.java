package ru.job4j.concurrent;

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println("First thread: " + Thread.currentThread().getName())
        );

        Thread second = new Thread(
                () -> System.out.println("Second thread: " + Thread.currentThread().getName())
        );

        first.start();
        second.start();
        System.out.println("Main thread: " + Thread.currentThread().getName());
    }
}