
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Persistence.DAO.LessonDAO" %>
<%@ page import="Persistence.DAO.UserDAO" %>
<%@ page import="Persistence.DAO.DBConfig" %>
<%@ page import="Persistence.DTO.UserDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="Persistence.DTO.Lesson" %>
<%@ page import="Persistence.DAO.SaleDAO" %>
<%@ page import="Persistence.DTO.SaleDTO" %>
<%@ page import="java.util.Date" %>
<%@ page import="Service.LessonManagementService" %>

<html>
<head>
    <title>registrationLesson</title>
</head>
<body>
<%

    String lesson_id = request.getParameter("lesson_id");
    String lesson_name = request.getParameter("lesson_name");
    String classroom = request.getParameter("classroom");
 //   String user_name = request.getParameter("user_name");
    String price = request.getParameter("price");

    LessonDAO lessonDAO = new LessonDAO();
    UserDAO userDAO = new UserDAO();
    SaleDAO saleDAO = new SaleDAO();
    try{
        Integer.parseInt(lesson_id);
    }
    catch (Exception e)
    {
        System.out.println(lesson_id);
        e.printStackTrace();
    }
    Lesson lesson = lessonDAO.selectLessonWithId(Integer.parseInt(lesson_id));

    //List<UserDTO> userDTOs = userDAO.selectUserByName();
    List<UserDTO> userDTOs = userDAO.selectUser();
    UserDTO userDTO = userDTOs.get(3);

    LessonManagementService lessonManagementService = new LessonManagementService(saleDAO,lessonDAO);
    lessonManagementService.lectureRegistration(userDTO.getUserId(),lesson.getId());

    response.sendRedirect("../main.jsp");

%>
</body>
</html>
