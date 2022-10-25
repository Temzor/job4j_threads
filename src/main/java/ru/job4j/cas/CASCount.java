package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int reference;
        int tempValue;
        do {
            tempValue = count.get();
            reference = tempValue + 1;
        } while (!count.compareAndSet(tempValue, reference));
    }

    public int get() {
        return count.get();
    }
}
