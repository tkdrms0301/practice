import java.util.Scanner;

public class NameBSTMain {
    public static void main(String[] args) {
        NameBST nameBST = new NameBST();
        Scanner s = new Scanner(System.in);
        String a = new String(s.nextLine().getBytes());
        System.out.println(a);
        while (true) {
            System.out.print("input String (quit is exit) : ");
            String input = s.nextLine();
            if (input.equals("quit"))
                break;
            nameBST.input(input);
            nameBST.print();
        }
    }
}
