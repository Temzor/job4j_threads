package ru.job4j.monitor;

/**
 * Объект монитор - это объект, по которому виртуальная машина понимает, что блок кода выполняется нитью.
 * Объект монитора может быть в двух значениях - блок занят или блок свободный. Проверка состояния монитора атомарна.
 * Блок synchronized {} называется критической секцией. В ней одновременно может находиться только одна нить.
 * Запись метода с ключевым словом synchronized
 * <p>
 *     public synchronized void increment() {
 *         value++;
 *     }
 * и метода c блоком synchronized
 * <p>
 *     public void increment() {
 *         synchronized (this) {
 *             value++;
 *         }
 *     }
 * эквиваленты. В нашем примере они сделаны для наглядности объекта монитора и критической секции.
 * В случае синхронизированного метода объектом монитора является экземпляр этого класса.
 */
public class Count {
    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized int get() {
        return value;
    }
}

class CountRefactor {
    private int value;

    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    public int get() {
        synchronized (this) {
            return value;
        }
    }
}