package hf.dp.strategy;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        printInfo();
        FlyWithWings flyWithWings = new FlyWithWings();
        FlyNoWay flyNoWay = new FlyNoWay();

        Quack quack = new Quack();
        Squeak squeak = new Squeak();
        MuteQuack muteQuack = new MuteQuack();

        System.out.println("Hello mallard duck!");
        Duck mallard = new MallardDuck();
        mallard.setQuackBehavior(quack);
        mallard.setFlyBehavior(flyWithWings);
        mallard.performQuack();
        mallard.performFLy();

        System.out.println("Hello model!");
        Duck model = new ModelDuck();
        model.setFlyBehavior(flyNoWay);
        model.setQuackBehavior(squeak);
        model.performQuack();
        model.performFLy();
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