import java.util.*;

public class FibonacciRecursive {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        s.close();
        long start = System.currentTimeMillis(); // 시간 측정
        int result = fibonacci(num);
        System.out.println(result);

        long end = System.currentTimeMillis();
        System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
    }

    public static int fibonacci(int number) {
        int result = 0;
        if (number >= 2)
            result = fibonacci(number - 1) + fibonacci(number - 2);
        else if (number == 1)
            result += 1;
        else
            result += 0;
        return result;
    }
}
