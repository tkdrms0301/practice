package hf.dp.composite;

import java.util.Stack;
import java.util.Iterator;

public class CompositeIterator implements Iterator {
    Stack stack = new Stack<>();

    public CompositeIterator(Iterator iterator) {
        stack.push(iterator);
    }

    @Override
    public MenuComponent next() {
        if (hasNext()) {
            Iterator iterator = (Iterator) stack.peek();
            MenuComponent component = (MenuComponent) iterator.next();
            if (component instanceof Menu)
                stack.push(component.createIterator());
            return component;
        }else return null;
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) return false;
        Iterator iterator = (Iterator) stack.peek();
        if (!iterator.hasNext()) {
            stack.pop();
            return hasNext();
        }else return true;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
