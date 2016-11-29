/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author cinthia
 */
public class Conexion {
    private String url = "jdbc:mysql://localhost:3306/recetario";
    private String user = "root";
    private String password = "1234";
    private Connection connection = null;
    
    public Conexion() {
        if (connection == null) {
          try {
              Class.forName("com.mysql.jdbc.Driver");
              connection = (Connection)DriverManager.getConnection(this.url,this.user,this.password);
          } catch (SQLException e) {
              e.printStackTrace();
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
        }
    }
    
    public Connection get_connection() {
        return this.connection;
    }
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
