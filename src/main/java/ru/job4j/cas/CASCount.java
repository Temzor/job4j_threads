package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int reference;
        int tempValue;
        do {
            reference = count.get();
            tempValue = reference + 1;
        } while (!count.compareAndSet(reference, tempValue));
    }

    public int get() {
       return count.get();
    }
}
