package ru.job4j.monitor;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Запомните, что аннотации jcip - это как JavaDoc - он нужен,
 * только чтобы описать класс, который будет работать в многопоточной среде.
 * Синхронизацию эти аннотации не добавляют.
 */
@ThreadSafe
/* @ThreadSafe - Говорит пользователям данного класса,
  что класс можно использовать в многопоточном режиме
  и он будет работать правильно.*/

public class CountIncrement {
    @GuardedBy("this")
    /*  @GuardedBy - Выставляется над общим ресурсом.
    Аннотация имеет входящий параметр.
    Он указывает на объект монитора,
    по которому мы будем синхронизироваться.*/

    private int value;

    public synchronized void increment() {
        this.value++;
    }

    public synchronized int getValue() {
        return this.value;
    }
}
