package ru.job4j.linked;

/*
 Шаги, которые необходимо предпринять, чтобы класс стал immutable:
 1. Запретите расширение класса – либо объявите его final, либо закройте доступ наследникам ко всем способам мутации, перечисленным в следующих пунктах;
 2. Сделайте все поля финальными;
 3. Не выставляйте наружу методов-мутаторов, которые меняют состояние;
 4. Не отдавайте наружу поля ссылочного изменяемого типа (объекты классов, массивы) – если объект под ссылкой не иммутабельный, должна возвращаться его глубокая копия (defensive copy);
 */

public final class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }
}