<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>소공 체육 센터</title>
</head>
<body>
<%@ page import="static Service.Message.*" %>
<%String result = (String) request.getAttribute("resultMessage");%>
<%System.out.println(result);%>
<%if(result.equals(ENROLL_SUC)){%>
<script>alert("사용자 등록 성공!")</script>
<script>document.location.href="/userManage/read";</script>
<%}else if(result.equals(ENROLL_INVALID_ACCOUNT_VALUE)){%>
<script>alert("유효하지 않은 아이디나 비밀번호 입니다.")</script>
<%}else if(result.equals(ENROLL_EXIST_ID)){%>
<script>alert("중복된 아이디입니다.")</script>
<%}else if(result.equals(ENROLL_INVALID_TYPE_VALUE)){%>
<script>alert("유효하지 않은 타입입니다.")</script>
<%}else{%>
<script>alert("잘못된 요청입니다.")</script>
<%}%>
<script>document.location.href="/userManage/enroll";</script>
</body>
</html>
