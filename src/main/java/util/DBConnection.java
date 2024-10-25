/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author hoang
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
  private static final String URL = "jdbc:mysql://localhost:3306/lab6";
  private static final String USER = "hoenmike";
  private static final String PASSWORD = "Crtm123123";
  
  public static Connection getConnection() throws SQLException {
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          return DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (ClassNotFoundException e) {
          throw new SQLException("Database driver not found", e);
      }
  } 
}
