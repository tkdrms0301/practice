package com;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.SocketException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MyReportController extends WindowController implements Initializable {

    @FXML private Button inspectionBtn;
    @FXML private Button guideLineBtn;
    @FXML private TableView<MyReport> tableView;// = new TableView<>();
    @FXML private TableColumn<MyReport, Integer> reportNum;
    @FXML private TableColumn<MyReport, String> domain;// = new TableColumn<>();
    @FXML private TableColumn<MyReport, LocalDate> date;// = new TableColumn<>();
    @FXML private TableColumn<MyReport, String> reportPath;// = new TableColumn<>();
    ObservableList<MyReport> data = FXCollections.observableArrayList();

    Stage primaryStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inspectionBtn.setOnAction(e -> openInspection());
        guideLineBtn.setOnAction(e -> openGuideLine());

        reportNum.setCellValueFactory(cellData -> cellData.getValue().getReportNum().asObject());
        domain.setCellValueFactory(cellData -> cellData.getValue().getDomain());
        date.setCellValueFactory(cellData -> cellData.getValue().getDate());
        reportPath.setCellValueFactory(cellData -> cellData.getValue().getReportPath());
        setupPerRowCursor(tableView);

        tableView.setItems(data);
        open();
        tableView.setOnMouseClicked(event -> mouseClick(event));

    }

    private void setupPerRowCursor(TableView<MyReport> table) {
        table.setRowFactory(this::createTableRow);
    }

    private TableRow<MyReport> createTableRow(TableView<MyReport> table) {
        TableRow<MyReport> row =  new TableRow<MyReport>();
        row.indexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            rowChanged(row, newValue.intValue());
        });
        return row;
    }

    private void rowChanged(TableRow<MyReport> myReport, int idx) {
        data = myReport.getTableView().getItems();
        if (idx >= 0 && idx < data.size()) {
            MyReport myReport1 = data.get(idx);
            if(myReport1.getReportPath().toString().contains("d"))
                myReport.setCursor(Cursor.HAND);
            else {
                myReport.setCursor(Cursor.DEFAULT);
            }
        }
    }

    public void open() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String url, time, report;
            Integer reportNumber;
            byte[] receivePacket = new byte[2];

            protocol = new Protocol(MSG.PT_TYPE_REQUEST);
            protocol.setRequest(MSG.PT_REQ_MY_INSPECTION_RECORD);

            dos.write(protocol.getPacket(), 0, protocol.getSize());
            dos.flush();

            dis.read(receivePacket);

            if (receivePacket[0] == MSG.PT_TYPE_RESPOND && receivePacket[1] == MSG.PT_RES_MY_REPORT_RECORD_SEND) {
                receivePacket = new byte[4];

                dis.read(receivePacket);
                int length = Integer.parseInt(changeByteToString(receivePacket));

                for (int i = 0; i < length; i++) {
                    dis.read(receivePacket);
                    reportNumber = Integer.parseInt(changeByteToString(receivePacket));

                    dis.read(receivePacket);
                    url = changeByteToString(receivePacket);

                    dis.read(receivePacket);
                    time = changeByteToString(receivePacket);
                    MyReport myReport= new MyReport(reportNumber, url, (LocalDate.parse(time, DateTimeFormatter.ISO_DATE)), "Download");
                    data.add(myReport);
                }
                for(int i = 0; i < data.size(); i++){
                    System.out.println(data.get(i).getReportNum());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeStage() {
        Stage stage11 = (Stage) inspectionBtn.getScene().getWindow();
        Platform.runLater(() -> {
            stage11.close();
        });
    }

    public void mouseClick(MouseEvent event){
        if(event.getClickCount() == 2){
            try {
                String filePath = "";
                DirectoryChooser directoryChooser = new DirectoryChooser();

                //현재 화면에 띄울수 없으므로 위에서 선언한 stage를 사용한다.
                File dir = directoryChooser.showDialog(primaryStage);

                //아무것도 선택하지 않고 취소를 하면  else를 실행한다.
                if (dir != null || !dir.getPath().equals(" ") || !dir.getPath().equals("") || dir.getPath() != null) {
                    filePath = dir.getPath();
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());

                    byte[] receivePacket = new byte[2];

                    protocol = new Protocol(MSG.PT_TYPE_REQUEST);
                    protocol.setRequest(MSG.PT_REQ_MY_REPORT_DOWNLOAD);

                    protocol.setStrToByte(String.valueOf(tableView.getSelectionModel().getSelectedItem().getReportNum().get()));


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
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                openMyReport();
            }
        }

    }
}
