/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author afzv8
 */
public class CAlumnos {
    
    int codigo;
    String nombreAlumnos;
    String apellidoAlumnos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidoAlumnos() {
        return apellidoAlumnos;
    }

    public void setApellidoAlumnos(String apellidoAlumnos) {
        this.apellidoAlumnos = apellidoAlumnos;
    }
    
    
    public void InsertarAlumno(JTextField paramNombres,JTextField paramApellidos){
       
            setNombreAlumnos(paramNombres.getText());
            setApellidoAlumnos(paramApellidos.getText());
            
            
            CConexion objetoConexion = new CConexion();
            
            String query = ("INSERT INTO alumnos (nombres,apellidos) VALUES (?,?);");
            
         try {
            CallableStatement cs = objetoConexion.Conexion().prepareCall(query);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidoAlumnos());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se inserto el Alumno");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto el Alumno" + e.toString());
        }

    }
        
    public void MostrarAlumnos(JTable paramTablaAlumnos){
      
        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaAlumnos.setRowSorter(OrdenarTabla);
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        paramTablaAlumnos.setModel(modelo);
        
           String sql ="SELECT * FROM alumnos";
           
           String[] datos = new String[3];
           
           
         try {

            Statement stmt = objetoConexion.Conexion().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);

            }

            paramTablaAlumnos.setModel(modelo);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los Registros " + e.toString());
        }
    }
    
                                            public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId,JTextField paramNombres,JTextField paramApellidos){
        
        try {

            int row = paramTablaAlumnos.getSelectedRow();

            if (row >= 0) {

                paramId
                        .setText((String) (paramTablaAlumnos.getValueAt(row, 0).toString()));
                paramNombres
                        .setText((String) (paramTablaAlumnos.getValueAt(row, 1).toString()));
                paramApellidos
                        .setText((String) (paramTablaAlumnos.getValueAt(row, 2).toString()));

            } else {
                JOptionPane.showMessageDialog(null, "Fila no selecionada");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de seleccion" + e.toString());
        }

    }
    
    public void Modificar(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos(paramNombres.getText());
        setApellidoAlumnos(paramApellidos.getText());
        
      CConexion objetoConexion = new CConexion();
      
        String query ="UPDATE alumnos set nombres = ? , apellidos = ? WHERE id = ?;";  
        
        try {

            CallableStatement cs = objetoConexion.Conexion().prepareCall(query);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidoAlumnos());
            cs.setInt(3, getCodigo());          
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion Exitosa");

        } catch (SQLException e) {
               JOptionPane.showMessageDialog(null, "Modificacion Exitosa" + e.toString());
        }

    }
    
    public void EliminarAlumno(JTextField paramCodigo){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
         CConexion objetoConexion = new CConexion();
         
          String query ="DELETE FROM alumnos WHERE id = ?;";  
          
          try {
              
               CallableStatement cs = objetoConexion.Conexion().prepareCall(query);
           cs.setInt(1, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se Elimino el Alumnos");
            
        } catch (SQLException e) {
            
              JOptionPane.showMessageDialog(null, "No Se Elimino el Alumnos" + e.toString());
        }
        
    }
}
