<%@ page import="java.io.PrintWriter" %>
<%@ page import="Service.AnnouncementService" %>
<%@ page import="Persistence.DTO.AnnouncementDTO" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="Persistence.DTO.AttachedFileDTO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.commons.fileupload.DiskFileUpload" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.io.File" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="announcement" class="Persistence.DTO.AnnouncementDTO" scope="page"></jsp:useBean>
<jsp:useBean id="attachedFile" class="Persistence.DTO.AttachedFileDTO" scope="page"></jsp:useBean>
<jsp:setProperty name="announcement" property="announcementTitle"></jsp:setProperty>
<jsp:setProperty name="announcement" property="announcementContent"></jsp:setProperty>
<jsp:setProperty name="attachedFile" property="attachedFile"></jsp:setProperty>
<html>
<head>
    <title>소공 체육 센터</title>
</head>
<body>
    <%
        int announcementId = -1;

        if(session.getAttribute("announcementId") != null){
            announcementId = (int)session.getAttribute("announcementId");
        }

        int isAttachedFile = 0;
        final int FILESIZE = 1024 * 1024 * 10;
        String path = "C:\\Users\\84102\\attachedFile";
        DiskFileUpload dfu = new DiskFileUpload();
        dfu.setSizeMax(FILESIZE);
        dfu.setSizeThreshold(4096);
        dfu.setRepositoryPath(path);
        List items = dfu.parseRequest((HttpServletRequest) request);
        Iterator params =  items.iterator();
        Blob blob = null;
        FileInputStream fis = null;

        String announcementTitle = "", announcementContent = "";

        while(params.hasNext()){
            FileItem item = (FileItem) params.next();

            if(item.isFormField()){
                String name = item.getFieldName();

                if(name.equals("announcementTitle")){
                    announcementTitle = item.getString("UTF-8");
                }else if(name.equals("announcementContent")){
                    announcementContent = item.getString("UTF-8");
                }
            }else{
                String fileFieldName = item.getFieldName();
                String fileName = item.getName();
                String contentType = item.getContentType();
                fileName.substring(fileName.lastIndexOf("\\")+1);
                File file = new File(path + "/" + fileName);
                item.write(file);

                try {
                    byte[] bytes = new byte[(int) file.length()];
                    fis = new FileInputStream(file);
                    fis.read(bytes);

                    blob = new javax.sql.rowset.serial.SerialBlob(bytes);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        if (fis != null) {
                            fis.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                isAttachedFile = 1;
            }
        }

        if(announcementTitle == null) {
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('제목을 입력하세요.')");
            script.println("history.back()");
            script.println("</script>");
        }else {
            AnnouncementService service = new AnnouncementService();

            AnnouncementDTO announcementDTO = new AnnouncementDTO(announcementTitle, announcementContent, "관리자",
                    java.sql.Date.valueOf(LocalDate.now()), isAttachedFile, 0);

            AttachedFileDTO attachedFileDTO = new AttachedFileDTO(-1, -1, blob);

            HashMap<String, Object> resultMap = service.createAnnouncement(announcementDTO, attachedFileDTO);

            int insertId = (int) resultMap.get("insertID");
            boolean result = (boolean) resultMap.get("result");

            if(result){
                session.setAttribute("announcementId", insertId);
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("location.href = 'announcementRead.jsp'");
                script.println("</script>");
            }else{
                PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('글쓰기에 실패했습니다.')");
                script.println("history.back()");
                script.println("</script>");
            }
        }
    %>
</body>
</html>
