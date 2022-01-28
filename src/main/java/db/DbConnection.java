package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kartik
 */
public class DbConnection {

    public static void saveUser(String username, String email, String password) {
        try ( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "mysql"); // here sonoo is database name, root is username and password
                  Statement stmt = con.createStatement();) {

            stmt.execute("INSERT INTO myuser (username, email, password) VALUES ('" + username + "','" + email
                    + "','" + password + "');");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}