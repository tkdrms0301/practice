import java.util.Scanner;

public class NameBST {
    BinarySearchTree<String> bst;

    public NameBST() {
    }

    public void input(String input) {
        String[] nameList = input.split(" ");
        bst = new BinarySearchTree<String>();
        for (int i = 0; i < nameList.length; i++) {
            bst.put(nameList[i]);
        }
    }

    public void print() {
        bst.print(System.out);
    }
}
