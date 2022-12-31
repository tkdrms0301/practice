import java.util.EmptyStackException;

public class ArrayStack<E> {
    private E[] s;
    private int top;

    public ArrayStack() {
        s = (E[]) new Object[1];
        top = -1;
    }

    public int getSize() {
        return top + 1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return s[top];
    }

    public void push(E newItem) {
        if (getSize() == s.length)
            regetSize(2 * s.length);
        s[++top] = newItem;
    }

    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        E item = s[top];
        s[top--] = null;
        if (getSize() > 0 && getSize() == s.length / 4)
            regetSize(s.length / 2);
        return item;
    }

    public void regetSize(int k) {
        Object[] newS = new Object[k];
        for (int i = 0; i < getSize(); i++)
            newS[i] = s[i];
        s = (E[]) newS;
    }
}