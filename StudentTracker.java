import java.util.ArrayList;
import java.util.Scanner;

public class StudentTracker {
    public int MaxStudents;
    public int NumberOfStudents;
    public ArrayList<Student> StudentList;

    public StudentTracker(int max, int number){
        MaxStudents = max;
        NumberOfStudents = number;
        StudentList = new ArrayList<Student>();
    }

    

    public void InsertStudent(Student student){
        boolean exists = false;
        for (int i = 0; i < StudentList.size(); i ++){
            if (StudentList.get(i).Id == student.Id){
                System.out.println("Error. A student by that Id already exists. Cannot add student.");
                exists = true;
            }
        }
        if (!exists){
            StudentList.add(student);
            System.out.println("Success. We added the student to the list.");
        }
        
    }

    public void DeleteStudent(int studentid){
        boolean removed = false;
        for(int i = 0; i < StudentList.size(); i++){
            if (StudentList.get(i).Id == studentid){
                StudentList.remove(i);
                System.out.println("Success. A student was removed.");
                removed = true;
            }
        }
        if (removed == false){
            System.out.println("No student was removed. Could not find a student by that Id.");
        }
    }

    public Student GetStudent(int studentid){
        for(int i = 0; i < StudentList.size(); i++){
            if (StudentList.get(i).Id == studentid){
                return StudentList.get(i);
            }
        }
        return null;
    }

    public void UpdateStudent(Student student, Scanner in){
        System.out.println("Please update the student's name.");
        in.nextLine();
        String name = in.nextLine();
        student.Name = name;
        System.out.println("Please update the student's major.");
        String major = in.nextLine();
        student.Major = major;
    }

    public void DisplayStudent(Student student){
        if (student == null){
            System.out.println("No student was found.");
        } else {
            System.out.println("Student Id: " + student.Id + ". Student name: " + student.Name + ". Student major: " + student.Major);
        }
    }


}