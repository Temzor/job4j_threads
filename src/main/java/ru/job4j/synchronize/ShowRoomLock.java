package ru.job4j.synchronize;

public class ShowRoomLock {
    /* Объект монитор - это объект ShowRoomLock */
    public synchronized void lockOfInstanceFirst() {

    }

    /* Объект монитор будет сам класс ShowRoomLock */
    public static synchronized void lockOfClassFirst() {

    }

    /* Объект монитор - это объект ShowRoomLock */
    public void lockOfInstanceSecond() {
        synchronized (this) {
            String start;
        }
    }

    /* Объект монитор будет сам класс ShowRoomLock */
    public static void lockOfClassSecond() {
        synchronized (ShowRoomLock.class) {
            String start;
        }
    }
}