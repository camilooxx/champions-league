/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Connect.ConectarSQL;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
/**
 *
 * @author mayca
 */
public class ConsultarDatosBD {
    private PreparedStatement PS;
    private ConectarSQL connect;
    private ResultSet RS; //PARA GUARDAR LUEGO EL RESULTADO DE LO QUE NOS LANZE LA BASE DE DATOS
    // String's necesarios tenerlos afuera para guardar variables en un private void
    String team = "x"; 
    String teamtochange = "x";
    private final String SQL_SELECT = "SELECT Equipo1, Equipo2, Resultado1, Resultado2, TeamVictory FROM partido"; //CONSULTA QUE QUIERO HACER
    private final String SQL_SELECT_CHAMPION = "SELECT TeamVictory FROM partido WHERE Cuadrangular = 'FINAL' ";
    // CONSULTA PARA ENCONTRAR EL EQUIPO CAMPEO
    public ConsultarDatosBD() { // CODIGO PARA ENLAZAR Y EMPEZAR OON EL PS NULL IGUAL QUE EL CONNECT
    PS = null;
    connect = new ConectarSQL();
    }
     public List<String> consultarYGuardarEquipos(int Cuadrangular) { // 0 SI CUADRANGULAR ES SEMIFINAL, 1 SI ES FINAL
        List<String> equipos = new ArrayList<>();
        
        
        if(Cuadrangular == 0) { // SI CUADRANGULAR ES IGUAL A 1 GUARDE TODOS LOS DATOS DE LA BD
           try {
            // Establecer la conexión con la base de datos
            Connection connection = connect.getConnection();

            // Preparar la consulta SQL
            PS = (PreparedStatement) connection.prepareStatement(SQL_SELECT);

            // Ejecutar la consulta
            RS = PS.executeQuery();

            // Obtener los datos y almacenarlos en la lista
            while (RS.next()) {
                String equiposs = RS.getString("Equipo1");
                equipos.add(equiposs);
                equiposs = RS.getString("Equipo2");
                equipos.add(equiposs);
                equiposs = RS.getString("TeamVictory");
                equipos.add(equiposs);
                equiposs = RS.getString("Resultado1");
                equipos.add(equiposs);
                equiposs = RS.getString("Resultado2");
                equipos.add(equiposs);
                
            }

            // Cerrar recursos
            RS.close();
            PS.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } 
        }
        if(Cuadrangular == 1) { //SI CUADRANGULAR ES IGUAL A 1 CONSULTE SOLAMENTE EL EQUIPO CAMPEON
            
            try {
            // Establecer la conexión con la base de datos
            Connection connection = connect.getConnection();

            // Preparar la consulta SQL
            PS = (PreparedStatement) connection.prepareStatement(SQL_SELECT_CHAMPION);

            // Ejecutar la consulta
            RS = PS.executeQuery();

            // Obtener los datos y almacenarlos en la lista
            while (RS.next()) {
                String equiposs = RS.getString("TeamVictory");
                equipos.add(equiposs);
            }

            // Cerrar recursos
            RS.close();
            PS.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } 
        }
        

        return equipos;
    }
     public DefaultListModel<String> ConsultarJugadores(String teamselected) { //PARA RETORNAR UNA LISTA COMPLETA DE LOS JUGADORES
         DefaultListModel<String> jugadores = new DefaultListModel<>();
         
         team = teamselected;
         try {
        // Establecer la conexión con la base de datos
        Connection connection = connect.getConnection();

        // Preparar la consulta SQL con un WHERE para filtrar por equipo
        String SQL_SELECT_TEAM = "SELECT Nombre FROM players WHERE team = ?";
        PS = (PreparedStatement) connection.prepareStatement(SQL_SELECT_TEAM);
        PS.setString(1, teamselected);

        // Ejecutar la consulta
        ResultSet RS = PS.executeQuery();

        // Obtener los datos y almacenarlos en la lista
        while (RS.next()) {
            String JugadoresRS = RS.getString("Nombre");
            jugadores.addElement(JugadoresRS);
        }

        // Cerrar recursos
        RS.close();
        PS.close();
        connection.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
         return jugadores;
     }
     
     public void ChangeTeamPlayers(String jugador, String TeamtoChange) {
         String SQL_CHANGE_TEAM = "UPDATE players SET team = ? WHERE Nombre = ?";
         try {
        // Establecer la conexión con la base de datos
        Connection connection = connect.getConnection();

        PS = (PreparedStatement) connection.prepareStatement(SQL_CHANGE_TEAM);
        PS.setString(1, TeamtoChange);
        PS.setString(2, jugador);
        // Ejecutar la consulta
        int RS = PS.executeUpdate();
        
        // Cerrar recursos
        PS.close();
        connection.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
         
   }
     
    
}
