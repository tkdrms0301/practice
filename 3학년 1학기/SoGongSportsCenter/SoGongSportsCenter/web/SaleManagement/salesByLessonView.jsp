<%--
  Created by IntelliJ IDEA.
  User: yujin
  Date: 2022-05-29
  Time: 오후 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Service.SaleManagementService" %>
<%@ page import="Persistence.DTO.SaleDTO" %>
<%@ page import="java.util.List" %>
<%
    String lesson = request.getParameter("lesson");
    SaleManagementService saleManagementService = new SaleManagementService();
    List<SaleDTO> saleDTOList = saleManagementService.findSalesByLesson(lesson);
    System.out.println(saleDTOList.size());
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <style>
        .content {
            width: 1200px;
            margin: 0 auto;
            padding-top: 20px;
        }

        .content .saleTable {
            width: 100%;
            height: 20px;

            background-color: #e8e8e8;
        }

        .content .saleIndex {
            width: 100%;
            height: 30px;
            margin: 0 auto;
            display: flex;
            flex-wrap: nowrap;
            margin: 0 auto;
            text-align: center;
        }

        .content .saleIndex.col {
            background-color: #a7a7a7;
        }

        .content .saleIndex .item {
            min-height: 40px;
            flex-basis: 200px;
            text-align: center;
        }


        .content .saleIndex .item:nth-child(1) { flex-grow: 0; }
        .content .saleIndex .item:nth-child(2) { flex-grow: 1; }
        .content .saleIndex .item:nth-child(3) { flex-grow: 1; }
        .content .saleIndex .item:nth-child(4) { flex-grow: 2; }
        .content .saleIndex .item:nth-child(5) { flex-grow: 4; }
    </style>
</head>
<body>
<div class="header">
    <div class="logo"><h1>소공 체육 센터</h1></div>
</div>
<div class="nav-bar">
    <ul>
        <li><a class="active" href="/">Home</a></li>
        <li class="dropdown">
            <a href="/userManage/" class="dropbtn">회원 관리</a>
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


<div class="content">
    <%
        if (saleDTOList.size() != 0) {
    %>
    <table class="saleTable">
        <thead>
        <tr class="saleIndex col">
            <th class="item">번호</th>
            <th class="item">센터명</th>
            <th class="item">강좌명</th>
            <th class="item">매출액</th>
            <th class="item">날짜</th>
        </tr>
        </thead>
        <tbody>
        <% for(int i = 0; i < saleDTOList.size(); i++) {%>
        <tr class="saleIndex">
            <td class="item" ><%=i%></td>
            <td class="item"><%=saleDTOList.get(i).getCenterName()%></td>
            <td class="item"><%=saleDTOList.get(i).getLessonName()%></td>
            <td class="item"><%=saleDTOList.get(i).getSales()%></td>
            <td class="item"><%=saleDTOList.get(i).getTime()%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
    <%} else {%>
    <table class="saleTable">
        <thead>
        <tr class="saleIndex col">
            <th class="item">번호</th>
            <th class="item">센터명</th>
            <th class="item">강좌명</th>
            <th class="item">매출액</th>
            <th class="item">날짜</th>
        </tr>
        </thead>
    </table>
    <%}%>
</div>
</body>
</html>
