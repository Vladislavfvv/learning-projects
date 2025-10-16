package org.example;

/**
 * Create your own realization of LinkedList and implement the following operations:
 * + size() - returns the size of the list
 * + addFirst(el) - adds the element in the beginning of the list
 * + addLast(el) - adds the element in the end of the list
 * + add(index, el) - adds the element in the list by index
 * + getFirst() - returns the first element of the list
 * + getLast() - returns the last element of the list
 * + get(index) - returns the element by index
 * + removeFirst() - retrieve and remove the first element of the list
 * + removeLast() - retrieve and remove the last element of the list
 * + remove(index) - retrieve and remove the element of the list by index
 * + Cover all these operations with unit tests using JUnit 5
 */

public class CustomLinkedList<T> {

    public CustomLinkedList() {
    }

    public static class Node<T> {
        T el; //data
        Node<T> next;
        Node<T> previous;

        public Node(T el) {
            this.el = el;
            this.next = null;
            this.previous = null;
        }
    }

    private int size;
    private Node<T> head;
    private Node<T> tail;

    public int size() {
        return this.size;
    }

    public void addFirst(T el) {
        Node<T> newNode = new Node<>(el);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(T el) {
        Node<T> newNode = new Node<>(el);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    public Node<T> get(int index) {
        checkOutOfBounds(index);
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    public void add(int index, T el) {
        checkOutOfBounds(index);
        if (index == 0) {
            addFirst(el);
            return;
        }
        if (index == size) {
            addLast(el);
            return;
        }

        Node<T> currentNode = get(index);
        Node<T> previousNode = currentNode.previous;
        Node<T> newNode = new Node<>(el);

        newNode.previous = previousNode;
        previousNode.next = newNode;

        currentNode.previous = newNode;
        newNode.next = currentNode;

        size++;
    }

    public Node<T> removeFirst() {
        checkNotEmpty();
        Node<T> current = head;
        head = head.next;
        if (head != null) {
            head.previous = null;
        } else {
            tail = null;
        }
        size--;
        return current;
    }

    public Node<T> removeLast() {
        checkNotEmpty();
        Node<T> current = tail;
        tail = tail.previous;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return current;
    }

    public Node<T> remove(int index) {
        checkOutOfBounds(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }

        Node<T> current = get(index);
        Node<T> previousNode = current.previous;
        Node<T> nextNode = current.next;

        previousNode.next = nextNode;
        nextNode.previous = previousNode;
        size--;

        return current;
    }

    public Node<T> getFirst() {
        checkNotEmpty();
        return head;
    }

    public Node<T> getLast() {
        checkNotEmpty();
        return tail;
    }

    public Node<T> getByElement(T el) {
        Node<T> current = head;
        while (current != null) {
            if (current.el.equals(el)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void checkNotEmpty() {
        if (size == 0)
            throw new IllegalStateException("List is empty");
    }

    private void checkOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    }
}
