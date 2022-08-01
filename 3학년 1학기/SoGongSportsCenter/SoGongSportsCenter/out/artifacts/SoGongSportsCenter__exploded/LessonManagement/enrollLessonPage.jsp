
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Persistence.DAO.InstructorDAO" %>
<%@ page import="Persistence.DTO.InstructorDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>

<%
    InstructorDAO instructorDAO = new InstructorDAO();
    List<InstructorDTO> instructorList = instructorDAO.selectInstructor();

%>

<html>
<head>
    <title>강습 등록</title>
</head>

<style>
    .lesson_enroll_container
    {
        margin: 0 auto;
        text-align: center;
    }

    h1
    {
        text-align: center;
    }

    form
    {
        display: inline-block;
    }

    input
    {
        margin : 2px auto;
    }

</style>
<body>
    <h1> 강습 등록 </h1>
    <div class = "lesson_enroll_container">

        <div class = "lesson_enroll_form">
            <form action="enrollButtonClick.jsp" method = "post" >
                <div><label for="lesson_name">강좌 이름</label><input type="text" id = "lesson_name" name = "lesson_name" placeholder="강좌 이름 입력" required></div>
                <div><label for="classroom">강의실</label><input type="text" id = "classroom" name = "classroom" placeholder="장소 입력" required></div>
                <div><label for="price">강습료</label><input type="number" id = "price" name = "price" placeholder="가격 입력" required></div>
                <div><label>강사 선택</label>
                    <select id="select_instructor" name="instructor">
                        <%  int i;
                            for( i = 0 ; i < instructorList.size();i++){ %>
                        <option value="<%=instructorList.get(i).getInstructorId()%>"><%=instructorList.get(i).getUserName()%></option>
                        <% } %>
                    </select>
                </div>

                <div><input type="submit" value="등록"> &nbsp <input type="button" onclick=history.back() value="취소"> </div>

            </form>
        </div>
    </div>

</body>
</html>
