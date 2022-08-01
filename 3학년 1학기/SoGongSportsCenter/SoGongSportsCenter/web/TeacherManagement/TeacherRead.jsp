<%@ page import="Persistence.DTO.TeacherDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="Persistence.DTO.TeacherDTO" %>
<%@ page import="Service.UserRead" %>
<%@ page import="Persistence.DTO.UserDTO" %>

<html>
<head>
    <title>소공 체육 센터</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Teacher.css" type="text/css">
</head>
<body>
<div class="header">
    <div class="logo"><h1>소공 체육 센터</h1></div>
</div>
<div class="nav-bar">
    <ul>
        <li><a class="active" href="/">Home</a></li>
        <li class="dropdown">
            <a href="${pageContext.request.contextPath}/userManage/" class="dropbtn">회원 관리</a>
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
            <a href="#" class="dropbtn">공지사항</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/AnnouncementManagement/announcementCreate.jsp">공지 등록</a>
                <a href="${pageContext.request.contextPath}/AnnouncementManagement/announcementList.jsp">공지 조회</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="#" class="dropbtn">사물함 관리</a>
            <div class="dropdown-content">
                <a href="#">사물함 생성</a>
                <a href="#">사물함 신청</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="#" class="dropbtn">매출 조회</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/SaleManagement/salesByPeriod.jsp">기간별 매출 조회</a>
                <a href="${pageContext.request.contextPath}/SaleManagement/salesByLesson.jsp">강습별 매출 조회</a>
            </div>
        </li>
    </ul>
</div>
<div id = "wrap">
    <br><br>
    <b><font size="6" color="gray">강사조회</font></b>
    <br><br><br>
    <table>
        <tr>
            <td id="id">아이디</td>
            <td id="name">이름</td>
            <td id="password">비밀번호</td>
        </tr>
    <%
        UserRead userRead = new UserRead();
        List<UserDTO> instructorList = userRead.userDataResultByType("instructor");
        for(UserDTO teacher : instructorList){
    %>
        <tr>
            <td><%=teacher.getUserId()%></td>
            <td><%=teacher.getUserName()%></td>
            <td><%=teacher.getUserPassword()%></td>
        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
