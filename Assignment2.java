import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Assignment2 {

    public static void main(final String[] args) throws IOException {
        System.out.println("Hello world - this is assignment 2 in Java!");
        Scanner in = new Scanner(System.in);
        int maxsize = SetMaximumSize(in);
        int students = SetNumberOfStudents(maxsize, in);
        while (students == -1) {
            students = SetNumberOfStudents(maxsize, in);
        }
        StudentTracker tracker = new StudentTracker(maxsize, students);
        EnterDeclarativeState(tracker, in);
        EnterMenuState(tracker, in);
        in.close();
        System.out.println("Exiting program. Have a nice day!");
    }

    public static boolean AssertNumber(String input, String value) {
        try {
            int val = Integer.parseInt(input);
            System.out.println("Success. You assigned a value of " + input + " to " + value);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Error. " + input + " is not a valid value for " + value);
            return false;
        }
    }

    public static int SetMaximumSize(Scanner in) {
        System.out.println("Please input maximum number of students.");
        String s = in.next();
        while (!AssertNumber(s, "maximum size")) {
            s = in.next();
        }
        int result = Integer.parseInt(s);
        return result;
    }

    public static int SetNumberOfStudents(int max, Scanner in) {
        System.out.println("Please input the number of students. Must be less than " + max + " but greater than 0.");
        String s = in.next();
        while (!AssertNumber(s, "student number")) {
            s = in.next();
        }
        int val = Integer.parseInt(s);
        if (val > max || val <= 0) {
            System.out.println("Number of students cannot be greater than " + max + ". Please try again.");
            return -1;
        }
        return val;
    }

    public static void EnterDeclarativeState(StudentTracker tracker, Scanner in) {
        // Begin declarative state
        // If no students exists, continue declaring and inserting students until list
        // contains all current students.
        while (tracker.StudentList.size() < tracker.NumberOfStudents) {
            Student student = DeclareStudent(in);
            tracker.InsertStudent(student);
        }

    }

    public static void EnterMenuState(StudentTracker tracker, Scanner in) {
        // Menu state begins prompts for new actions. Actions 1 - 5 update list. Action
        // 6 exits program.
        
        int action = -1;
        while (action != 6) {
            System.out.println("Welcome to the main menu.");
            System.out.println("Please enter a number for one of the following actions: 1 to insert new student, 2 to fetch a student by Id, 3 to delete a student, 4 to update a student, 5 to output all students and 6 to exit the program.");
            action = in.nextInt();
            while (action < 1 || action > 6) {
                System.out.println("Invalid input. Please select an option from the above choices.");
                action = in.nextInt();
            }
            if (action == 1) {
                System.out.println("Insert a new student. Make sure the student Id is not duplicate.");
                Student student = DeclareStudent(in);
                tracker.InsertStudent(student);
            }
            if (action == 2) {
                System.out.println("Fetch a student's information. Please enter the student Id.");
                int studentid = in.nextInt();
                Student student = tracker.GetStudent(studentid);
                tracker.DisplayStudent(student);
            }
            if (action == 3) {
                System.out.println("Delete a student. Please enter the student Id.");
                int studentid = in.nextInt();
                tracker.DeleteStudent(studentid);
            }
            if (action == 4) {
                System.out.println("Update a student. Please enter the Student Id you wish to update.");
                int studentid = in.nextInt();
                Student student = tracker.GetStudent(studentid);
                tracker.DisplayStudent(student);
                tracker.UpdateStudent(student, in);
            }
            if (action == 5) {
                System.out.println("Output sorted list of all students: ");
                List<Student> sortedlist = tracker.StudentList;
                sortedlist.sort(Comparator.naturalOrder());
                for (int i = 0; i < sortedlist.size(); i ++){
                    tracker.DisplayStudent(sortedlist.get(i));
                }
            }
        }
        
        
    }

    public static Student DeclareStudent(Scanner in){
        System.out.println("Please enter the student Id. This must be an integer value.");
        Student student = new Student();
        String idstring = in.next();
        while(!AssertNumber(idstring, "student Id")){
            idstring = in.next();
        }
        student.Id = Integer.parseInt(idstring);
        System.out.println("Please enter the student name.");
        in.nextLine();
        String name = in.nextLine();
        student.Name = name;
        System.out.println("Please enter the student's major.");
        String major = in.nextLine();
        student.Major = major;
        return student;
    }
}