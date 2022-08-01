package Persistence.DTO;

public class InstructorDTO extends UserDTO{
    private int instructorId;

    public InstructorDTO(){

    }

    public InstructorDTO(int instructorId){
        this.instructorId = instructorId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}
