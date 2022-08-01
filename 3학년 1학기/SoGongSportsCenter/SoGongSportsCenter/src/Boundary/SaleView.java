package Boundary;

import Persistence.DTO.SaleDTO;

import java.util.List;

public class SaleView {
    public void printPeriodSale(List<SaleDTO> saleDTOList) {
        System.out.println(String.format("|%-4s|%-4s|%-5s|%-4s|","센터이름","강좌이름","매출일","매출액"));
        for (SaleDTO saleDTO : saleDTOList) {
            saleDTO.printSales();
        }
    }

    public void printLessonSale(List<SaleDTO> saleDTOList) {
        System.out.println(String.format("|%-4s|%-4s|%-5s|%-4s|","센터이름","강좌이름","매출일","매출액"));

        for (SaleDTO saleDTO : saleDTOList) {
            saleDTO.printSales();
        }
    }
}
