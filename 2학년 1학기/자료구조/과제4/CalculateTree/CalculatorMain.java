import java.util.Scanner;

public class CalculatorMain {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner s = new Scanner(System.in);
        String notation;
        while (true) {
            System.out.print("input notation (quit is exit) : ");
            notation = s.nextLine();
            if (notation.equals("quit")) {
                break;
            }
            calculator.transPostfix(notation);
            calculator.queueToTree();
            calculator.print();
        }
        s.close();
    }
}