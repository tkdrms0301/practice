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

    InstructorDAO instructorDAO = new InstructorDAO();

%>

<html>
    <head>
        <title>소공 체육 센터</title>
        <meta name="viewport" content="width=device-width", initial-scale="1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Lesson.css">
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
    <div class="header">
        <div class="logo"><h1>소공 체육 센터</h1></div>
    </div>

    <div class="nav-bar">
        <ul>
            <li><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li class="dropdown">
                <a href="${pageContext.request.contextPath}/" class="dropbtn">회원 관리</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/userManage/userManageEnrollView.jsp">회원 등록</a>
                    <a href="${pageContext.request.contextPath}/userManage/userManageReadView.jsp">회원 조회</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="${pageContext.request.contextPath}/" class="dropbtn">강사 관리</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/TeacherManagement/TeacherCreate.jsp">강사 등록</a>
                    <a href="${pageContext.request.contextPath}/TeacherManagement/TeacherRead.jsp">강사 조회</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="${pageContext.request.contextPath}/" class="dropbtn">강습 관리</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/LessonManagement/enrollLessonPage.jsp">강습 등록</a>
                    <a href="${pageContext.request.contextPath}/LessonManagement/registrationPage.jsp">수강 신청</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="${pageContext.request.contextPath}/" class="dropbtn">공지사항</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/AnnouncementManagement/announcementCreate.jsp">공지 등록</a>
                    <a href="${pageContext.request.contextPath}/AnnouncementManagement/announcementList.jsp">공지 조회</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="${pageContext.request.contextPath}/" class="dropbtn">사물함 관리</a>
                <div class="dropdown-content">
                    <a href="#">사물함 생성</a>
                    <a href="#">사물함 신청</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="${pageContext.request.contextPath}/" class="dropbtn">매출 조회</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/SaleManagement/salesByPeriod.jsp">기간별 매출 조회</a>
                    <a href="${pageContext.request.contextPath}/SaleManagement/salesByLesson.jsp">강습별 매출 조회</a>
                </div>
            </li>
        </ul>
    </div>

    <h1>수강 신청</h1>
        <div class = "registration_container">

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

                                <th><a href ="registrationLesson.jsp?lesson_id=<%=lesson.getId()%>&price=<%=lesson.getPrice()%>">신청</a></th>
                            </tr>
                        <% } %>
                    </table>
                </div>

        </div>
    </body>
</html>
