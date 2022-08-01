<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>소공 체육 센터</title>
        <meta name="viewport" content="width=device-width", initial-scale="1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    </head>
    <body>
        <div class="header">
            <div class="logo"><h1>소공 체육 센터</h1></div>
        </div>

        <div class="nav-bar">
            <ul>
                <li><a class="active" href="main.jsp">Home</a></li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">회원 관리</a>
                    <div class="dropdown-content">
                        <a href="userManage/userManageEnrollView.jsp">회원 등록</a>
                        <a href="userManage/userManageReadView.jsp">회원 조회</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">강사 관리</a>
                    <div class="dropdown-content">
                        <a href="TeacherManagement/TeacherCreate.jsp">강사 등록</a>
                        <a href="TeacherManagement/TeacherRead.jsp">강사 조회</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">강습 관리</a>
                    <div class="dropdown-content">
                        <a href="LessonManagement/enrollLessonPage.jsp">강습 등록</a>
                        <a href="LessonManagement/registrationPage.jsp">수강 신청</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">공지사항</a>
                    <div class="dropdown-content">
                        <a href="AnnouncementManagement/announcementCreate.jsp">공지 등록</a>
                        <a href="AnnouncementManagement/announcementList.jsp">공지 조회</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">사물함 관리</a>
                    <div class="dropdown-content">
                        <a href="LockerManagement/Locker.jsp">사물함 생성</a>
                        <a href="LockerManagement/Locker.jsp">사물함 신청</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">매출 조회</a>
                    <div class="dropdown-content">
                        <a href="SaleManagement/salesByPeriod.jsp">기간별 매출 조회</a>
                        <a href="SaleManagement/salesByLesson.jsp">강습별 매출 조회</a>
                    </div>
                </li>
            </ul>
        </div>
    </body>
</html>
