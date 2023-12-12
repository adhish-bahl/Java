import java.util.Scanner;

abstract class Person {
    protected String name;
    protected int age;
    protected double height;
    protected String gender;

    public Person(String name, int age, double height, String gender) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.gender = gender;
    }

    public void get() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name: ");
        name = scanner.next();
        System.out.print("Age: ");
        age = scanner.nextInt();
        System.out.print("Height: ");
        height = scanner.nextDouble();
        System.out.print("Gender: ");
        gender = scanner.next();
    }

    public abstract void show();

}

class Student extends Person {
    protected static int currStudentId = 101;
    protected int studentId;
    protected String institutionName;
    protected String phNo;

    public Student(String name, int age, double height, String gender, String institutionName, String phNo) {
        super(name, age, height, gender);
        this.studentId = currStudentId;
        currStudentId++;
        this.institutionName = institutionName;
        this.phNo = phNo;
    }

    @Override
    public void get() {
        super.get();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Institution Name: ");
        institutionName = scanner.next();
        System.out.print("Phone Number: ");
        phNo = scanner.next();
    }

    @Override
    public void show() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Gender: " + gender);
        System.out.println("Student ID: " + studentId);
        System.out.println("Institution Name: " + institutionName);
        System.out.println("Phone Number: " + phNo);
    }
}

final class GraduateStudent extends Student {
    protected String programName;
    protected int noOfSubjects;
    protected String classTeacher;
    protected double marks;
    protected double attendance;

    public GraduateStudent() {
        super("", 0, 0, "", "", "");
        this.programName = "";
        this.noOfSubjects = 0;
        this.classTeacher = "";
        this.marks = 0;
        this.attendance = 0;
        this.get();
    }

    public GraduateStudent(String name, int age, double height, String gender, String institutionName, String phNo, String programName, int noOfSubjects, String classTeacher, double marks, double attendance) {
        super(name, age, height, gender, institutionName, phNo);
        this.programName = programName;
        this.noOfSubjects = noOfSubjects;
        this.classTeacher = classTeacher;
        this.marks = marks;
        this.attendance = attendance;
    }

    @Override
    public void get() {
        super.get();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Program Name: ");
        programName = scanner.next();
        System.out.print("Number of Subjects: ");
        noOfSubjects = scanner.nextInt();
        System.out.print("Class Teacher Name: ");
        classTeacher = scanner.next();
        System.out.print("Marks: ");
        marks = scanner.nextDouble();
        System.out.print("Attendance: ");
        attendance = scanner.nextDouble();
    }

    @Override
    final public void show() {
        super.show();
        System.out.println("Program Name: " + programName);
        System.out.println("Number of Subjects: " + noOfSubjects);
        System.out.println("Class Teacher Name: " + classTeacher);
        System.out.println("Marks: " + marks);
        System.out.println("Attendance: " + attendance);
    }
    
    final public static void showStudentID() { 
        System.out.println("Current Student ID: " + currStudentId);
    }
}

class Java_LabTest_203 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student[] students = new Student[7];
        students[0] = new GraduateStudent("Adhish", 22, 186.0, "Male", "Christ", "9999999999", "MCA", 5, "Teacher1", 68, 85);
        students[1] = new GraduateStudent("Bhomit", 21, 169.0, "Male", "VIT", "8888888888", "MBA", 6, "Teacher2", 75, 56);
        students[2] = new GraduateStudent("Shreya", 22, 159.0, "Female", "PES", "777777777", "MCA", 7, "Teacher3", 80, 65);
        students[3] = new GraduateStudent("Yash", 23, 180.0, "Male", "Christ", "666666666", "MBA", 7, "Teacher4", 60, 90);
        students[4] = new GraduateStudent("Ashna", 20, 150.0, "Female", "VIT", "5555555555", "MCA", 8, "Teacher5", 89, 90);
        students[5] = new GraduateStudent();
        students[6] = new GraduateStudent();

        int choice = 0;
        while(choice != 5) {
            System.out.println("================================================");
            System.out.println("Menu:");
            System.out.println("1. Display All Students");
            System.out.println("2. Display male students who have above 60% of marks in the program MCA.");
            System.out.println("3. Display female students who have above 75% of attendance in the program MCA.");
            System.out.println("4. Display the current Student ID.");
            System.out.println("5. Exit.");
            System.out.print("Enter Choice: ");
            choice = scanner.nextInt();
            System.out.println("--------------------------------------\n");

            switch (choice) {
                case 1: for (Student student : students) {
                            student.show();
                            System.out.println("--------------------------\n");
                        }
                    break;

                case 2: for (Student student : students) {
                            if (student.gender.equals("Male") && ((GraduateStudent) student).marks > 60 && ((GraduateStudent) student).programName.equals("MCA")) {
                                student.show();
                                System.out.println("--------------------------\n");
                            }
                        }
                    break;

                case 3: for (Student student : students) {
                            if (student.gender.equals("Female") && ((GraduateStudent) student).attendance > 75 && ((GraduateStudent) student).programName.equals("MCA")) {
                                student.show();
                                System.out.println("--------------------------\n");
                            }
                        }
                    break;

                case 4: GraduateStudent.showStudentID();
                    break;

                case 5: System.out.println("Exiting...");
                    break;
            
                default: System.out.println("Invalid Choice. Enter Again");
                    break;
            }
        }
        
        
    }
}