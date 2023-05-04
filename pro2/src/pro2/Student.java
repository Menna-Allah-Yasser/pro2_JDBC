package pro2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {

    static Connection connection;
    static String query;
    static PreparedStatement preparedStatement;
    Scanner input = new Scanner(System.in);
    Sec sec = new Sec();
    ResultSet resultSet = null;
    
    public Student(){
        try {
            connection = sec.connect();
            query = "insert into student values (?,?,?,?.? )";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "mo");
            preparedStatement.setString(3, "yasser");
            preparedStatement.setString(4, "mo2@gmail");
            preparedStatement.setString(5, "m");
            preparedStatement.execute();
            System.out.println("Student Added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int searchById(int id) {
        try {
            query = "select * from student where student_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void showCourses(int studentId) {
        ResultSet resultSet = null;
        try {
            query = "select s.course_id , c.name , s.degree\n"
                    + "from Student_Courese s\n"
                    + "join course c\n"
                    + "on s.course_id = c.course_id\n"
                    + "where s.student_id =?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                System.out.println("Syudent with Id = "+ studentId + "  has not enrolled Courses");
            }else{
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("course_id") + "  "
                        + resultSet.getString("name") + "  " + resultSet.getFloat("degree"));
            }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
