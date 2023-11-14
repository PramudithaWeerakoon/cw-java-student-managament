import java.util.*;

public class StudentManagementSystem {
    private static Map<String, Student> students = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printWelcomeMessage();
            printMenu();
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    addNewStudent(scanner);
                    break;
                case 2:
                    addNewStudentWithMarks(scanner);
                    break;
                case 3:
                    addStudentMarks(scanner);
                    break;
                case 4:
                    updateStudentDetails(scanner);
                    break;
                case 5:
                    updateStudentMarks(scanner);
                    break;
                case 6:
                    deleteStudent(scanner);
                    break;
                case 7:
                    printStudentDetails(scanner);
                    break;
                case 8:
                    printStudentRanks();
                    break;
                case 9:
                    printBestStudents("Programming Fundamentals");
                    break;
                case 10:
                    printBestStudents("Database Management System");
                    break;
                case 11:
                    searchStudent(scanner);
                    break;
                case 12:
                    System.out.println("\nExiting the program. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid option. Please try again.");
                    break;
            }
        }
    }

    private static void printWelcomeMessage() {
        System.out.println("***************************************");
        System.out.println("*   Welcome to GDSE Marks Management  *");
        System.out.println("*               System                *");
        System.out.println("***************************************");
    }

    private static void printMenu() {
        System.out.println("\nMenu Options:");
        System.out.println("1. Add New Student");
        System.out.println("2. Add New Student with Marks");
        System.out.println("3. Add Marks");
        System.out.println("4. Update Student Details");
        System.out.println("5. Update Marks");
        System.out.println("6. Delete Student");
        System.out.println("7. Print Student Details");
        System.out.println("8. Print Student Ranks");
        System.out.println("9. Best Students in Programming Fundamentals");
        System.out.println("10. Best Student in Database Management System");
        System.out.println("11. Search Student");
        System.out.println("12. Exit");
        System.out.print("\nEnter the option number: ");
    }

    private static void printBestStudents(String subject) {
        List<Student> studentList = new ArrayList<>(students.values());
        studentList.sort(Comparator.comparingInt(student -> student.getMarks(subject)).reversed());

        System.out.println("Best Students in " + subject + ":");
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            System.out.println((i + 1) + ". " + student.getName() +
                    " - " + subject + " Marks: " + student.getMarks(subject));
        }
    }

    private static void addNewStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        students.put(studentId, new Student(studentId, name));
        System.out.println("Student added successfully!");
    }

    private static void addNewStudentWithMarks(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Marks for Programming Fundamentals: ");
        int pfMarks = scanner.nextInt();
        System.out.print("Enter Marks for Database Management System: ");
        int dbmsMarks = scanner.nextInt();

        Student student = new Student(studentId, name);
        student.setMarks("Programming Fundamentals", pfMarks);
        student.setMarks("Database Management System", dbmsMarks);
        students.put(studentId, student);
        System.out.println("Student with marks added successfully!");
    }

    private static void addStudentMarks(Scanner scanner, String subject) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            System.out.print("Enter Marks for " + subject + ": ");
            int marks = scanner.nextInt();
            students.get(studentId).setMarks(subject, marks);
            System.out.println(subject + " marks added successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void updateStudentDetails(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            System.out.print("Enter New Student Name: ");
            String newName = scanner.nextLine();
            students.get(studentId).setName(newName);
            System.out.println("Student details updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void updateStudentMarks(Scanner scanner, String subject) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            System.out.print("Enter New Marks for " + subject + ": ");
            int newMarks = scanner.nextInt();
            students.get(studentId).setMarks(subject, newMarks);
            System.out.println(subject + " marks updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            students.remove(studentId);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void printStudentDetails(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            System.out.println("Student Details:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Programming Fundamentals Marks: " + student.getMarks("Programming Fundamentals"));
            System.out.println("Database Management System Marks: " + student.getMarks("Database Management System"));
        } else {
            System.out.println("Student not found!");
        }
    }

    private static void printStudentRanks() {
        List<Student> studentList = new ArrayList<>(students.values());
        studentList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int totalMarks1 = s1.getMarks("Programming Fundamentals") + s1.getMarks("Database Management System");
                int totalMarks2 = s2.getMarks("Programming Fundamentals") + s2.getMarks("Database Management System");
                return Integer.compare(totalMarks2, totalMarks1);
            }
        });

        System.out.println("Student Ranks based on Marks:");
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            System.out.println((i + 1) + ". " + student.getName() +
                    " - PF Marks: " + student.getMarks("Programming Fundamentals") +
                    ", DBMS Marks: " + student.getMarks("Database Management System"));
        }
    }


    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            System.out.println("Student found:");
            Student student = students.get(studentId);
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Programming Fundamentals Marks: " + student.getMarks("Programming Fundamentals"));
            System.out.println("Database Management System Marks: " + student.getMarks("Database Management System"));
        } else {
            System.out.println("Student not found!");
        }
    }
}

class Student {
    private String studentId;
    private String name;
    private Map<String, Integer> marks = new HashMap<>();

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getMarks(String subject) {
        return marks.getOrDefault(subject, -1);
    }

    public void setMarks(String subject, int marks) {
        this.marks.put(subject, marks);
    }

    public void setName(String name) {
        this.name = name;
    }
}