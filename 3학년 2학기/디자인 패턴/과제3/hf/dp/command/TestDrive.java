package hf.dp.command;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestDrive {
    public static void main(String[] args) {
        printInfo();
        Lamp lamp = new Lamp();
        Command lampOnCommand = new LampOnCommand(lamp);

        Button button1 = new Button(lampOnCommand);
        button1.pressed();

        Alarm alarm = new Alarm();
        Command alarmCommand = new AlarmOnCommand(alarm);

        Button button2 = new Button(alarmCommand);
        button2.pressed();

        button2.setCommand(lampOnCommand);
        button2.pressed();
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
