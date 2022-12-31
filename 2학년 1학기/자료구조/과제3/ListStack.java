import java.util.EmptyStackException;

public class ListStack<E> {
    private class Node {
        E item;
        Node next;

        public Node(E newItem, Node next) {
            item = newItem;
            this.next = next;
        }

        public E getItem() {
            return item;
        }

        public Node getNext() {
            return next;
        }
    }

    private Node top;
    private int size;

    public ListStack() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return top.getItem();
    }

    public void push(E newItem) {
        Node newNode = new Node(newItem, top);
        top = newNode;
        size++;
    }

    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        E topItem = top.getItem();
        top = top.getNext();
        size--;
        return topItem;
    }
}