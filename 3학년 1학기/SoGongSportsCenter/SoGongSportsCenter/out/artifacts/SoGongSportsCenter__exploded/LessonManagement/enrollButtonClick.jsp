<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Persistence.DAO.InstructorDAO" %>
<%@ page import="Persistence.DAO.DBConfig" %>
<%@ page import="Persistence.DAO.LessonDAO" %>
<%@ page import="Persistence.DTO.UserDTO" %>
<%@ page import="Persistence.DTO.InstructorDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="Service.LessonManagementService" %>
<%@ page import="Persistence.DAO.SaleDAO" %>

<html>
<head>
    <title>enrollButtonClick</title>
</head>
<body>
    <%

        request.setCharacterEncoding("UTF-8");

        String lesson_name = request.getParameter("lesson_name");
        String classroom = request.getParameter("classroom");
        int price = Integer.parseInt(request.getParameter("price"));
        int instructor = Integer.parseInt(request.getParameter("instructor"));


        DBConfig dbConfig  = new DBConfig();
        SaleDAO saleDAO = new SaleDAO();
        LessonDAO lessonDAO = new LessonDAO();
        InstructorDAO instructorDAO = new InstructorDAO(dbConfig.getConnection());

        LessonManagementService lessonManagementService = new LessonManagementService(saleDAO,lessonDAO);
        List<InstructorDTO> list = instructorDAO.selectInstructor();

        for(InstructorDTO dto : list)
        {
            if(dto.getInstructorId() == instructor)
            {
                HashMap<String ,Object> map = new HashMap<>();
                map.put("lessonName",lesson_name);
                map.put("classroom",classroom);
                map.put("price",price);
                map.put("instructorId",dto.getInstructorId());
                lessonManagementService.createLesson(map);
                break;
            }

        }

        response.sendRedirect("../main.jsp");
    %>
    </body>
</html>
