package com;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class SignUpController extends WindowController implements Initializable {
    @FXML private Button backBtn;
    @FXML private Button signUpBtn;
    @FXML private TextField txtID;
    @FXML private TextField txtPW;
    @FXML private TextField txtPWCheck;
    @FXML private TextField txtEmail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signUpBtn.setOnAction(e -> signUp());
        backBtn.setOnAction(e -> openLogin());
    }


    public void signUp() {
        if (txtID.getText().isEmpty() || txtPW.getText().isEmpty() || txtPWCheck.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("아이디 혹은 비밀번호를 입력하지 않았습니다!!");
            alert.show();
            txtID.clear();
            txtPW.clear();
            txtPWCheck.clear();
            txtEmail.clear();
            txtID.requestFocus();
        }
        else if (! txtPW.getText().equals(txtPWCheck.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("비밀번호가 일치하지 않습니다!!");
            alert.show();
            txtID.clear();
            txtPW.clear();
            txtPWCheck.clear();
            txtEmail.clear();
            txtID.requestFocus();
        }
        else{
            try {
                System.out.println("Connected: " + socket);
                Scanner sc = new Scanner(System.in);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                byte[] receivePacket = new byte[2];

                protocol = new Protocol(MSG.PT_TYPE_SEND);
                protocol.setSend(MSG.PT_SEND_SIGN_UP);

                protocol.setStrToByte(txtID.getText());
                protocol.setStrToByte(txtPW.getText());
                protocol.setStrToByte(txtEmail.getText());

                dos.write(protocol.getPacket(), 0, protocol.getSize());
                dos.flush();

                dis.read(receivePacket);

                if (receivePacket[0] == MSG.PT_TYPE_RESPOND && receivePacket[1] == MSG.PT_RES_SUCESS) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("회원가입 성공");
                    alert.show();

                    openLogin();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("회원가입 실패. 다시 시도해주세요!!");
                    alert.show();
                    txtID.clear();
                    txtPW.clear();
                    txtPWCheck.clear();
                    txtEmail.clear();
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
        Stage stage11 = (Stage) backBtn.getScene().getWindow();
        Platform.runLater(() -> {
            stage11.close();
        });
    }

}
