<%@ page import="Persistence.DAO.DBConfig" %>
<%@ page import="Persistence.DAO.LessonDAO" %>
<%@ page import="Persistence.DTO.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page import="Persistence.DAO.InstructorDAO" %>
<%@ page import="Persistence.DTO.InstructorDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    LessonDAO lessonDAO = new LessonDAO();
    List<Lesson> list = lessonDAO.selectAll();
    String lessonName = request.getParameter("search_lesson_name");

//    if( lessonName != null)
//    {
//        Lesson lesson = lessonDAO.selectLessonWithName(lessonName);
//        list.add(lesson);
//    }
//    else
//    {
//        list = lessonDAO.selectAll();
//    }


    InstructorDAO instructorDAO = new InstructorDAO(new DBConfig().getConnection());

%>

<html>
    <head>
        <title>수강 신청</title>
    </head>

    <style>
        .registration_container
        {
            margin: 0 auto;
            text-align: center;
        }
        h1
        {
            text-align: center;
        }

        table
        {
            width: 100%;
            border: 3px solid black;
            border-collapse: collapse;
        }

        th, td
        {
            border: 1px solid black;
        }
    </style>

    <body>
    <h1>수강 신청</h1>
        <div class = "registration_container">

<%--            <form action="registrationPage.jsp" method = "get">--%>
<%--                <span><label for="search_lesson">강습 검색</label><input type="search" id = "search_lesson" name = "search_lesson_name">--%>
<%--                    <input type="submit" onclick="" value="조회">--%>
<%--                    <input type="button" onclick= history.back() value="취소">--%>
<%--                </span>--%>
<%--            </form>--%>

                <div style="width:100%; height:200px; overflow:auto">
                    <table>
                        <tr>
                            <th>강습 아이디</th>
                            <th>강습 이름</th>
                            <th>강습 장소</th>
                            <th>강사명</th>
                            <th>가격</th>
                            <th>신청</th>
                        </tr>
                        <%for(int i = 0; i < list.size() ; i++)
                        {
                            Lesson lesson  = list.get(i);
                            int instructor_id = lesson.getInstructorId();
                            InstructorDTO instructorDTO = instructorDAO.selectInstructorById(instructor_id);
                        %>
                            <tr>
                                <th name = "lesson_id"><%=lesson.getId()%></th>
                                <th name = "lesson_name"><%=lesson.getLessonName()%></th>
                                <th name = "classroom"><%=lesson.getClassroom()%></th>
                                <th name = "user_name"><%=instructorDTO.getUserName()%></th>
                                <th name = "price"><%=lesson.getPrice()%></th>
<%--                                <jsp:forward page="registrationLesson.jsp">--%>
<%--                                    <jsp:param value="<%=lesson.getId()%>" name="lesson_id"/>--%>
<%--                                </jsp:forward>--%>
                                <th><a href ="registrationLesson.jsp?lesson_id=<%=lesson.getId()%>&price=<%=lesson.getPrice()%>">신청</a></th>
                            </tr>
                        <% } %>
                    </table>
                </div>

        </div>
    </body>
</html>
