package Persistence.DTO;

public class TeacherDTO {
    public String teacherId;
    public String teacherName;
    public String teacherPassWord;

    public TeacherDTO(String teacherId, String teacherName, String teacherPassWord){
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherPassWord = teacherPassWord;
    }

    public TeacherDTO() {

    }

    public String getTeacherId(){
        return teacherId;
    }

    public void setTeacherId(String teacherId){
        this.teacherId = teacherId;
    }

    public String getTeacherName(){
        return teacherName;
    }

    public void setTeacherName(String teacherName){
        this.teacherName = teacherName;
    }

    public String getTeacherPassWord(){
        return teacherPassWord;
    }

    public void setTeacherPassWord(String teacherPassWord){
        this.teacherPassWord = teacherPassWord;
    }
}
