package ru.job4j.concurrent;

import java.util.List;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        List<Character> symbols = List.of('-', '\\', '|', '/', '-', '\\', '|', '/');
        while (!Thread.currentThread().isInterrupted()) {
            for (Character symbol : symbols) {
                System.out.print("\r Loading " + symbol);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}