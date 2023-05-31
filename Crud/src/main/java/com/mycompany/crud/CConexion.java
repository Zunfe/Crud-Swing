/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author afzv8
 */
public class CConexion {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/crudjava";
    String usuario = "root";
    String clave = "";
    
  
    public Connection Conexion(){
    Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,usuario,clave);
        // JOptionPane.showMessageDialog(null,"La conexion a BD fue OK ");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error de conexion a BD " + e.toString());
        }
         return conn;
    }
   
    
}
