<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Persistence.DAO.InstructorDAO" %>
<%@ page import="Persistence.DTO.InstructorDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>

<%
    InstructorDAO instructorDAO = new InstructorDAO();
    List<InstructorDTO> instructorList = instructorDAO.selectInstructor();
%>

<html>
<head>
    <title>소공 체육 센터</title>
    <meta name="viewport" content="width=device-width", initial-scale="1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Lesson.css">
</head>

<style>
    .lesson_enroll_container
    {
        margin: 0 auto;
        text-align: center;
    }
    h1
    {
        text-align: center;
    }
    form
    {
        display: inline-block;
    }
    input
    {
        margin : 2px auto;
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

<h1> 강습 등록 </h1>
<div class = "lesson_enroll_container">

    <div class = "lesson_enroll_form">
        <form action="enrollButtonClick.jsp" method = "post" >
            <div><label for="lesson_name">강좌 이름</label><input type="text" id = "lesson_name" name = "lesson_name" placeholder="강좌 이름 입력" required></div>
            <div><label for="classroom">강의실</label><input type="text" id = "classroom" name = "classroom" placeholder="장소 입력" required></div>
            <div><label for="price">강습료</label><input type="number" id = "price" name = "price" placeholder="가격 입력" required></div>
            <div><label>강사 선택</label>
                <select id="select_instructor" name="instructor">
                    <%  int i;
                        for( i = 0 ; i < instructorList.size();i++){ %>
                    <option value="<%=instructorList.get(i).getInstructorId()%>"><%=instructorList.get(i).getUserName()%></option>
                    <% } %>
                </select>
            </div>

            <div><input type="submit" value="등록"> &nbsp <input type="button" onclick=history.back() value="취소"> </div>

        </form>
    </div>
</div>

</body>
</html>