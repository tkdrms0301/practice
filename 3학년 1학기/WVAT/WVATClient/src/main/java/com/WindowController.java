package com;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class WindowController {
    String userId;
    static Socket socket;
    Protocol protocol;
    DataInputStream dis;
    DataOutputStream dos;

    public void openLogin(){
        Parent root;
        try {
            closeStage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("loginView.fxml"));
            root = (Parent) loader.load();
            Scene scene = new Scene(root);

            LoginController loginController = loader.getController();
            loginController.setSocket(socket);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Web Vulnerability Analysis Tool");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openMyReport(){
        Parent root;
        try {
            closeStage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("myReport.fxml"));
            root = (Parent) loader.load();
            Scene scene = new Scene(root);

            MyReportController myReportController = loader.getController();
            myReportController.setUserId(userId);
            myReportController.setSocket(socket);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Web Vulnerability Analysis Tool");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openInspection(){
        Parent root;
        try {
            closeStage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("inspectionView.fxml"));
            root = (Parent) loader.load();
            Scene scene = new Scene(root);
            System.out.println("Connected: " + socket);

            InspectionController inspectionController = loader.getController();
            inspectionController.setUserId(userId);
            inspectionController.setSocket(socket);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Web Vulnerability Analysis Tool");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openGuideLine(){
        Parent root;
        try {
            closeStage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("guideLine.fxml"));
            root = (Parent) loader.load();
            Scene scene = new Scene(root);

            GuideLineController guideLineController = loader.getController();
            guideLineController.setUserId(userId);
            guideLineController.setSocket(socket);
            guideLineController.open();

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Web Vulnerability Analysis Tool");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openSignUp(){
        Stage primaryStage = new Stage();
        Parent root;
        try {
            closeStage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("signUpView.fxml"));
            root = (Parent) loader.load();
            Scene scene = new Scene(root);

            SignUpController signUpController = loader.getController();
            signUpController.setSocket(socket);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Web Vulnerability Analysis Tool");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeStage() {
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public void setSocket(Socket socket) {this.socket = socket;}

    public String changeByteToString(byte[] recvHeader) throws IOException {
        dis = new DataInputStream(socket.getInputStream());
        int bytesRcvd;
        int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

        int dataLength = byteArrayToInt(recvHeader);
        if (dataLength == 0) return null;

        byte[] recvData = new byte[dataLength];

        while (totalBytesRcvd < dataLength) {
            if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
                throw new SocketException("Connection close prematurely");
            totalBytesRcvd += bytesRcvd;
        }

        return new String(recvData);
    }

    protected static int byteArrayToInt(byte[] data) {
        if (data == null || data.length != 4) return 0x0;
        return (int)((0xff & data[0]) << 24 | (0xff & data[1]) << 16 | (0xff & data[2]) << 8 | (0xff & data[3]) << 0);
    }
}
