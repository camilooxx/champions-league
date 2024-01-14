/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Connect.ConectarSQL;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mayca
 */
public class Jugadores {
    float Calificacion;
    int FrecuenteuOcasional;
    int Infracciones;
    float Promedio;
    int GRL;
    String nombre;
    // CODIGO PARA AGREGAR A LA BASE DE DATOS
    ConectarSQL con = new ConectarSQL();
    private final String SQL_INSERT = "INSERT INTO players (Calificacion, FrecuenteuOcasional, Infracciones, Promedio, GRL, Nombre, team) values(?,?,?,?,?,?,?)";
     // PARA SELECCIONAR TODA LA TABLA EN BASE DE DATOS
    private PreparedStatement PS; // PARA AGREGAR EN BASE DE DATOS players
    private ConectarSQL conect;
    public Jugadores() { // CONSTRUCTOR
        PS = null;
        conect = new ConectarSQL();
    }
    public int InsertarDatos(float Calificacion, int FrecuenteuOcasional,int Infracciones, float Promedio, int GRL, String nombre, String team) {
        try{
            PS = (PreparedStatement) conect.getConnection().prepareStatement(SQL_INSERT);
            PS.setFloat(1, Calificacion);
            PS.setInt(2, FrecuenteuOcasional);
            PS.setInt(3, Infracciones);
            PS.setFloat(4, Promedio);
            PS.setInt(5, GRL);
            PS.setString(6, nombre.trim());
            PS.setString(7, team.trim());
            int respuesta = PS.executeUpdate();
            if(respuesta > 0) {
                JOptionPane.showMessageDialog(null, "REGISTRO GUARDADO");
            } else {
                
            }
        }catch(Exception e) {
            System.out.println("Error al guardar los datos" +e.getMessage());
        } finally {
            PS = null;
            conect.desconectar();
        }
        return 0;
        
    }
    //public int InsertatPartidosBD(String Cuadrangular, int Resultado1, int Resultado2, String TeamVictory) {
        
    //}
    
    
}
