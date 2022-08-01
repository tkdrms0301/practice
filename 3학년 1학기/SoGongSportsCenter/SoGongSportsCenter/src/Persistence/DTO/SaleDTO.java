package Persistence.DTO;

import java.util.Date;

public class SaleDTO {
   private int index;
    private String centerName;
    private String lessonName;
    private Date time;
    private int sales;

    public SaleDTO() { }

    public SaleDTO(String centerName, String lessonName, Date time, int sales) {
        this.centerName = centerName;
        this.lessonName = lessonName;
        this.time = time;
        this.sales = sales;
    }

    public String getCenterName() { return centerName; }
    public String getLessonName() { return lessonName; }
    public Date getTime() { return time; }
    public int getSales() { return sales; }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }
    public void  setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public void setSales(int sales) {
        this.sales = sales;
    }

    public void printSales() {
        System.out.println("|" + centerName + "|" + lessonName + "|" + "|" + time + "|" + sales);
    }
}
