public class Student implements Comparable<Student> { // Test2
    private int korean;
    private int math;
    private int english;
    private int physics;
    private int computer;

    public Student(int koreanGrade, int mathGrade, int englishGrade, int physicsGrade, int computerGrade) {
        korean = koreanGrade;
        math = mathGrade;
        english = englishGrade;
        physics = physicsGrade;
        computer = computerGrade;
    }

    public Comparable getAverage() {
        return (korean + math + english + physics + computer) / 5;
    }

    @Override
    public int compareTo(Student sdt) {
        return this.getAverage().compareTo(sdt.getAverage());
    }
}
