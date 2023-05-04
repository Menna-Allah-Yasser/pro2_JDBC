package pro2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Pro2 {

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);

        Admin admin2 = new Admin(02, "pass2");
        Teacher teacher = new Teacher();
        Student student = new Student();

        System.out.println("Enter 1 : Admin");
        System.out.println("Enter 2 : Teacher");
        System.out.println("Enter 3 : Student");
        int x = input.nextInt();

        if (x == 1) { // admin

            System.out.print("id : ");
            int id = input.nextInt();
            System.out.print("Password : ");
            String pass = input.next();
            admin2.avilableAdmin(id, pass);

        } else if (x == 2) {
            System.out.print("Teacher Id : ");
            int teacherId = input.nextInt();
            int r = teacher.searchById(teacherId);
            if (r == 1) {
                System.out.println("Enter 1 : Show Courses");
                System.out.println("Enter 2 : set degree to student");
                int y = input.nextInt();
                if (y == 1) {
                    teacher.showCourses(teacherId);
                } else {
                    teacher.setDegree(teacherId);
                }
            } else {
                System.out.println("Teacher not found");
            }
        } else if (x == 3) {//Student
            System.out.print("Student Id : ");
            int studentId = input.nextInt();
            int result = student.searchById(studentId);
            if (result == 1) {
                System.out.println("Enter 1 : show your Courses");
                int in = input.nextInt();
                if (in == 1) {
                    student.showCourses(studentId);
                }
            }else{
                System.out.println("Invalid Student Id");
            }
        } else {
            System.out.println("Invalid Number");
        }

    }

}
