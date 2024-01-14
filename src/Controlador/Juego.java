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
public class Juego {
    String[] Teamsarray = {"Bayern Munich", "Real Madrid", "FC Barcelona", "Manchester City"}; // ARRAY DE LOS EQUIPOS
    int GolesEquipo1 = 0;
    int GolesEquipo2 = 0;
    String Resultado = null;
    ConectarSQL con = new ConectarSQL();
    private final String SQL_INSERT = "INSERT INTO partido (Cuadrangular, Resultado1, Resultado2, TeamVictory, Equipo1, Equipo2) values(?,?,?,?,?,?)";
    private PreparedStatement PS; // PARA AGREGAR EN BASE DE DATOS partido
    private ConectarSQL conect;
    int cuadrangular = 0; //1 SI ES FINAL, 0 SI ES SEMIFINAL
    public Juego() { // CONSTRUCTOR
        PS = null;
        conect = new ConectarSQL();
    }
    
    public int getGolesEquipo1() {
        GolesEquipo1 = (int) (Math.random() * 10);
        return GolesEquipo1;
    }

    public int getGolesEquipo2() {
        GolesEquipo2 = (int) (Math.random() * 10);
        return GolesEquipo2;
    }
    
    
    public int InsertarPartidos(String Cuadrangular, int GolesTeam1, int GolesTeam2, String TeamVictory, String Team1, String Team2, boolean CuadrangularTerminado) {
        
        try{
            PS = (PreparedStatement) conect.getConnection().prepareStatement(SQL_INSERT);
            PS.setString(1, Cuadrangular.trim());
            PS.setInt(2, GolesTeam1);
            PS.setInt(3, GolesTeam2);
            PS.setString(4, TeamVictory.trim());
            PS.setString(5, Team1.trim());
            PS.setString(6, Team2.trim());
            int respuesta = PS.executeUpdate();
            if(respuesta > 0) {
                JOptionPane.showMessageDialog(null, "REGISTRO GUARDADO");
            } else {
                
            }
        }catch(Exception e) {
            System.out.println("Error al guardar los datos" +e.getMessage());
        } finally {
            PS = null;
            if(CuadrangularTerminado == true) {
                conect.desconectar();
            }
            
        }
        return 0;
        
    }
    
    public String GenerarLlaves() {
         // FUNCION PARA GENERAR LAS LLAVES
        int numeroaleatorio;
        String Teamseleccionado;
        boolean band = false;
         // Escoger numero aleatorio entre 1 y 4
        while(band == false) {
            numeroaleatorio = (int) (Math.random() * 4);
            System.out.println("Numero aleatorio: "+numeroaleatorio);
            if(!Teamsarray[numeroaleatorio].equals("x")) {
            Teamseleccionado = Teamsarray[numeroaleatorio];
            Teamsarray[numeroaleatorio] = "x";
            band = true;
            return Teamseleccionado;
        }
        }
        return "Error";
    }
    
}
