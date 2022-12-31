package hf.dp.observer;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.time.LocalDate;
import java.time.LocalTime;

public class WeatherMain {
    public static void main(String[] args) {
        printInfo();
        WeatherData weatherData = new WeatherData();
        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
        StatisticsDisplay satisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.4f);

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
