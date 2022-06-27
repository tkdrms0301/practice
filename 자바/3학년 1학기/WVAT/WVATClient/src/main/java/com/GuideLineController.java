package com;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.RowConstraints;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

public class GuideLineController extends WindowController implements Initializable {
    @FXML
    private Button myReportBtn;
    @FXML private Button inspectionBtn;
    @FXML private Label day1;
    @FXML private Label day2;
    @FXML private Label day3;
    @FXML private Label day4;
    @FXML private Label day5;
    @FXML private Label day6;
    @FXML private Label day7;
    @FXML private Label day8;
    @FXML private Label day9;
    @FXML private Label day10;
    @FXML private Label adminExpose;
    @FXML private Label sqlInjection;
    @FXML private Label xss;
    @FXML private Label pathTracking;
    @FXML private Label webMethod;
    @FXML private Label informationLeakage;
    @FXML private Label osCommand;
    @FXML private Label directoryIndexing;
    @FXML private Label locationDisclosure;
    @FXML private Label plainText;

    Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myReportBtn.setOnAction(e -> openMyReport());
        inspectionBtn.setOnAction(e -> openInspection());

        Label[] download = { adminExpose, sqlInjection, pathTracking, webMethod, xss, informationLeakage, osCommand, directoryIndexing, locationDisclosure, plainText};
        for(int i =0; i < download.length; i++ ){
            download[i].setCursor(Cursor.HAND);
        }
    }
    public void closeStage() {
        Stage stage11 = (Stage) myReportBtn.getScene().getWindow();
        Platform.runLater(() -> {
            stage11.close();
        });
    }

    public void open() {
        try {
            Label[] label = { day1, day2, day3, day4, day5, day6, day7, day8, day9, day10};

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            byte[] receivePacket = new byte[2];

            protocol = new Protocol(MSG.PT_TYPE_REQUEST);
            protocol.setRequest(MSG.PT_REQ_GUIDELINE);

            dos.write(protocol.getPacket(), 0, protocol.getSize());
            dos.flush();

            dis.read(receivePacket);

            if (receivePacket[0] == MSG.PT_TYPE_RESPOND && receivePacket[1] == MSG.PT_RES_GUIDELINE_RECORD_SEND) {
                receivePacket = new byte[4];
                dis.read(receivePacket);

                int length = Integer.parseInt(changeByteToString(receivePacket));

                for (int i = 0; i < length; i++) {
                    dis.read(receivePacket);
                    label[i].setText(changeByteToString(receivePacket));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void guideLineDownload(MouseEvent event) {
        // open url etc
        System.out.println("You clicked label: " + ((Label)event.getSource()).getId());

        try {

            String filePath = "";
            DirectoryChooser directoryChooser = new DirectoryChooser();

            //현재 화면에 띄울수 없으므로 위에서 선언한 stage를 사용한다.
            File dir = directoryChooser.showDialog(primaryStage);

            //아무것도 선택하지 않고 취소를 하면  else를 실행한다.
            if (dir != null) {
                filePath = dir.getPath();
            }

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            byte[] receivePacket = new byte[2];

            protocol = new Protocol(MSG.PT_TYPE_REQUEST);
            protocol.setRequest(MSG.PT_REQ_GUIDELINE_DOWNLOAD);

            protocol.setStrToByte(((Label)event.getSource()).getId());


            dos.write(protocol.getPacket(), 0, protocol.getSize());
            dos.flush();



            dis.read(receivePacket);

            if (receivePacket[0] == MSG.PT_TYPE_RESPOND && receivePacket[1] == MSG.PT_RES_GUIDELINE_SEND) {
                receivePacket = new byte[MSG.FIXED_LEN];
                dis.read(receivePacket);

                String fileName = changeByteToString(receivePacket);
                String home = System.getProperty("user.home");
                File file = new File(filePath + "\\" +fileName);
                FileOutputStream fos = new FileOutputStream(file);

                System.out.println(file + " 파일 생성");


                dis.read(receivePacket);

                int bytesRcvd;
                int totalBytesRcvd = 0;  // 지금까지 받은 바이트 수

                int dataLength = byteArrayToInt(receivePacket);
                if (dataLength != 0) {
                    byte[] recvData = new byte[dataLength];

                    while (totalBytesRcvd < dataLength) {
                        if ((bytesRcvd = dis.read(recvData, totalBytesRcvd, dataLength - totalBytesRcvd)) == -1)
                            throw new SocketException("Connection close prematurely");
                        totalBytesRcvd += bytesRcvd;
                    }
                    System.out.println("totalByte : " + totalBytesRcvd);
                    fos.write(recvData, 0, totalBytesRcvd);
                }


                fos.flush();
                fos.close();
            }
        } catch (IOException e) {

        }
    }

    public void guideLine() {

    }
}
