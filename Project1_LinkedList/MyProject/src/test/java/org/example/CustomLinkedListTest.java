package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListTest {

    private CustomLinkedList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new CustomLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
    }

    @Test
    void testAddFirst() {
        list.addFirst(Integer.valueOf(1000));
        assertAll(
                () -> assertEquals(1000, list.getFirst().el),
                () -> assertEquals(6, list.size()),
                () -> assertEquals(10, list.get(0).next.el));
    }

    @Test
    void testSize() {
        assertEquals(5, list.size());
    }

    @Test
    void testAddLast() {
        list.addLast(Integer.valueOf(7000));
        assertAll(
                () -> assertEquals(7000, list.getLast().el),
                () -> assertEquals(6, list.size()),
                () -> assertEquals(50, list.get(5).previous.el)
        );
    }

    @Test
    void testAdd() {
        CustomLinkedList.Node<Integer> currentNode = list.get(3);
        list.add(3, Integer.valueOf(100500));
        assertAll("Smt wrong with this method",
                () -> assertEquals(Integer.valueOf(100500), list.get(3).el),
                () -> assertEquals(6, list.size()),
                () -> assertEquals(currentNode, list.getByElement(100500).next));
    }

    @Test
    void testRemoveFirst() {
        CustomLinkedList.Node<Integer> deletedFirstNode = list.removeFirst();
        assertAll("Try to find problem in this method",
                () -> assertEquals(4, list.size()),
                () -> assertEquals(10, deletedFirstNode.el),
                () -> assertEquals(20, list.getFirst().el),
                () -> assertNull(list.getFirst().previous));
    }

    @Test
    void testRemoveLast() {
        CustomLinkedList.Node<Integer> deletedLastNode = list.removeLast();
        assertAll("Try to find problem in this method",
                () -> assertEquals(4, list.size()),
                () -> assertEquals(50, deletedLastNode.el),
                () -> assertEquals(40, list.getLast().el),
                () -> assertNull(list.getLast().next));
    }

    @Test
    void testRemoveByIndex() {
        CustomLinkedList.Node<Integer> deletedByIndex = list.remove(3);
        assertAll(
                () -> assertEquals(4, list.size()),
                () -> assertEquals(40, deletedByIndex.el),
                () -> assertNotEquals(deletedByIndex.el, list.get(3).el)
        );
    }

    @Test
    void testGetByIndex() {
        CustomLinkedList.Node<Integer> getByIndex = list.get(3);
        assertEquals(40, getByIndex.el);
    }

    @Test
    void testGetByIndexWithIndexOfBoundException() {
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(100))
        );
    }

    @Test
    void testGetFirst() {
        assertEquals(10, list.getFirst().el);
    }

    @Test
    void testGetLast() {
        assertEquals(50, list.getLast().el);
    }

    @Test
    void testGetFirstWithIllegalStateException() {
        for (int i = 0; i < 5; i++) {
            list.removeFirst();
        }
        assertEquals(0, list.size());
        assertThrows(IllegalStateException.class, () -> list.getFirst());
    }

    @Test
    void testGetLastWithIllegalStateException() {
        for (int i = list.size(); i > 0; i--) {
            list.removeLast();
        }
        assertEquals(0, list.size());
        assertThrows(IllegalStateException.class, () -> list.getFirst());
    }


    @Test
    void testRemoveFirstWithIllegalStateException() {
        for (int i = 0; i < 5; i++) {
            list.removeFirst();
        }
        assertEquals(0, list.size());
        assertThrows(IllegalStateException.class, () -> list.getFirst());
    }

    @Test
    void testRemoveLastWithIllegalStateException() {
        for (int i = 0; i < 5; i++) {
            list.removeLast();
        }
        assertEquals(0, list.size());
        assertThrows(IllegalStateException.class, () -> list.getFirst());
    }

    @Test
    void testRemoveByIndexWithIllegalStateException() {
        for (int i = 0; i < 5; i++) {
            list.removeFirst();
        }
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1)),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()))
        );
    }

}