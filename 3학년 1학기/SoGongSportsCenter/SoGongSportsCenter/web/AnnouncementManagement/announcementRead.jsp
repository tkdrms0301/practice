<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Persistence.DTO.AnnouncementDTO" %>
<%@ page import="Persistence.DTO.AttachedFileDTO" %>
<%@ page import="Service.AnnouncementService" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="org.apache.commons.io.FileUtils" %>
<%@ page import="java.io.File" %>
<html>
<head>
    <head>
        <title>소공 체육 센터</title>
        <meta name="viewport" content="width=device-width", initial-scale="1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/announcement.css?ver=1">
    </head>
</head>
<body>
    <%
        int announcementId = -1;

        if(session.getAttribute("announcementId") != null){
            announcementId = (int)session.getAttribute("announcementId");
        }

        if(request.getParameter("announcementId") != null){
            announcementId = Integer.parseInt(request.getParameter("announcementId"));
        }

        if(announcementId < 1){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('유효하지 않은 글입니다.')");
            script.println("location.href = 'announcementList.jsp'");
            script.println("</script>");
        }

        HashMap<String, Object> map = new AnnouncementService().readAnnouncement(announcementId);
        AnnouncementDTO announcementDTO = (AnnouncementDTO) map.get("AnnouncementDTO");
        List<AttachedFileDTO> fileDTOList = (List<AttachedFileDTO>) map.get("AttachedFileDTO");
    %>
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

    <div class="container">
        <div class="row">
            <table class="readTable" style="text-align: center; border: 1px solid #dddddd">
                <thead>
                    <tr>
                        <th colspan="6" style="background-color: #eeeeee; text-align: center; font-size: 15px">공지사항</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <td style="width: 20%; font-size: 20px; height: 15%">제목</td>
                        <td colspan="6" style="font-size: 20px"><%=announcementDTO.getAnnouncementTitle().replaceAll(" ", "&nbsp")
                                .replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>")%></td>
                    </tr>
                    <tr class="row2">
                        <td>작성자</td>
                        <td><%=announcementDTO.getAnnouncementWriterName()%></td>
                        <td>작성일자</td>
                        <td><%=announcementDTO.getWriteDate()%></td>
                        <td>조회수</td>
                        <td style="width: 5%"><%=announcementDTO.getHits()%></td>
                    </tr>
                    <tr class="row3">
                        <td>첨부파일</td>
                    <%
                        if(fileDTOList != null){
                            for(int i = 0; i < fileDTOList.size(); i++){
                                Blob blob = fileDTOList.get(i).getAttachedFile();
                                byte[] content = blob.getBytes(1, (int) blob.length());
                                File file = new File("C:\\Users\\84102\\attachedFile\\" + String.valueOf(i) + ".txt");
                                FileUtils.writeByteArrayToFile(file,content);
                    %>
                        <td colspan="5"><a href="<%file.getAbsolutePath();%>"><%=String.valueOf(i) + ".txt"%></a></td>
                    <%
                            }
                        }else{
                    %>
                        <td colspan="5">첨부파일이 없습니다.</td>
                        <%
                        }
                    %>
                    </tr>
                    <tr>
                        <td class="content" colspan="6" style="min-height: 200px; text-align: left;"><%=announcementDTO.getAnnouncementContent().replaceAll(" ", "&nbsp")
                                .replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll("\n", "<br>")%></td>
                    </tr>
                </tbody>
            </table>
            <a href="announcementList.jsp" class="writeBtn">목록</a>
        </div>
    </div>
</body>
</html>
