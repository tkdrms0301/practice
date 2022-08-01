<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="Persistence.DTO.LockerDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="Service.LockerService" %>
<html>
    <head>
        <title>소공 체육 센터</title>
        <meta name="viewport" content="width=device-width", initial-scale="1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/locker.css">
        <style>
            table, th, td {
                border: 1px solid #bcbcbc;
            }

            table {
                width: 100%;
                height: 200px;
                margin: 0 auto;
            }
        </style>
    </head>
    <body>
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
                    <a href="${pageContext.request.contextPath}/LockerManagement/Locker.jsp">사물함 생성</a>
                    <a href="${pageContext.request.contextPath}/LockerManagement/Locker.jsp">사물함 신청</a>
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

        <div style="display: flex; flex-direction: column; justify-content: center; align-content: center">
            <div style="display: flex; justify-content: space-between; align-content: center; margin-top: 10px">
                <div class="locker-form" style="border: 1px solid black; padding: 20px 10px 0 10px">
                    <h2>사물함 등록</h2>

                    <form action="${pageContext.request.contextPath}/locker" method="post" autocomplete="on">
                        <div class="form-example">
                            <label>사물함 번호: </label>
                            <input type="text" name="lockerNumber" required>
                        </div>
                        <div>
                            <label>사물함 위치: </label>
                            <input type="text" name="lockerLocation">
                        </div>
                        <div>
                            <label>금액: </label>
                            <input type="text" name="amount">
                        </div>
                        <div>
                            <input type="submit" value="submit">
                        </div>
                    </form>
                </div>

                <div class="locker-form" style="border: 1px solid black; padding: 20px 10px 0 10px">
                    <h2>사물함 삭제</h2>

                    <form action="${pageContext.request.contextPath}/locker/delete" method="post" autocomplete="on">
                        <div class="form-example">
                            <label>사물함 번호: </label>
                            <input type="text" name="lockerNumber" required>
                        </div>
                        <div>
                            <input type="submit" value="submit">
                        </div>
                    </form>
                </div>

                <div style="border: 1px solid black; padding: 20px 10px 0 10px">
                    <h2>사물함 수정</h2>

                    <form name="profile" action="${pageContext.request.contextPath}/locker/update" method="post"
                          autocomplete="on">
                        <div>
                            <label>사물함 번호: </label>
                            <input type="text" name="lockerNumber">
                        </div>
                        <div>
                            <label>사물함 위치: </label>
                            <input type="text" name="lockerLocation">
                        </div>
                        <div>
                            <label>금액: </label>
                            <input type="text" name="amount">
                        </div>
                        <div>
                            <label>사용자 번호: </label>
                            <input type="text" name="userId">
                        </div>
                        <div>
                            <input type="submit" value="submit">
                        </div>
                    </form>
                </div>

                <div style="border: 1px solid black; padding: 20px 10px 0 10px">
                    <h2>사물함 배정</h2>

                    <form name="profile" action="${pageContext.request.contextPath}/locker/assign" method="post"
                          autocomplete="on">
                        <div class="form-example">
                            <label>사물함 번호: </label>
                            <input type="text" name="lockerNumber">
                        </div>
                        <div>
                            <label>사용자 번호: </label>
                            <input type="text" name="userId">
                        </div>
                        <div>
                            <input type="submit" value="submit">
                        </div>
                    </form>
                </div>
            </div>

            <div style="display: flex; justify-content: center; width: 80%; margin: auto; margin-top: 50px">
                <table class="table" height="200">
                    <caption style="font-size: 2em">사물함 현황</caption>
                    <thead>
                    <tr>
                        <th>위치</th>
                        <th>번호</th>
                        <th>금액</th>
                        <th>사용자</th>
                    </tr>
                    </thead>

                    <tbody>
                        <%
                            LockerService lockerService = new LockerService();
                            List<LockerDTO> dtos = lockerService.readAll();

                            if (dtos.size() != 0) {
                                for(int i = 0; i < dtos.size(); i++){
                        %>
                        <tr>
                            <td><%=dtos.get(i).getLockerNumber()%></td>
                            <td><%=dtos.get(i).getLockerLocation()%></td>
                            <td><%=dtos.get(i).getAmount()%></td>
                            <td><%=dtos.get(i).getUserId()%></td>
                        </tr>
                        <%
                                }
                            }
                        %>

                    </tbody>
                </table>
            </div>
        </div>

    </body>
</html>
