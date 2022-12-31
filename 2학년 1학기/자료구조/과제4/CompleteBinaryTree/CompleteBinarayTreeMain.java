import java.util.Scanner;

public class CompleteBinarayTreeMain {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("input the number of node : ");
        int nodeNumber = s.nextInt();
        CompleteBinarayTree<Integer> cbt = new CompleteBinarayTree<Integer>(nodeNumber);
        cbt.printTreeNumber();
    }
}
