<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@page import="Service.AnnouncementService" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Persistence.DTO.AnnouncementDTO" %>

<% request.setCharacterEncoding("UTF-8"); %>
<html>
    <head>
        <head>
            <title>소공 체육 센터</title>
            <meta name="viewport" content="width=device-width", initial-scale="1">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/css/announcement.css">
        </head>
    </head>
    <body>
        <%
            int announcementId = -1;

            if(session.getAttribute("announcementId") != null){
                announcementId = (int)session.getAttribute("announcementId");
            }

            int pageNum = 1;

            if(request.getParameter("pageNum") != null){
                pageNum = Integer.parseInt(request.getParameter("pageNum"));
            }
        %>
        <div class="header">
            <div class="logo"><h1>소공 체육 센터</h1></div>
        </div>

        <div class="nav-bar">
            <ul>
                <li><a class="active" href="${pageContext.request.contextPath}/">Home</a></li>
                <li class="dropdown">
                    <a href="${pageContext.request.contextPath}/" class="dropbtn">회원 관리</a>
                    <div class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/userManage/userManageEnrollView.jsp">회원 등록</a>
                        <a href="${pageContext.request.contextPath}/userManage/userManageReadView.jsp">회원 조회</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">강사 관리</a>
                    <div class="dropdown-content">
                        <a href="#">강사 등록</a>
                        <a href="#">강사 조회</a>
                    </div>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">강습 관리</a>
                    <div class="dropdown-content">
                        <a href="/LessonManagement/enrollLessonPage.jsp">강습 등록</a>
                        <a href="/LessonManagement/registrationPage.jsp">수강 신청</a>
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
                        <a href="#">기간별 매출 조회</a>
                        <a href="#">강습별 매출 조회</a>
                    </div>
                </li>
            </ul>
        </div>

        <div class="container">
            <div class="row">
                <table class="listTable" style="text-align: center; border: 1px solid #dddddd">
                    <thead>
                        <tr>
                            <th class="num">번호</th>
                            <th class="title">제목</th>
                            <th class="user">작성자</th>
                            <th class="date">작성일</th>
                            <th class="hits">조회수</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            AnnouncementService service = new AnnouncementService();
                            ArrayList<AnnouncementDTO> list = service.getList(pageNum);

                            if(list != null){
                                for(int i = 0; i < list.size(); i++){
                        %>
                            <tr>
                                <td class="num"><%=list.get(i).getAnnouncementId()%></td>
                                <td class="title"><a href="${pageContext.request.contextPath}/AnnouncementManagement/announcementRead.jsp?announcementId=<%=list.get(i).getAnnouncementId()%>"><%=list.get(i).getAnnouncementTitle()%></a></td>
                                <td class="user"><%=list.get(i).getAnnouncementWriterName()%></td>
                                <td class="date"><%=list.get(i).getWriteDate()%></td>
                                <td class="hits"><%=list.get(i).getHits()%></td>
                            </tr>
                        <%
                                }
                            }else{
                        %>
                                <tr>
                                    <td>게시물이 존재하지 않습니다.</td>
                                </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
                <%
                    if(pageNum != 1){
                %>
                    <a href="announcementList.jsp?pageNum=<%=pageNum - 1%>" class="arrowBtn">이전</a>
                <%
                    }
                    if(service.isNextPage(pageNum + 1)){
                %>
                    <a href="announcementList.jsp?pageNum=<%=pageNum + 1%>" class="arrowBtn">다음</a>
                <%
                    }
                %>
                <a href="announcementCreate.jsp" class="writeBtn">등록</a>
            </div>
        </div>
    </body>
</html>
