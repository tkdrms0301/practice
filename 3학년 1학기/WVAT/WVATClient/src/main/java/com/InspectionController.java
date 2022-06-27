package com;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InspectionController extends WindowController implements Initializable {

    int a;
    @FXML private Button myReportBtn;
    @FXML private Button guideLineBtn;
    @FXML private ComboBox comboBox;
    @FXML private TextField currentUrl;
    @FXML private ProgressBar progressBar;
    @FXML private Button executeInspctionBtn;
    @FXML private TextField progressText;
    @FXML private TextField inspectionUrl;
    @FXML private CheckBox sqlCheck;
    @FXML private Button downloadBtn;
    @FXML private CheckBox xssCheck;
    @FXML private CheckBox adminCheck;
    @FXML private CheckBox pathCheck;
    @FXML private CheckBox osCheck;
    @FXML private CheckBox directoryCheck;
    @FXML private CheckBox plainCheck;
    @FXML private CheckBox webCheck;
    @FXML private CheckBox informationCheck;
    @FXML private CheckBox cveCheck;
    @FXML private CheckBox locationCheck;
    Stage primaryStage;

    double count  = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        myReportBtn.setOnAction(e -> openMyReport());
        guideLineBtn.setOnAction(e -> openGuideLine());
        executeInspctionBtn.setOnAction(e -> executeInspection());
        downloadBtn.setOnAction(e -> reportDownload());
        //downloadBtn.setDisable(true);
        currentUrl.setDisable(true);

        String[] strArr = {"단일 페이지", "복합 페이지"};
        ObservableList<String> fxComboBoxList = FXCollections.observableArrayList(strArr);
        comboBox.setItems(fxComboBoxList);
    }


    public void closeStage() {
        Stage stage11 = (Stage) myReportBtn.getScene().getWindow();
        Platform.runLater(() -> {
            stage11.close();
        });
    }
    public void executeInspection() {
        progressBar.setProgress(0);
        progressText.setText("URL 파싱중...");
        try {
            boolean checkList[] = new boolean[]{xssCheck.isSelected(), sqlCheck.isSelected(), osCheck.isSelected(), adminCheck.isSelected(),
                    locationCheck.isSelected(), pathCheck.isSelected(), directoryCheck.isSelected(), informationCheck.isSelected(),
                    plainCheck.isSelected(),  webCheck.isSelected(), cveCheck.isSelected()};
            System.out.println("Connected: " + socket);
            Scanner sc = new Scanner(System.in);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            protocol = new Protocol(MSG.PT_TYPE_SEND);
            protocol.setRequest(MSG.PT_REQ_INSPECTION);
            protocol.setStrToByte(inspectionUrl.getText());
            protocol.setBooltoByte(checkList);

            if (comboBox.getSelectionModel().getSelectedItem().toString().equals("단일 페이지")){
                protocol.setStrToByte("single");
            }else if(comboBox.getSelectionModel().getSelectedItem().toString().equals("복합 페이지")){
                protocol.setStrToByte("multi");
            }

            dos.write(protocol.getPacket(), 0, protocol.getSize());
            dos.flush();

            byte[] receivePacket = new byte[2];

            dis.read(receivePacket);

            if (receivePacket[0] == MSG.PT_TYPE_RESPOND && receivePacket[1] == MSG.PT_RES_SUCESS) {
                byte receiveHeader[] = new byte[MSG.FIXED_LEN];
                String curUrl;
                protocol = new Protocol(MSG.PT_TYPE_REQUEST);
                protocol.setRequest(MSG.PT_REQ_INSPECTION_PROGRESS);
                dos.write(protocol.getPacket(), 0, protocol.getSize());
                dos.flush();

                Thread thread = new Thread() {
                    @Override
                    public synchronized void run() {
                                double progressPercent = 0;  //퍼센트
                                String percentStr;
                                int cnt = 0;
                                try{
                                    while (progressPercent < 1) {
                                        if (socket.isClosed()) {
                                            System.out.println("socket down");
                                        }

                                        System.out.println("읽는 중 : " + cnt);
                                        dis.read(receivePacket);
                                        cnt++;
                                        if (receivePacket[0] == MSG.PT_TYPE_RESPOND && receivePacket[1] == MSG.PT_RES_INSPECTION_PROGRESS) {
                                            System.out.println("recv0 : " + receivePacket[0] + ", recv 1 : " + receivePacket[1]);
                                            dis.read(receiveHeader);
                                            percentStr = changeByteToString(receiveHeader);
                                            progressPercent = Double.parseDouble(percentStr);
                                            System.out.println("progress : " + progressPercent);

                                            dis.read(receiveHeader);
                                            String test = changeByteToString(receiveHeader);
                                            System.out.println("url : " + test);
                                            count = progressPercent;
                                            final double progressPercents = progressPercent;
                                            a = (int) (progressPercent*100);
                                            Platform.runLater(new Runnable() {
                                                @Override public void run() {
                                                    currentUrl.setText(test);
                                                    progressBar.setProgress( progressPercents);
                                                    progressText.setText(String.valueOf(a) + "%");
                                                }
                                            });
                                            Thread.sleep(100);
                                        }
                                        protocol = new Protocol(MSG.PT_TYPE_REQUEST);
                                        protocol.setRequest(MSG.PT_REQ_SIGN_UP);
                                        dos.write(protocol.getPacket(), 0, protocol.getSize());
                                        dos.flush();
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                    }
                };
                thread.setDaemon(true);
                thread.start();

                downloadBtn.setDisable(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reportDownload(){
        try{
            if (progressText.getText().equals("100%")) {

                String filePath = "";
                DirectoryChooser directoryChooser = new DirectoryChooser();

                //현재 화면에 띄울수 없으므로 위에서 선언한 stage를 사용한다.
                File dir = directoryChooser.showDialog(primaryStage);

                //아무것도 선택하지 않고 취소를 하면  else를 실행한다.
                if (dir != null) {
                    filePath = dir.getPath();
                }

                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                byte[] receivePacket = new byte[2];


                protocol = new Protocol(MSG.PT_TYPE_REQUEST);
                protocol.setRequest(MSG.PT_REQ_REPORT_DOWNLOAD);

                dos.write(protocol.getPacket(), 0, protocol.getSize());
                dos.flush();

                dis.read(receivePacket);

                if (receivePacket[0] == MSG.PT_TYPE_RESPOND && receivePacket[1] == MSG.PT_RES_REPORT_SEND) {
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
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}