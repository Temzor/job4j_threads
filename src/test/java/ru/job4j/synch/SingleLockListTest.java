package ru.job4j.synch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.*;

public class SingleLockListTest {

    @Test
    public void add() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> result = new TreeSet<>();
        list.iterator().forEachRemaining(result::add);
        assertThat(result).contains(1, 2);
    }

    @Test
    public void get() throws InterruptedException {
        SingleLockList<Integer> list = new SingleLockList<>(new ArrayList<>());
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(1).isEqualTo(list.get(0));
        assertThat(2).isEqualTo(list.get(1));
    }
}