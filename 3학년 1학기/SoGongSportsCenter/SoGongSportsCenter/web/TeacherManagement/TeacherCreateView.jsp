<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Persistence.DTO.TeacherDTO" %>
<%@ page import="Persistence.DAO.TeacherDAO" %>
<%@ page import="Service.UserEnroll" %>
<%@ page import="Persistence.DTO.UserDTO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="Teacher" class="Persistence.DTO.TeacherDTO" scope="page"></jsp:useBean>
<jsp:setProperty name="Teacher" property="teacherId"></jsp:setProperty>
<jsp:setProperty name="Teacher" property="teacherName"></jsp:setProperty>
<jsp:setProperty name="Teacher" property="teacherPassWord"></jsp:setProperty>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Teacher.css" type="text/css">
</head>
<body>
<%
    UserEnroll userEnroll = new UserEnroll();

    String result = userEnroll.userEnroll(new UserDTO(Integer.parseInt(Teacher.getTeacherId()), Teacher.getTeacherPassWord(), Teacher.getTeacherName(), "instructor"));

    PrintWriter script = response.getWriter();
    script.println("<script>");
    script.println("location.href = 'TeacherRead.jsp'");
    script.println("</script>");

%>
</body>
</html>
