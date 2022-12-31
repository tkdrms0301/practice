import java.io.*;

public class CircleDoublyLinkedListMain {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        CircleDoublyLinkedList cdll = new CircleDoublyLinkedList();
        String path = args[0];
        cdll.mainMenu(path);
    }
}
