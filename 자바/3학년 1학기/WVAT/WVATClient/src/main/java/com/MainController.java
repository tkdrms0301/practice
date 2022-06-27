package com;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends WindowController implements Initializable{
    @FXML private Button myReportBtn;
    @FXML private Button inspectionBtn;
    @FXML private Button guideLineBtn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        myReportBtn.setOnAction(e -> openMyReport());
        inspectionBtn.setOnAction(e -> openInspection());
        guideLineBtn.setOnAction(e -> openGuideLine());
    }

    public void closeStage() {
        Stage stage11 = (Stage) myReportBtn.getScene().getWindow();
        Platform.runLater(() -> {
            stage11.close();
        });
    }

}
