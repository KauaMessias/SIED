/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.*;

public class Modulo_Conexao {
    
    public static Connection connector() {
        java.sql.Connection conexao = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_escola1";
        String user = "root";
        String password = "1234";
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
}
