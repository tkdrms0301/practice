<%@ page import="java.nio.file.Path" %>
<%@ page import="Persistence.DTO.SaleDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>소공 체육 센터</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">

</head>
<body>
<div class="header">
    <div class="logo"><h1>소공 체육 센터</h1></div>
</div>
<div>
    <div class="nav-bar">
        <ul>
            <li><a class="active" href="/">Home</a></li>
            <li class="dropdown">
                <a href="${pageContext.request.contextPath}/userManage/" class="dropbtn">회원 관리</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/userManage/enroll">회원 등록</a>
                    <a href="${pageContext.request.contextPath}/userManage/read">회원 조회</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="#" class="dropbtn">강사 관리</a>
                <div class="dropdown-content">
                    <a href="${pageContext.request.contextPath}/TeacherManagement/TeacherCreate.jsp">강사 등록</a>
                    <a href="${pageContext.request.contextPath}/TeacherManagement/TeacherRead.jsp">강사 조회</a>
                </div>
            </li>
            <li class="dropdown">
                <a href="#" class="dropbtn">강습 관리</a>
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

    <form method="get" action="salesByLessonView.jsp" >
        <input type="text" name="lesson" id="lesson" placeholder="강습">
        <input type="submit" value="조회">
    </form>
</div>
</body>
</html>
