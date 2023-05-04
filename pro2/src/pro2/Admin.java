package pro2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Admin {

    Scanner input = new Scanner(System.in);
    Teacher teacher = new Teacher();
    Student student = new Student();
    Course course = new Course();
    Connection connection;
    String query;
    PreparedStatement preparedStatement;

    public Admin(int id, String pass) {

        Sec sec = new Sec();

        try {
            connection = sec.connect();
            query = "insert into admin values (?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, pass);
            preparedStatement.execute();
            System.out.println("Admin " + id + " Added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void avilableAdmin(int id, String pass) {
        try {
            ResultSet resultSet = null;
            query = "select * from Admin where Admin_id=? and Admin_password=? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Enter 1 : Add");
                System.out.println("Enter 2 : Delete");
                int x = input.nextInt();
                if (x == 1) {
                    add();
                } else if(x==2){
                    delete();
                }else{
                    System.out.println("Invalid Number");
                }
            } else {
                System.out.println("Incorrect Password , try again");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void add() {
        System.out.println("Enter 1 : Add Teacher");
        System.out.println("Enter 2 : Add Student");
        System.out.println("Enter 3 : Add Course");
        System.out.println("Enter 4 : Add Student to Course");
        int x = input.nextInt();
        if (x == 1) {
            int id;
            String name, email, sex;
            try {
                System.out.print("Enter Teacher id : ");
                id = input.nextInt();
                System.out.print("Enter Teacher name : ");
                name = input.next();
                System.out.print("Enter Teacher Email : ");
                email = input.next();
                System.out.print("Enter Teacher Sex : ");
                sex = input.next();
                query = "insert into teacher values (?,?,?,?)";

                preparedStatement = connection.prepareStatement(query);

                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, sex);
                preparedStatement.execute();
                System.out.println("Teacher inserted");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (x == 2) {
            int id;
            String fname, lname, email, sex;
            try {
                System.out.print("Enter Student id : ");
                id = input.nextInt();
                int result = student.searchById(id);
                if (result == 0) {
                    System.out.print("Enter Student First name : ");
                    fname = input.next();
                    System.out.print("Enter Student Last name : ");
                    lname = input.next();
                    System.out.print("Enter Student Email : ");
                    email = input.next();
                    System.out.print("Enter Student Sex : ");
                    sex = input.next();
                    query = "insert into student values (?,?,?,?,?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, fname);
                    preparedStatement.setString(3, lname);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, sex);
                    preparedStatement.execute();
                    System.out.println("Student inserted");
                } else {
                    System.out.println("Duplicated Id");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (x == 3) {
            int course_id, teacher_id = 0;
            String course_name;
            try {
                System.out.print("Enter Course id : ");
                course_id = input.nextInt();
                System.out.print("Enter Course name : ");
                course_name = input.next();
                System.out.print("Enter Teacher id : ");
                teacher_id = input.nextInt();
                query = "insert into course values (?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, course_id);
                preparedStatement.setString(2, course_name);
                preparedStatement.setInt(3, teacher_id);
                preparedStatement.execute();
                System.out.println("Course inserted");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                int result = teacher.searchById(teacher_id);
                if (result == 0) {
                    System.out.println("Teacher Id not Found");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        } else if (x == 4) {
            System.out.print("Enter Student id : ");
            int student_id = input.nextInt();
            System.out.print("Enter Course id : ");
            int course_id = input.nextInt();
            System.out.print("Enter Start Date : ");
            String sDate = input.next();
            java.util.Date myDate = new java.util.Date(sDate);
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            System.out.print("Enter Price : ");
            int price = input.nextInt();
            try {
                query = "insert into Student_Courese values (?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, student_id);
                preparedStatement.setInt(2, course_id);
                preparedStatement.setDate(3, sqlDate);
                preparedStatement.setFloat(4, -1);
                preparedStatement.setInt(5, price);
                preparedStatement.execute();
                System.out.println("Student added to Course ");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                int resultStudent = student.searchById(student_id);
                if (resultStudent == 0) {
                    System.out.println("Student Id not Found");
                }
                int resultCourse = course.searchById(course_id);
                if (resultCourse == 0) {
                    System.out.println("Course Id not Found");
                }
            } 
        } else {
            System.out.println("Invalid Number");
        }

    }

    public void delete() {
        System.out.println("Enter 1 : Delete Teacher ");
        System.out.println("Enter 2 : Delete Student ");
        System.out.println("Enter 3 : Delete Course ");
        int x = input.nextInt();
        int id;
        if (x == 1) {

            try {
                System.out.println("Enter Teacher id : ");
                id = input.nextInt();
                query = "delete from teacher where teacher_id=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                System.out.println("Delete Teacher with id " + id + " done");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (x == 2) {
            try {
                System.out.println("Enter Student id : ");
                id = input.nextInt();
                query = "delete from student where student_id=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                System.out.println("Delete student with id " + id + " done");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Enter Course id : ");
            id = input.nextInt();
            try {
                System.out.println("Enter Course id : ");
                id = input.nextInt();
                query = "delete from course where course_id=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                System.out.println("Delete course with id " + id + " done");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
