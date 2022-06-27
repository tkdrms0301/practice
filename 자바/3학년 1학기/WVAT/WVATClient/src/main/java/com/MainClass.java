package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainClass extends Application {
    static Socket socket;
    Protocol protocol;

    @Override
    public void start(Stage primaryStage) throws Exception{

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        byte[] receivePacket = new byte[2];

        protocol = new Protocol(MSG.PT_TYPE_REQUEST);
        protocol.setRequest(MSG.PT_REQ_SIGN_IN);

        dos.write(protocol.getPacket(), 0, protocol.getSize());
        dos.flush();

        dis.read(receivePacket);

        if (receivePacket[0] == MSG.PT_TYPE_RESPOND && receivePacket[1] == MSG.PT_RES_SUCESS) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("loginView.fxml"));
                Parent root = (Parent) loader.load();
                Scene scene = new Scene(root);

                LoginController loginController = loader.getController();
                loginController.setSocket(socket);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Web Vulnerability Analysis Tool");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            socket = new Socket("192.168.236.246", 3000);
            socket.setSoTimeout(0);
            System.out.println("Connected: " + socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }
}