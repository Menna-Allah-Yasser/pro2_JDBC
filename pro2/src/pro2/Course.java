
package pro2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Course {
    static Connection connection;
    static String query;
    static PreparedStatement preparedStatement;
    Scanner input = new Scanner(System.in);
    Sec sec = new Sec();
    ResultSet resultSet = null;

    public int searchById(int id) {
        try {
            query = "select * from course where course_id = ?";
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
}
