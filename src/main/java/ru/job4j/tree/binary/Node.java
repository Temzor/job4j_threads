package ru.job4j.tree.binary;

class Node {

    /* Ключ узла */
    private int value;

    /* Левый узел потомок */
    private Node leftChild;

    /* Правый узел потомок */
    private Node rightChild;

    /* Вывод значения узла в консоль */
    public void printNode() {
        System.out.println(" Выбранный узел имеет значение :" + value);
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public void setLeftChild(final Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return this.rightChild;
    }

    public void setRightChild(final Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return String.format("Node(value=%s ,leftChild=%s ,rightChild=%s)",
                this.value,
                this.leftChild,
                this.rightChild);
    }
}
