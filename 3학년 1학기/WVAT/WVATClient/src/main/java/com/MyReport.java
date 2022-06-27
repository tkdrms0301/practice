package com;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class MyReport {
    private IntegerProperty reportNum;
    private StringProperty domain;
    private ObjectProperty<LocalDate> date;
    private StringProperty reportPath;


    public MyReport(Integer reportNum, String domain, LocalDate date, String reportPath) {
        super();
        this.reportNum = new SimpleIntegerProperty(reportNum);
        this.domain = new SimpleStringProperty(domain);
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.reportPath = new SimpleStringProperty(reportPath);

    }
    public IntegerProperty getReportNum(){return reportNum;}

    public StringProperty getDomain(){
        return domain;
    }

    public ObjectProperty<LocalDate> getDate() {
        return date;
    }

    public StringProperty getReportPath() {
        return reportPath;
    }
    public final void setDate(LocalDate date) {
        this.date.set(date);
    }
}