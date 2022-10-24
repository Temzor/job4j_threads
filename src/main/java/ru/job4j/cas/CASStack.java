package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASStack<T> {
    private final AtomicReference<Node<T>> head = new AtomicReference<>();

    public void push(T value) {
        Node<T> temp = new Node<>(value);
        Node<T> reference;
        do {
            reference = head.get();
            temp.next = reference;
        } while (!head.compareAndSet(reference, temp));
    }

    public T poll() {
        Node<T> temp;
        Node<T> reference;
        do {
            reference = head.get();
            if (reference == null) {
                throw new IllegalStateException("Stack is empty");
            }
            temp = reference.next;
        } while (!head.compareAndSet(reference, temp));
            reference.next = null;
            return reference.value;
    }
    private static final class Node<T> {
        final T value;

        Node<T> next;

        public Node(final T value) {
            this.value = value;
        }
    }

}
