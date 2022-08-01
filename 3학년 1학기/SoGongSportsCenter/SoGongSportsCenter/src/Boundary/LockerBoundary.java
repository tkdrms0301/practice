package Boundary;

import Persistence.DTO.LockerDTO;
import Service.LockerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LockerBoundary extends HttpServlet {
    private LockerService lockerService;

    public void init() {
        lockerService = new LockerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("read locker");

        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        List<LockerDTO> lockerDTOList = lockerService.readAll();

        for (LockerDTO a : lockerDTOList) {
            System.out.println(a);
        }

        req.setAttribute("lockerList", lockerDTOList);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/LockerManagement/Locker.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String conPath = req.getContextPath();
        conPath += "/locker";
        String com = uri.substring(conPath.length());
        String method = req.getParameter("method");

        System.out.println(uri);
        System.out.println(conPath);
        System.out.println(com);
        System.out.println(method);

        if (com.equals("")) {
            System.out.println("create locker");
            try {
                req.setCharacterEncoding("UTF-8");
                res.setCharacterEncoding("UTF-8");

                int lockerNumber = Integer.parseInt(req.getParameter("lockerNumber"));
                int lockerLocation = Integer.parseInt(req.getParameter("lockerLocation"));
                int amount = Integer.parseInt(req.getParameter("amount"));

                lockerService.createLocker(new LockerDTO(lockerNumber, lockerLocation, amount));
            } catch (Exception e) {
                System.out.println(e);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/LockerManagement/Locker.jsp");
            dispatcher.forward(req, res);
        } else if (com.equals("/delete")) {
            System.out.println("delete locker");
            try {
                req.setCharacterEncoding("UTF-8");
                res.setCharacterEncoding("UTF-8");

                int lockerNumber = Integer.parseInt(req.getParameter("lockerNumber"));

                lockerService.deleteLocker(lockerNumber);
            } catch (Exception e) {
                System.out.println(e);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/LockerManagement/Locker.jsp");
            dispatcher.forward(req, res);
        } else if (com.equals("/update")) {
            System.out.println("update locker");

            try {
                req.setCharacterEncoding("UTF-8");
                res.setCharacterEncoding("UTF-8");

                int lockerNumber = Integer.parseInt(req.getParameter("lockerNumber"));
                int lockerLocation = Integer.parseInt(req.getParameter("lockerLocation"));
                int amount = Integer.parseInt(req.getParameter("amount"));
                int userId = Integer.parseInt(req.getParameter("userId"));

                lockerService.updateLocker(new LockerDTO(lockerNumber, lockerLocation, amount, userId));
            } catch (Exception e) {
                System.out.println(e);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/LockerManagement/Locker.jsp");
            dispatcher.forward(req, res);
        } else if (com.equals("/assign")) {
            System.out.println("assign locker");

            try {
                req.setCharacterEncoding("UTF-8");
                res.setCharacterEncoding("UTF-8");

                int lockerNumber = Integer.parseInt(req.getParameter("lockerNumber"));
                int userId = Integer.parseInt(req.getParameter("userId"));

                System.out.println(lockerNumber);
                System.out.println(userId);
                lockerService.assignLocker(lockerNumber, userId);
            } catch (Exception e) {
                System.out.println(e);
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/LockerManagement/Locker.jsp");
            dispatcher.forward(req, res);
        }
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("delete locker");
        try {
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");

            int lockerNumber = Integer.parseInt(req.getParameter("lockerNumber"));

            lockerService.deleteLocker(lockerNumber);
        } catch (Exception e) {
            System.out.println(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/LockerManagement/Locker.jsp");
        dispatcher.forward(req, res);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("update locker");

        try {
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");

            int lockerNumber = Integer.parseInt(req.getParameter("lockerNumber"));
            int lockerLocation = Integer.parseInt(req.getParameter("lockerLocation"));
            int amount = Integer.parseInt(req.getParameter("amount"));
            int userId = Integer.parseInt(req.getParameter("userId"));

            lockerService.updateLocker(new LockerDTO(lockerNumber, lockerLocation, amount, userId));
        } catch (Exception e) {
            System.out.println(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/LockerManagement/Locker.jsp");
        dispatcher.forward(req, res);
    }

    public void action(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/LockerManagement/Locker.jsp");
        dispatcher.forward(request, response);
    }
}
