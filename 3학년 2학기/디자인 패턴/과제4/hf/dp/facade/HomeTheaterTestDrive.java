package hf.dp.facade;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class HomeTheaterTestDrive {
    public static void main(String[] args) {
        printInfo();

        Amplifier amp = new Amplifier("Top-O-Line Amplifer");
        Tuner tuner = new Tuner("Top-O-Line AM/FM Tuner", amp);
        DvdPlayer dvd = new DvdPlayer("Top-O-Line DVD Player", amp);
        CdPlayer cd = new CdPlayer("Top-O-Line CD Player", amp);
        Projector projector = new Projector("Top-O-Line Project", dvd);
        TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
        Screen screen = new Screen("Theater Screen");
        PopcornPopper popper = new PopcornPopper("Popcorn Popper");

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, tuner, dvd, cd, projector, lights, screen, popper);

        homeTheater.watchMovie("Raiders of the Lost Ark");
        homeTheater.endMovie();
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
