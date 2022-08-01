package Persistence.DTO;

public class Lesson {

    private int id;
    private String lessonName;
    private String classroom;
    private int instructorId;
    private int price;

    public int getId() {
        return id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getClassroom() {
        return classroom;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
