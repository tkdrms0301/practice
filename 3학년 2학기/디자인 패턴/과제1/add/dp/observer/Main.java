package add.dp.observer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
	public static void main(String[] args) {
		printInfo();
		NumberGenerator generator = new RandomNumberGenerator();
		
		
		Observer observer1 = new DigitObserver();
		Observer observer2 = new GraphObserver();
		
		
		generator.addObserver(observer1);
		generator.addObserver(observer2);

		generator.execute();
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
