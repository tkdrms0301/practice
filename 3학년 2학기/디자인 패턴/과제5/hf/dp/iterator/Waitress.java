package hf.dp.iterator;

import java.util.Iterator;

public class Waitress {
    PancakeHouseMenu pancakeHouseMenu;
    DinnerMenu dinerMenu;

    public Waitress(PancakeHouseMenu pancakeHouseMenu, DinnerMenu dinerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator<MenuItem> dinerIterator =  dinerMenu.createIterator();
        System.out.println("Menu\n----\nBREAKFAST");
        printMenu(pancakeIterator);
        System.out.println("\nLUNCH");
        printMenu(dinerIterator);
    }

    private void printMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.println(menuItem.getName() + ", ");
        }
    }

    public void printVegetarianMenu() {
        Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator<MenuItem> dinerIterator =  dinerMenu.createIterator();
        System.out.println("Menu\n----\nBREAKFAST");
        printVegetarianMenu(pancakeIterator);
        System.out.println("\nLUNCH");
        printVegetarianMenu(dinerIterator);
    }

    private void printVegetarianMenu(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            if (menuItem.isVegetarian())
                System.out.println(menuItem.getName() + ", ");
        }
    }
}
