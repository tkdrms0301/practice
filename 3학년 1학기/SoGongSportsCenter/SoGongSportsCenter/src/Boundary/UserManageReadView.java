package Boundary;

import Service.UserRead;
import Persistence.DTO.UserDTO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class UserManageReadView extends HttpServlet {
    private UserRead userRead;

    public void init(){
        userRead = new UserRead();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        action(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        action(request, response);
    }

    public void action(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = null;
        if(request.getParameter("method") == null){
            dispatcher = request.getRequestDispatcher("/userManage/userManageReadView.jsp");
            dispatcher.forward(request, response);
        }else{
            String method = request.getParameter("method");
            System.out.println(method);
            List<UserDTO> userDTOList = new ArrayList<>();
            if(method.equals("all")){
                userDTOList = userRead.userDataResultAll();
            }else if(method.equals("name")){
                userDTOList = userRead.userDataResultByName(request.getParameter("data"));
            }else if(method.equals("type")) {
                if (request.getParameter("data") != null) {
                    String type = request.getParameter("data");
                    System.out.println(type);
                    if (type.equals("admin"))
                        userDTOList = userRead.userDataResultByType(request.getParameter("data"));
                    else if(type.equals("instructor"))
                            userDTOList = userRead.userDataResultByType(request.getParameter("data"));
                    else if(type.equals("member"))
                            userDTOList = userRead.userDataResultByType(request.getParameter("data"));
                }
            }
            String resultList = "";
            for(UserDTO userDTO : userDTOList){
                resultList += userDTO.getUserId() + " ";
                resultList += userDTO.getUserPassword() + " ";
                resultList += userDTO.getUserName() + " ";
                resultList += userDTO.getUserType() + " ";
            }
            request.setAttribute("userList", resultList);
            request.setAttribute("listSize", userDTOList.size());
            ServletContext servletContext = getServletContext();
            dispatcher = servletContext.getRequestDispatcher("/userManage/userManageReadView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
