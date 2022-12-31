import java.io.*;

public class SimplyLinkedListMain {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        SimplyLinkedList sll = new SimplyLinkedList();
        String path = args[0];
        sll.mainMenu(path);
    }
}
