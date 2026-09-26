// M5. Student and College Information Management
class Student {
    String name;
    int attendance;

    static String collegeName = "SRM Institute of Science and Technology";
    static int studentCount = 0;

    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++;
    }

    static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class M5_StudentCollege {
    public static void main(String[] args) {
        Student s1 = new Student("Kavya", 90);
        Student s2 = new Student("Rahul", 85);

        Student.printCollegeInfo();
    }
}
