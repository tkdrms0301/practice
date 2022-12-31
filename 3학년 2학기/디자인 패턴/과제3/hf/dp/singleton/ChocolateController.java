package hf.dp.singleton;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class ChocolateController {
    public static void main(String[] args) {
        printInfo();
        ChocolateBoiler boiler1 = ChocolateBoiler.getInstance();
        ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();

        boiler1.fill();
        boiler2.reportBoilerState();

        boiler1.boil();
        boiler2.reportBoilerState();

        boiler1.drain();
        boiler2.reportBoilerState();
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
