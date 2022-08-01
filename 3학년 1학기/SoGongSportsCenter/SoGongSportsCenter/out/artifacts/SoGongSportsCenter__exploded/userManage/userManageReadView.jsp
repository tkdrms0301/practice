<%@ page import="Persistence.DTO.UserDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>소공 체육 센터</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userRead.css" type="text/css">
</head>
<body>
<div class="header">
    <div class="logo"><h1>소공 체육 센터</h1></div>
</div>
<div class="nav-bar">
    <ul>
        <li><a class="active" href="/">Home</a></li>
        <li class="dropdown">
            <a href="#" class="dropbtn">회원 관리</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/userManage/enroll">회원 등록</a>
                <a href="${pageContext.request.contextPath}/userManage/read">회원 조회</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="${pageContext.request.contextPath}/" class="dropbtn">강사 관리</a>
            <div class="dropdown-content">
                <a href="#">강사 등록</a>
                <a href="#">강사 조회</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="${pageContext.request.contextPath}/" class="dropbtn">강습 관리</a>
            <div class="dropdown-content">
                <a href="/LessonManagement/enrollLessonPage.jsp">강습 등록</a>
                <a href="/LessonManagement/registrationPage.jsp">수강 신청</a>
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
                <a href="#">기간별 매출 조회</a>
                <a href="#">강습별 매출 조회</a>
            </div>
        </li>
    </ul>
</div>
<div class="contents_container">
    <div class="select_content">
        <div class="content_head">
            <p>
                <h4>사용자 조회</h4>
            </p>
        </div>
        <div class="contents">
            <form method="get" action="${pageContext.request.contextPath}/userManage/read">
                <select id="method" name="method">
                    <option name="" value="">조회하기</option>
                    <option name="all" value="all">모두</option>
                    <option name="name" value="name">이름</option>
                    <option name="type" value="type">타입</option>
                </select>
                <input class="input_tag" type="text" name="data" value="" placeholder="이름이나 타입을 입력하세요">
                <input type="submit" value="검색">
            </form>
        </div>
    </div>
    <%@ page import="Persistence.DTO.UserDTO" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.util.ArrayList" %>
    <%
        if(request.getAttribute("userList") != null){
        String userList = (String) request.getAttribute("userList");
        int listSize = (Integer) request.getAttribute("listSize");

        System.out.println(userList);
        System.out.println(listSize);
        if(listSize != 0){
            String[] split = userList.split(" ");
            List<UserDTO> userDTOList = new ArrayList<>();
            for(int i = 0; i < split.length; i += 4){
                UserDTO userDTO = new UserDTO(Integer.parseInt(split[i]), split[i + 1], split[i + 2], split[i + 3]);
                userDTOList.add(userDTO);
            }
            %>
    <div class="select_content">
    <table>
        <thead>
        <tr>
            <th>사용자 아이디</th>
            <th>사용자 비밀번호</th>
            <th>사용자 이름</th>
            <th>사용자 타입</th>
        </tr>
        </thead>
        <tbody>
        <%for(int i = 0; i < userDTOList.size(); i++){%>
        <tr>
            <td><%=userDTOList.get(i).getUserId()%></td>
            <td><%=userDTOList.get(i).getUserPassword()%></td>
            <td><%=userDTOList.get(i).getUserName()%></td>
            <td><%=userDTOList.get(i).getUserType()%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
        <%}else {%>
    <table>
        <thead>
        <tr>
            <th>사용자 아이디</th>
            <th>사용자 비밀번호</th>
            <th>사용자 이름</th>
            <th>사용자 타입</th>
        </tr>
        </thead>
    </table>
    <%}%>
    <%}%>
    </div>
</div>
</body>
</html>
