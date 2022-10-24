package ru.job4j.cas;

public final class Node<T> {
    private final T value;

    private Node<T> next;

    public Node(final T value) {
        this.value = value;
    }
}
