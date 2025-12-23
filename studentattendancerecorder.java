// A student attendance recorder.

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

//Student class.
class Student {
  private int rollno;
  private String name;

  Student(int rollno, String name) {
    this.rollno = rollno;
    this.name = name;

  }

  String getName() {
    return name;
  }

  int getRoll() {
    return rollno;
  }
}

// Main class
class StudentAttendanceRecorder {

  static HashMap<Integer, Student> students = new HashMap<Integer, Student>();
  static HashMap<Integer, Boolean> attendance = new HashMap<Integer, Boolean>();

  // main method
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("\t\t======= Student Attendance Recorder =======");
    while (true) {
      // menu
      System.out.println("1. Add student");
      System.out.println("2. Mark Attendance to an existing student");
      System.out.println("3. Remove student");
      System.out.println("4. View Students");
      System.out.println("5. View Students with Attendance");
      System.out.println("0. Exit");
      System.out.print("\nChoose an option: ");
      int choice = sc.nextInt();
      sc.nextLine();
      System.out.println();

      switch (choice) {
        case 1:
          addStudent(sc);
          break;
        case 2:
          markAttendance(sc);
          break;
        case 3:
          removeStudent(sc);
          break;
        case 4:
          viewStudents();
          break;
        case 5:
          viewStudentsWithAttendance();
          break;
        case 0:
          exit();
          sc.close();
          return;

        default:
          System.out.println("Please enter a valid choice!!");
      }
    }
  }

  public static void addStudent(Scanner sc) {
    System.out.print("Enter Student Roll number: ");
    int roll = sc.nextInt();
    sc.nextLine();

    if (students.containsKey(roll)) {
      System.out.println("Student with same Roll number already Exists!!");
      return;
    }

    System.out.print("Enter Student's Name: ");
    String name = sc.nextLine();

    students.put(roll, new Student(roll, name));
    attendance.put(roll, false);
    System.out.println("Student added successfully!!");
    System.out.println();
  }

  public static void markAttendance(Scanner sc) {

    if (students.isEmpty()) {
      System.out.println("No students found!");
      System.out.println();
      return;
    }
    for (Map.Entry<Integer, Student> entry : students.entrySet()) {
      System.out.print(entry.getKey() + ". " + entry.getValue().getName() + " -> ");
      String present = sc.nextLine();
      if (present.contains("p") || present.contains("P")) {
        attendance.put(entry.getKey(), true);
      } else if (present.contains("a") || present.contains("A")) {
        attendance.put(entry.getKey(), false);
      }
    }
    System.out.println();
  }

  public static void removeStudent(Scanner sc) {

    if (students.isEmpty()) {
      System.out.println("No students found!");
      System.out.println();
      return;
    }

    System.out.print("Enter student's Roll number: ");
    int roll = sc.nextInt();
    System.out.print("Are you sure?(y/n): ");
    char sure = sc.next().charAt(0);

    if (Character.toLowerCase(sure) == 'n') {
      return;
    }

    students.remove(roll);
    attendance.remove(roll);
    System.out.println("Student removed Successfully!");
    System.out.println();
  }

  public static void viewStudents() {

    if (students.isEmpty()) {
      System.out.println("No students found!");
      System.out.println();
      return;
    }

    System.out.println("Student's List:");
    for (Map.Entry<Integer, Student> entry : students.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue().getName());
    }
    System.out.println();

  }

  public static void viewStudentsWithAttendance() {

    if (students.isEmpty()) {
      System.out.println("No students found!");
      System.out.println();
      return;
    }

    System.out.println("Student's List With Attendance:");

    for (Map.Entry<Integer, Student> entry : students.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue().getName() + " -> "
          + (attendance.get(entry.getKey()) ? "Present" : "Absent"));
    }
    System.out.println();

  }

  public static void exit() {
    System.out.print("Exiting");

    try {
      for (int i = 1; i <= 3; i++) {
        Thread.sleep(500);
        System.out.print(".");
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("\nThank you for using Student Attendance recorder!!");
  }
}
