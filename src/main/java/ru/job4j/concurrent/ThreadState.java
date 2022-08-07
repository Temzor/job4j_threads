package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println("Name thread first: " + Thread.currentThread().getName()
                        + " and state: " + Thread.currentThread().getState())
        );
        Thread second = new Thread(
                () -> System.out.println("Name thread second: " + Thread.currentThread().getName()
                        + " and state: " + Thread.currentThread().getState())
        );
        System.out.println("Name thread first: " + first.getName() + " and state: " + first.getState());
        System.out.println("Name thread second: " + second.getName() + " and state: " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println("Name thread first: " + first.getName() + " and state: " + first.getState());
            System.out.println("Name thread second: " + second.getName() + " and state: " + second.getState());
        }
        System.out.println("Работа завершена!");
    }
}