package pro2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teacher {

    Connection connection;
    String query;
    PreparedStatement preparedStatement;
    Scanner input = new Scanner(System.in);
    Sec sec = new Sec();
    ResultSet resultSet = null;

    public Teacher() {
        try {
            connection = sec.connect();
            query = "insert into teacher values (?,?,?,? )";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "mo");
            preparedStatement.setString(3, "mov");
            preparedStatement.setString(4, "m");
            preparedStatement.execute();
            System.out.println("Teacher Added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int searchById(int id) {
        try {
            query = "select * from teacher where teacher_id = ?";
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

//    public void avilableTeacher(int teacherId){
//        try {
//            ResultSet resultSet ;
//            query = "select * from Teacher where Teacher_id=? ";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, teacherId);
//            resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                System.out.println("Done");
//            } else {
//                System.out.println("Id not Found ");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    public void showCourses(int id) {
        ResultSet resultSet = null;
        try {
            query = "select * from course where teacher_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                System.out.println("Teacher with Id = "+ id + "  has not any Courses");
            }else{
            System.out.println("Teacher with Id = "+ id + "  has Courses : ");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("course_id") + "  "
                        + resultSet.getString("name") );
            }
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    public int findTeacherIdOfCourse(int courseId){
         ResultSet resultSet = null;
        try {
            query = "select teacher_id from course where course_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                System.out.println("Course with Id = "+ courseId + "  has not Founded");
            }else{
            while (resultSet.next()) {
                return resultSet.getInt("teacher_id");
            }
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    public void setDegree(int teacherId) {
        System.out.print("Enter Student Id : ");
        int studentId=input.nextInt();
        System.out.print("Enter Course Id : ");
        int courseId=input.nextInt();
        int result=findTeacherIdOfCourse(courseId);
        if(result ==teacherId)
        {
            System.out.print("Enter Degree : ");
            float degree=input.nextFloat();
            try{
            query="update Student_Courese set degree=? where Student_id=? and Course_id=?";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setFloat(1, degree);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, courseId);
            preparedStatement.execute();
                System.out.println("Degree Updated");
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
        else{
            System.out.println("Sorry, this Course has another Teacher");
        }
    }
}
