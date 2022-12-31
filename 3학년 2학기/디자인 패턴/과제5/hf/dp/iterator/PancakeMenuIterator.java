package hf.dp.iterator;

import java.util.ArrayList;
import java.util.Iterator;

public class PancakeMenuIterator implements Iterator<MenuItem> {
    ArrayList<MenuItem> items;
    int position = 0;

    public PancakeMenuIterator(ArrayList<MenuItem> items) {
        this.items = items;
    }

    @Override
    public MenuItem next() {
        MenuItem item = items.get(position);
        position++;
        return item;
    }

    public boolean hasNext() {
        if (position >= items.size())
            return false;
        else
            return true;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Can't delete menu items");
    }

    public Iterator createIterator() {
        return new PancakeMenuIterator(items);
    }
}
