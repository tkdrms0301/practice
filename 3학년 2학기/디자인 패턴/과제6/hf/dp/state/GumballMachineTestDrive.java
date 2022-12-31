package hf.dp.state;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class GumballMachineTestDrive {

	public static void main(String[] args) {
		printInfo();
		GumballMachine gumballMachine =
			new GumballMachine(10);

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);

		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();
		gumballMachine.insertQuarter();
		gumballMachine.turnCrank();

		System.out.println(gumballMachine);
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
