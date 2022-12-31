package hf.dp.template_method;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class BeverageTestDrive {
    public static void main(String[] args) {
        printInfo();

        Tea tea = new Tea();
        Coffe coffe = new Coffe();
        System.out.println("\nMaking tea...");
        tea.prepareRecipe();
        System.out.println("\nMaking coffee...");
        coffe.prepareRecipe();
        TeaWithHook teaHook = new TeaWithHook();
        CoffeeWithHook coffeeWithHook = new CoffeeWithHook();
        System.out.println("\nMaking tea...");
        teaHook.prepareRecipe();
        System.out.println("\nMaking coffee...");
        coffeeWithHook.prepareRecipe();
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
