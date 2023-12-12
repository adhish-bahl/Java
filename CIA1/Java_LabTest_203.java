import java.util.Scanner;

abstract class Person {
    protected String name;
    protected int age;
    protected double height;

    

    public abstract void get();

    public abstract void show();

}

class Student extends Person {
    protected int studentId;
    protected String institutionName;
    protected String phNo;

    

    public void get() {
        
    }

    public void show() {

    }

}

class GraduateStudent extends Student {
    protected String programName;
    protected int noOfSubjects;
    protected String classTeacher;
    protected double marks;
    protected double attendance;

    

    public void get() {
        
    }

    public void show() {

    }
}

class Java_LabTest_203 {
    public static void main(String[] args) {

    }
}