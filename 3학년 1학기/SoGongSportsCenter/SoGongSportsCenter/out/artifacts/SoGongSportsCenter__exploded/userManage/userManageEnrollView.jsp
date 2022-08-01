<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>소공 체육 센터</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userEnroll.css
" type="text/css">
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
                <a href="#">기간별 매출 조회</a>
                <a href="#">강습별 매출 조회</a>
            </div>
        </li>
    </ul>
</div>
<div class="contents_container">
    <div class="content">
        <div class="form_content">
            <h4>사용자 등록</h4>
        </div>
        <div class="form_content">
            <form class="login_content" method="post" action="${pageContext.request.contextPath}/userManage/enroll">
                <div class="content">
                    <p>
                        <label for="idTag">사용자 아이디</label>
                        <input id="idTag" type="text" name="id" required placeholder="내용을 입력해주세요">
                    </p>
                </div>
                <div class="content">
                    <p>
                        <label for="passwordTag">사용자 비밀번호</label>
                        <input id="passwordTag" type="password" name="password" required placeholder="내용을 입력해주세요">
                    </p>
                </div>
                <div class="content">
                    <p>
                        <label for="nameTag">사용자 이름</label>
                        <input id="nameTag" type="text" name="name" required placeholder="내용을 입력해주세요">
                    </p>
                </div>
                <div class="content">
                    <p>
                        <label for="nameTag">사용자 타입</label>
                        <input id="typeTag" type="text" name="type" placeholder="내용을 입력해주세요">
                    </p>
                </div>
                <div class="content">
                    <p>
                        <input type="submit" value="사용자 등록">
                    </p>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>