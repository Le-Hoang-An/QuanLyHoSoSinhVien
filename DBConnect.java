/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ACER
 */
public class DBConnect {

     public static Connection getConnection(){
        String host = "localhost";
        String user = "root";
        String pass ="";
        String port = "3306";
        String dbName = "quanlyhososinhvien";

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName +
                     "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

