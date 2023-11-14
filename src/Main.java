import java.util.*;

public class Main {
    private static Map<String, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.setProperty("console.encoding", "UTF-8");


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
                    printBestProgrammingFundamentals();
                    break;
                case 10:
                    printBestDatabaseManagementSystem();
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
        // ANSI escape code for green color
        String greenColor = "\u001B[32m";
        // ANSI escape code for resetting color
        String resetColor = "\u001B[0m";

        // Welcome message
        String welcomeMessage = "***************************************\n" +
                "*   Welcome to GDSE Marks Management  *\n" +
                "*               System                *\n" +
                "***************************************";

        // Calculate the width of the command prompt
        int cmdWidth = 80;  // Adjust this based on the actual width of your command prompt

        // Calculate the padding needed for centering
        int padding = Math.max((cmdWidth - welcomeMessage.length()) / 2, 0);

        // Print the centered and green welcome message
        System.out.println(" ".repeat(padding) + greenColor + welcomeMessage + resetColor);
    }


    private static void printMenu() {
        // ANSI escape code for green color
        String greenColor = "\u001B[32m";
        // ANSI escape code for resetting color
        String resetColor = "\u001B[0m";

        System.out.println("\n" + greenColor + "Menu Options:" + resetColor);

        // First row
        System.out.printf("%-2s %-40s", "1.", greenColor + "Add New Student" + resetColor);
        System.out.printf("%-2s %-30s", "2.", greenColor + "Add New Student with Marks" + resetColor);
        System.out.println();

        // Second row
        System.out.printf("%-2s %-40s", "3.", greenColor + "Add Marks" + resetColor);
        System.out.printf("%-2s %-30s", "4.", greenColor + "Update Student Details" + resetColor);
        System.out.println();

        // Third row
        System.out.printf("%-2s %-40s", "5.", greenColor + "Update Marks" + resetColor);
        System.out.printf("%-2s %-30s", "6.", greenColor + "Delete Student" + resetColor);
        System.out.println();

        // Fourth row
        System.out.printf("%-2s %-40s", "7.", greenColor + "Print Student Details" + resetColor);
        System.out.printf("%-2s %-30s", "8.", greenColor + "Print Student Ranks" + resetColor);
        System.out.println();

        // Fifth row
        System.out.printf("%-2s %-40s", "9.", greenColor + "Best in PF" + resetColor);
        System.out.printf("%-2s %-30s", "10.", greenColor + "Best in DBMS" + resetColor);
        System.out.println();

        // Sixth row
        System.out.printf("%-2s %-30s", "11.", greenColor + "Search Student" + resetColor);
        System.out.println();

        // Seventh row
        System.out.printf("%-2s %-30s", "12.", greenColor + "Exit" + resetColor);

        System.out.print("\nEnter the option number: ");
    }



    private static void addNewStudent(Scanner scanner) {
        do {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            if (students.containsKey(studentId)) {
                System.out.println("Student ID already exists. Please enter a different Student ID.");
                continue; // Restart the loop to prompt for a valid Student ID
            }

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            students.put(studentId, new Student(studentId, name));
            System.out.println("Student added successfully!");

            System.out.print("Do you want to add another student? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        } while (true);
    }



    private static void addNewStudentWithMarks(Scanner scanner) {
        do {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            if (students.containsKey(studentId)) {
                System.out.println("Student ID already exists. Please enter a different Student ID.");
                continue; // Restart the loop to prompt for a valid Student ID
            }

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            int pfMarks;
            do {
                System.out.print("Enter Marks for Programming Fundamentals (0-100): ");
                pfMarks = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (pfMarks < 0 || pfMarks > 100) {
                    System.out.println("Invalid marks. Please enter marks within the range of 0-100.");
                }
            } while (pfMarks < 0 || pfMarks > 100);

            int dbmsMarks;
            do {
                System.out.print("Enter Marks for Database Management System (0-100): ");
                dbmsMarks = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (dbmsMarks < 0 || dbmsMarks > 100) {
                    System.out.println("Invalid marks. Please enter marks within the range of 0-100.");
                }
            } while (dbmsMarks < 0 || dbmsMarks > 100);

            Student student = new Student(studentId, name);
            student.setMarks("Programming Fundamentals", pfMarks);
            student.setMarks("Database Management System", dbmsMarks);
            students.put(studentId, student);
            System.out.println("Student with marks added successfully!");

            System.out.print("Do you want to add another student? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        } while (true);
    }


    private static void addStudentMarks(Scanner scanner) {
        do {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            if (!students.containsKey(studentId)) {
                System.out.println("Student ID not found. Please enter a valid Student ID.");
                continue; // Restart the loop to prompt for a valid Student ID
            }

            Student student = students.get(studentId);

            if (student.getMarks("Programming Fundamentals") != -1 || student.getMarks("Database Management System") != -1) {
                System.out.println("Marks for this student have already been assigned.");
                break; // Exit the loop and go back to the main menu
            }

            System.out.println("Student Details:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());

            int newPfMarks;
            do {
                System.out.print("Enter New Marks for Programming Fundamentals (0-100): ");
                newPfMarks = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (newPfMarks < 0 || newPfMarks > 100) {
                    System.out.println("Invalid marks. Please enter marks within the range of 0-100.");
                }
            } while (newPfMarks < 0 || newPfMarks > 100);

            int newDbmsMarks;
            do {
                System.out.print("Enter New Marks for Database Management System (0-100): ");
                newDbmsMarks = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (newDbmsMarks < 0 || newDbmsMarks > 100) {
                    System.out.println("Invalid marks. Please enter marks within the range of 0-100.");
                }
            } while (newDbmsMarks < 0 || newDbmsMarks > 100);

            student.setMarks("Programming Fundamentals", newPfMarks);
            student.setMarks("Database Management System", newDbmsMarks);
            System.out.println("Marks added successfully!");

            System.out.print("Do you want to add marks for another student? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        } while (true);
    }



    private static void updateStudentDetails(Scanner scanner) {
        do {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            if (!students.containsKey(studentId)) {
                System.out.println("Student ID not found. Please enter a valid Student ID.");
                continue; // Restart the loop to prompt for a valid Student ID
            }

            Student student = students.get(studentId);

            System.out.println("Current Student Details:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Current Name: " + student.getName());

            System.out.print("Enter New Student Name: ");
            String newName = scanner.nextLine();

            student.setName(newName);
            System.out.println("Student details updated successfully!");

            System.out.print("Do you want to update another student's details? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        } while (true);
    }


    private static void updateStudentMarks(Scanner scanner) {
        do {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            if (!students.containsKey(studentId)) {
                System.out.println("Student ID not found. Please enter a valid Student ID.");
                continue; // Restart the loop to prompt for a valid Student ID
            }

            Student student = students.get(studentId);

            if (student.getMarks("Programming Fundamentals") == -1 || student.getMarks("Database Management System") == -1) {
                System.out.println("Marks for this student have not been added yet.");
                break; // Exit the loop and go back to the main menu
            }

            System.out.println("Student Details:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Current Marks - Programming Fundamentals: " + student.getMarks("Programming Fundamentals"));
            System.out.println("Current Marks - Database Management System: " + student.getMarks("Database Management System"));

            int newPfMarks;
            do {
                System.out.print("Enter New Marks for Programming Fundamentals (0-100): ");
                newPfMarks = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (newPfMarks < 0 || newPfMarks > 100) {
                    System.out.println("Invalid marks. Please enter marks within the range of 0-100.");
                }
            } while (newPfMarks < 0 || newPfMarks > 100);

            int newDbmsMarks;
            do {
                System.out.print("Enter New Marks for Database Management System (0-100): ");
                newDbmsMarks = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                if (newDbmsMarks < 0 || newDbmsMarks > 100) {
                    System.out.println("Invalid marks. Please enter marks within the range of 0-100.");
                }
            } while (newDbmsMarks < 0 || newDbmsMarks > 100);

            student.setMarks("Programming Fundamentals", newPfMarks);
            student.setMarks("Database Management System", newDbmsMarks);
            System.out.println("Marks updated successfully!");

            System.out.print("Do you want to update marks for another student? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        } while (true);
    }


    private static void deleteStudent(Scanner scanner) {
        do {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            if (!students.containsKey(studentId)) {
                System.out.println("Student ID not found. Please enter a valid Student ID.");
                continue; // Restart the loop to prompt for a valid Student ID
            }

            students.remove(studentId);
            System.out.println("Student deleted successfully!");

            System.out.print("Do you want to delete another student? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        } while (true);
    }


    private static void printStudentDetails(Scanner scanner) {
        do {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();

            if (!students.containsKey(studentId)) {
                System.out.println("Student ID not found. Please enter a valid Student ID.");
                continue; // Restart the loop to prompt for a valid Student ID
            }

            Student student = students.get(studentId);

            System.out.println("Student Details:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());

            int pfMarks = student.getMarks("Programming Fundamentals");
            int dbmsMarks = student.getMarks("Database Management System");

            if (pfMarks == -1 || dbmsMarks == -1) {
                System.out.println("Marks yet to be added for this student.");
            } else {
                System.out.println("Marks - Programming Fundamentals: " + pfMarks);
                System.out.println("Marks - Database Management System: " + dbmsMarks);

                // Calculate total marks, average marks, and rank
                int totalMarks = pfMarks + dbmsMarks;
                double avgMarks = totalMarks / 2.0;
                int rank = calculateRank(studentId);

                System.out.println("Total Marks: " + totalMarks);
                System.out.println("Average Marks: " + avgMarks);
                System.out.println("Rank: " + rank + getRankSuffix(rank));

                // Display top three and last rank positions
                if (rank == 1) {
                    System.out.println("First Rank (Gold Medalist)");
                } else if (rank == 2) {
                    System.out.println("Second Rank (Silver Medalist)");
                } else if (rank == 3) {
                    System.out.println("Third Rank (Bronze Medalist)");
                } else if (rank == students.size()) {
                    System.out.println("Last Rank (Tail Ender)");
                }

            }

            System.out.print("Do you want to view details for another student? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();
            if (!response.equals("yes")) {
                break;
            }
        } while (true);
    }

    private static int calculateRank(String studentId) {
        // Custom logic to calculate the rank based on total marks
        // You can implement your own ranking logic here
        // For simplicity, let's assume the student with the highest total marks gets the first rank
        int rank = 1;
        int studentTotalMarks = students.get(studentId).getMarks("Programming Fundamentals") +
                students.get(studentId).getMarks("Database Management System");

        for (String otherStudentId : students.keySet()) {
            if (!otherStudentId.equals(studentId)) {
                int otherStudentTotalMarks = students.get(otherStudentId).getMarks("Programming Fundamentals") +
                        students.get(otherStudentId).getMarks("Database Management System");

                if (otherStudentTotalMarks > studentTotalMarks) {
                    rank++;
                }
            }
        }

        return rank;
    }

    private static String getRankSuffix(int rank) {
        if (rank == 1 || rank % 10 == 1) {
            return "st";
        } else if (rank == 2 || rank % 10 == 2) {
            return "nd";
        } else if (rank == 3 || rank % 10 == 3) {
            return "rd";
        } else {
            return "th";
        }
    }


    private static void printStudentRanks() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

        // Create a list of students sorted by total marks in descending order
        List<Student> sortedStudents = new ArrayList<>(students.values());
        sortedStudents.sort((s1, s2) -> {
            int totalMarks1 = s1.getMarks("Programming Fundamentals") + s1.getMarks("Database Management System");
            int totalMarks2 = s2.getMarks("Programming Fundamentals") + s2.getMarks("Database Management System");
            return Integer.compare(totalMarks2, totalMarks1);
        });

        System.out.println("Student Ranks:");

        for (int i = 0; i < sortedStudents.size(); i++) {
            Student student = sortedStudents.get(i);
            int totalMarks = student.getMarks("Programming Fundamentals") + student.getMarks("Database Management System");
            double avgMarks = totalMarks / 2.0;

            System.out.println((i + 1) + ". Student ID: " + student.getStudentId());
            System.out.println("   Name: " + student.getName());
            System.out.println("   Total Marks: " + totalMarks);
            System.out.println("   Average Marks: " + avgMarks);
        }

        System.out.print("Do you want to stay in this view? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        if (!response.equals("yes")) {
            return;
        }
    }


    private static void printBestStudents(String subject) {
        List<Student> studentList = new ArrayList<>(students.values());
        studentList.sort(Comparator.comparingInt((Student student) -> student.getMarks(subject)).reversed());

        System.out.println("Best Students in " + subject + ":");
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            System.out.println((i + 1) + ". " + student.getName() +
                    " - " + subject + " Marks: " + student.getMarks(subject));
        }
    }

    private static void searchStudent(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (students.containsKey(studentId)) {
            Student student = students.get(studentId);
            System.out.println("Student found:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getName());
            System.out.println("Programming Fundamentals Marks: " + student.getMarks("Programming Fundamentals"));
            System.out.println("Database Management System Marks: " + student.getMarks("Database Management System"));
        } else {
            System.out.println("Student not found!");
        }
    }
    private static void printBestProgrammingFundamentals() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

        // Create a list of students sorted by Programming Fundamentals marks in descending order
        List<Student> sortedStudents = new ArrayList<>(students.values());
        sortedStudents.sort((s1, s2) -> Integer.compare(s2.getMarks("Programming Fundamentals"), s1.getMarks("Programming Fundamentals")));

        System.out.println("Top Performers in Programming Fundamentals:");

        for (int i = 0; i < sortedStudents.size(); i++) {
            Student student = sortedStudents.get(i);
            int pfMarks = student.getMarks("Programming Fundamentals");

            System.out.println((i + 1) + ". Student ID: " + student.getStudentId());
            System.out.println("   Name: " + student.getName());
            System.out.println("   Marks in Programming Fundamentals: " + pfMarks);
        }

        System.out.print("Do you want to stay in this view? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        if (!response.equals("yes")) {
            return;
        }
    }
    private static void printBestDatabaseManagementSystem() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

        // Create a list of students sorted by Database Management System marks in descending order
        List<Student> sortedStudents = new ArrayList<>(students.values());
        sortedStudents.sort((s1, s2) -> Integer.compare(s2.getMarks("Database Management System"), s1.getMarks("Database Management System")));

        System.out.println("Top Performers in Database Management System:");

        for (int i = 0; i < sortedStudents.size(); i++) {
            Student student = sortedStudents.get(i);
            int dbmsMarks = student.getMarks("Database Management System");

            System.out.println((i + 1) + ". Student ID: " + student.getStudentId());
            System.out.println("   Name: " + student.getName());
            System.out.println("   Marks in Database Management System: " + dbmsMarks);
        }

        System.out.print("Do you want to stay in this view? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();
        if (!response.equals("yes")) {
            return;
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
