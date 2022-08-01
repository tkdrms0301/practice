package Persistence.DTO;

import java.sql.Date;

public class LessonRegistrationInfo {

    private int id;
    private int userId;
    private int lessonId;
    private Date registrationDate;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
