package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;


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
        
       public static int loginUser(String username, String password ){
           int id;
           String query = "SELECT * from myuser Where username = ? AND password = ?;";
           try( Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "mysql"); // here sonoo is database name, root is username and password
                  PreparedStatement pst = con.prepareStatement(query);){
               pst.setString(1, username);
               pst.setString(2, password);

               ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            else{
                id = 0;
            }
            pst.close();
            con.close();

        }catch(Exception e){
            id = 0;
            System.out.println(e);
        }
           return id;
       }

    }