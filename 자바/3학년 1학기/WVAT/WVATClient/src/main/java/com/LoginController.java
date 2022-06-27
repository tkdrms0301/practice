package com;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LoginController extends WindowController implements Initializable {
    @FXML private Button loginBtn;
    @FXML private Button signUpBtn;
    @FXML private TextField txtID;
    @FXML private TextField txtPw;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loginBtn.setOnAction(e -> login());
        signUpBtn.setOnAction(e -> openSignUp());

    }

    public void login() {
        if (txtID.getText().isEmpty() || txtPw.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("아이디 혹은 비밀번호를 입력하지 않았습니다!!");
            alert.show();
            txtID.clear();
            txtPw.clear();
            txtID.requestFocus();
        }
        else{
            try {
//                Socket socket = new Socket("127.0.0.1", 3000);
                System.out.println("Connected: " + socket);
                Scanner sc = new Scanner(System.in);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                byte[] receivePacket = new byte[2];

                protocol = new Protocol(MSG.PT_TYPE_SEND);
                protocol.setSend(MSG.PT_SEND_SIGN_IN);
                protocol.setStrToByte(txtID.getText());
                protocol.setStrToByte(txtPw.getText());

                dos.write(protocol.getPacket(), 0, protocol.getSize());
                dos.flush();

                dis.read(receivePacket);

                if (receivePacket[0] == 2 && receivePacket[1] == 1) {
                    Parent root;
                    try {
                        closeStage();

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("threeButton.fxml"));
                        root = (Parent) loader.load();
                        Scene scene = new Scene(root);

                        MainController mainController = loader.getController();
                        mainController.setSocket(socket);
                        mainController.setUserId(txtID.getText());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Web Vulnerability Analysis Tool");
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("로그인 실패!! 아이디 혹은 비밀번호가 맞지 않습니다!!");
                    alert.show();
                    txtID.clear();
                    txtPw.clear();
                    txtID.requestFocus();
                }
            } catch (UnknownHostException uhe) {
                System.out.println("Host unknown : " + uhe.getMessage());
            } catch (IOException ioe) {
                System.out.println("Unexpected exception: " + ioe.getMessage());
            }
        }
    }

    public void closeStage() {
        Stage stage11 = (Stage) loginBtn.getScene().getWindow();
        Platform.runLater(() -> {
            stage11.close();
        });
    }



}
