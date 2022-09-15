package ru.job4j.tree.binary;

import java.util.Stack;

public class BinaryTree {
    /* Корневой узел */
    private Node rootNode;

    /* Пустое дерево */
    public BinaryTree() {
        rootNode = null;
    }

    /* Поиск узла по значению */
    public Node findNodeByValue(int value) {
        /* Начинаем поиск с корневого узла */
        Node currentNode = rootNode;
        /* Поиск покуда не будет найден элемент или не будут перебраны все */
        while (currentNode.getValue() != value) {
            /* Движение влево? */
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeftChild();
                /* Движение вправо */
            } else {
                currentNode = currentNode.getRightChild();
            }
            /* Если потомка нет, возвращаем null */
            if (currentNode == null) {
                return null;
            }
        }
        /* возвращаем найденный элемент */
        return currentNode;
    }

    /* метод вставки нового элемента */
    public void insertNode(int value) {
        /* создание нового узла */
        Node newNode = new Node();
        /* вставка данных */
        newNode.setValue(value);
        /* если корневой узел не существует */
        if (rootNode == null) {
            /* то новый элемент и есть корневой узел */
            rootNode = newNode;
            /* корневой узел занят */
        } else {
            /* начинаем с корневого узла */
            Node currentNode = rootNode;
            Node parentNode;
            /* мы имеем внутренний выход из цикла */
            while (true) {
                parentNode = currentNode;
                /* если такой элемент в дереве уже есть, не сохраняем его */
                if (value == currentNode.getValue()) {
                    /* просто выходим из метода */
                    return;
                    /* движение влево? */
                } else if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeftChild();
                    /* если был достигнут конец цепочки, */
                    if (currentNode == null) {
                        /*  то вставить слева и выйти из методы */
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                    /* Или направо? */
                } else {
                    currentNode = currentNode.getRightChild();
                    /* если был достигнут конец цепочки, */
                    if (currentNode == null) {
                        /*то вставить справа*/
                        parentNode.setRightChild(newNode);
                        /* и выйти*/
                        return;
                    }
                }
            }
        }
    }

    /* Удаление узла с заданным ключом */
    public boolean deleteNode(int value) {
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftChild = true;
        /* начинаем поиск узла */
        while (currentNode.getValue() != value) {
            parentNode = currentNode;
            /* Определяем, нужно ли движение влево?*/
            if (value < currentNode.getValue()) {
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
                /* или движение вправо? */
            } else {
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) {
                /* yзел не найден */
                return false;
            }
        }

        /* узел просто удаляется, если не имеет потомков */
        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            if (currentNode == rootNode) {
                /* если узел - корень, то дерево очищается*/
                rootNode = null;
            } else if (isLeftChild) {
                /* если нет - узел отсоединяется, от родителя */
                parentNode.setLeftChild(null);
            } else {
                parentNode.setRightChild(null);
            }
        } else if (currentNode.getRightChild() == null) {
            /* узел заменяется левым поддеревом, если правого потомка нет */
            if (currentNode == rootNode) {
                rootNode = currentNode.getLeftChild();
            } else if (isLeftChild) {
                parentNode.setLeftChild(currentNode.getLeftChild());
            } else {
                parentNode.setRightChild(currentNode.getLeftChild());
            }
            /* узел заменяется правым поддеревом, если левого потомка нет */
        } else if (currentNode.getLeftChild() == null) {
            if (currentNode == rootNode) {
                rootNode = currentNode.getRightChild();
            } else if (isLeftChild) {
                parentNode.setLeftChild(currentNode.getRightChild());
            } else {
                parentNode.setRightChild(currentNode.getRightChild());
            }
            /* если есть два потомка, узел заменяется преемником */
        } else {
            /* поиск преемника для удаляемого узла */
            Node heir = receiveHeir(currentNode);
            if (currentNode == rootNode) {
                rootNode = heir;
            } else if (isLeftChild) {
                parentNode.setLeftChild(heir);
            } else {
                parentNode.setRightChild(heir);
            }
        }
        /* элемент успешно удалён*/
        return true;
    }

    /**
     * Метод возвращает узел со следующим значением после передаваемого аргументом.
     * Для этого он сначала переходим к правому потомку, а затем
     * Отслеживаем цепочку левых потомков этого узла.
     */
    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        /* Переход к правому потомку */
        Node currentNode = node.getRightChild();
        /* Пока остаются левые потомки */
        while (currentNode != null) {
            /* потомка задаём как текущий узел */
            parentNode = heirNode;
            heirNode = currentNode;
            /* переход к левому потомку*/
            currentNode = currentNode.getLeftChild();
        }
        /* Если преемник не является правым потомком, создать связи между узлами */
        if (heirNode != node.getRightChild()) {
            parentNode.setLeftChild(heirNode.getRightChild());
            heirNode.setRightChild(node.getRightChild());
        }
        /* возвращаем приемника */
        return heirNode;
    }

    /* метод для вывода дерева в консоль */
    public void printTree() {
        /* общий стек для значений дерева */
        Stack globalStack = new Stack();
        globalStack.push(rootNode);
        /* начальное значение расстояния между элементами */
        int gaps = 32;
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        /* черта для указания начала нового дерева */
        System.out.println(separator);
        while (isRowEmpty) {
            /* локальный стек для задания потомков элемента */
            Stack localStack = new Stack();
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++) {
                System.out.print(' ');
            }
            /* покуда в общем стеке есть элементы */
            while (globalStack.isEmpty()) {
                /* берем следующий, при этом удаляя его из стека*/
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    /* выводим его значение в консоли */
                    System.out.print(temp.getValue());
                    localStack.push(temp.getLeftChild());
                    /* соохраняем в локальный стек, наследники текущего элемента */
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null
                            || temp.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    /* - если элемент пустой */
                    System.out.print("__");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++) {
                    System.out.print(' ');
                }
            }
            System.out.println();

            gaps /= 2;
            while (localStack.isEmpty()) {
                /* перемещаем все элементы из локального стека в глобальный */
                globalStack.push(localStack.pop());
            }
        }
        /* подводим черту */
        System.out.println(separator);
    }
}
