package Persistence.DAO;

import Persistence.DTO.Lesson;
import Persistence.DTO.LessonRegistrationInfo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LessonDAO {

    private static final String insertLessonQuery = "INSERT INTO LESSON (name,classroom,price,instructor_id) VALUES (?,?,?,?)";
    private static final String lessonRegistrationQuery = "INSERT INTO LESSON_REGISTRATION_INFO (user_id,lesson_id,registration_date) VALUES (?,?,?)";
    private static final String  selectLessonWithUserIdAndLessonIdQuery = "SELECT * FROM LESSON_REGISTRATION_INFO WHERE user_id = ? AND lesson_id = ?";

    private static final String selectLessonWithLessonId = "SELECT * FROM LESSON WHERE id = ?";
    private static final String selectAll = "SELECT * FROM LESSON";
    private static final String selectLessonWithLessonName = "SELECT * FROM LESSON WHERE name = ?";
    private DataSource ds;

    public LessonDAO(){
        try
        {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/OOSE");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Lesson selectLessonWithId(int lessonId){

        Lesson lesson = new Lesson();

        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(selectLessonWithLessonId)) {

            psmt.setInt(1,lessonId);

            try (ResultSet rs = psmt.executeQuery())
            {
                rs.next();
                lesson.setId(rs.getInt("id"));
                lesson.setLessonName(rs.getString("name"));
                lesson.setClassroom(rs.getString("classroom"));
                lesson.setInstructorId(rs.getInt("instructor_id"));
                lesson.setPrice(rs.getInt("price"));

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return lesson;

    }


    public Lesson selectLessonWithName(String lessonName){

        Lesson lesson = new Lesson();

        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(selectLessonWithLessonName)) {

            psmt.setString(1,lessonName);
            System.out.println(lessonName);

            try (ResultSet rs = psmt.executeQuery())
            {
                rs.next();
                lesson.setId(rs.getInt("id"));
                lesson.setLessonName(rs.getString("name"));
                lesson.setClassroom(rs.getString("classroom"));
                lesson.setInstructorId(rs.getInt("instructor_id"));
                lesson.setPrice(rs.getInt("price"));

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return lesson;

    }
    public List<Lesson> selectAll()
    {
        List<Lesson> list = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(selectAll)) {


            try (ResultSet rs = psmt.executeQuery())
            {
                while(rs.next())
                {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getInt("id"));
                    lesson.setLessonName(rs.getString("name"));
                    lesson.setClassroom(rs.getString("classroom"));
                    lesson.setInstructorId(rs.getInt("instructor_id"));
                    lesson.setPrice(rs.getInt("price"));

                    list.add(lesson);
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return list;
    }

    public boolean createLesson(HashMap<String,Object> map)
    {

        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(insertLessonQuery)) {

            psmt.setString(1, (String)map.get("lessonName"));
            psmt.setString(2, (String)map.get("classroom"));
            psmt.setInt(3, (int)map.get("price"));
            psmt.setInt(4, (int)map.get("instructorId"));

            int rowNumber = psmt.executeUpdate();
            System.out.println(rowNumber + "has changed");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean lectureRegistration(int userId,int lessonId)
    {

        LessonRegistrationInfo info = selectLessonWithUserIdAndLessonId(userId,lessonId);

        if(info != null) return false;

        Date date = new Date(System.currentTimeMillis());

        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(lessonRegistrationQuery)) {

            psmt.setInt(1, userId);
            psmt.setInt(2, lessonId);
            psmt.setDate(3, date);

            int rowNumber = psmt.executeUpdate();
            System.out.println(rowNumber + "has changed");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public LessonRegistrationInfo selectLessonWithUserIdAndLessonId(int userId, int lessonId)
    {
        LessonRegistrationInfo info = new LessonRegistrationInfo();

        try (Connection conn = ds.getConnection();
             PreparedStatement psmt = conn.prepareStatement(selectLessonWithUserIdAndLessonIdQuery)) {

            psmt.setInt(1, userId);
            psmt.setInt(2,lessonId);

            try (ResultSet rs = psmt.executeQuery())
            {
                rs.next();
                info.setId(rs.getInt("id"));
                info.setUserId(rs.getInt("user_id"));
                info.setLessonId(rs.getInt("lesson_id"));
                info.setRegistrationDate(rs.getDate("registration_date"));

            }

        }
        catch (Exception e)
        {
            return null;
        }

        return info;

    }




}
