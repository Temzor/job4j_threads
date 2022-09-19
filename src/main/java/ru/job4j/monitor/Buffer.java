package ru.job4j.monitor;

/**
 * Объектом монитора в данном коде является объект класса StringBuilder buffer.
 * Критической же секцией данного кода является тело методов add() и toString()
 */

public class Buffer {
    private final StringBuilder buffer = new StringBuilder();

    public synchronized void add(int value) {
        System.out.print(value);
        buffer.append(value);
    }

    @Override
    public synchronized String toString() {
        return buffer.toString();
    }
}
