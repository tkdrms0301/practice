package service;

import persistence.dao.AdminDAO;
import persistence.dto.AdminDTO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(AdminDAO boardDAO) {
        this.adminDAO = boardDAO;
    }
    public List<AdminDTO> findAdminAll(){
        List<AdminDTO> all = adminDAO.findAdminAll();
        return all;
    }

    public List<AdminDTO> findAll(){
        List<AdminDTO> all = adminDAO.findAll();
        return all;
    }

    public void insertAdmin(){
        Scanner s = new Scanner(System.in);
        System.out.print("input id : ");
        try{
            adminDAO.insertAdmin(s.nextInt());
        }catch (InputMismatchException e){
            e.printStackTrace();
        }
    }
}