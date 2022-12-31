package add.dp.strategy;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
	public static void main(String[] args) {
		printInfo();
		if (args.length != 2) {
			System.out.println("Usage: java Main randomseed1 randomseed2");
			System.out.println("Example: java Main 314 15");
			System.exit(0);
		}

		
		
		int seed1 = Integer.parseInt(args[0]);
		int seed2 = Integer.parseInt(args[1]);

		
		Player player1 = new Player("홍길동", new WinningStrategy(seed1));
		Player player2 = new Player("임꺽정", new ProbStrategy(seed2));

		
		for (int i = 0; i < 100; i++) {
			Hand nextHand1 = player1.nextHand();
			Hand nextHand2 = player2.nextHand();
			if (nextHand1.isStrongerThan(nextHand2)) {
				System.out.println("Winner:" + player1);
				player1.win();
				player2.lose();
			} else if (nextHand2.isStrongerThan(nextHand1)) {
				System.out.println("Winner:" + player2);
				player1.lose();
				player2.win();
			} else {
				System.out.println("Even...");
				player1.even();
				player2.even();
			}
		}

		System.out.println("Total result:");
		System.out.println("" + player1);
		
		System.out.println("" + player2);
	}

	public static void printInfo() {
		System.out.println("Date: " + LocalDate.now());
		System.out.println("Time: " + LocalTime.now());
		try {
			NetworkInterface netinf = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
			byte[] mac = netinf.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++)
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			System.out.println("MAC Addr.: " + sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("USER: " + System.getProperty("user.name"));
		System.out.println("===========================================\n");
	}
}
