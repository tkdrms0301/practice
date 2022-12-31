package hf.dp.iterator;

import java.util.Iterator;

public class DinnerMenu implements Menu {
    static final int MAX_ITEMS = 6;

    String name;
    String description;
    boolean vegetarian;
    double price;

    MenuItem[] items;
    int position = 0;

    public DinnerMenu() {
        this.items = new MenuItem[MAX_ITEMS];
        addItem("Vegetarian BLT", "(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99);
        addItem("BLT", "Bacon with lettuce & tomato on whole wheat", false, 2.99);
        addItem("Soup of the day", "A bowl of the soup of the day, with a side of potato salad", false, 3.29);
        addItem("Hotdog", "A hot dog, with saurkraut, relish, onions, topped with cheese", false, 3.05);
        addItem("Steamed Veggies and Brown Rice", "A medly of steamed vegetables over brown rice", true, 3.99);
        addItem("Pasta", "Spaghetti with Marinara Sauce, and a slice of sourdough bread", true, 3.89);
    }

    public Object next() {
        MenuItem menuItem = items[position];
        position++;
        return menuItem;
    }

    public boolean hasNext() {
        if (position >= items.length || items[position] == null)
            return false;
        else
            return true;
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        items[position] = menuItem;
        position++;
    }

    public Iterator<MenuItem> createIterator() {
        return new DinnerMenuIterator(items);
    }
}
