import java.util.Scanner;

public class FibonacciIteration {
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int number1 = 0;
		int number2 = 1;
		int repeat = s.nextInt();
		s.close();
		long start = System.currentTimeMillis(); // 실행 시간
		for (int n = 1; n <= repeat; n++) {
			if (n % 2 == 1) {
				number2 = number2 + number1;
			} else {
				number1 = number1 + number2;
			}
		}
		if (number1 > number2)
			System.out.println(number1);
		else
			System.out.println(number2);
		long end = System.currentTimeMillis();
		System.out.println("실행 시간 : " + (end - start) / 1000.0 + "초");
	}
}