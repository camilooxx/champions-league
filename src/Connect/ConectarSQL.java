
package Connect;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarSQL {
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/futbol5";
    public ConectarSQL() {
        conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, user, password);
            if(conn != null) {
                System.out.println("Conexión establecida");
            }
        }catch(ClassNotFoundException | SQLException e) {
            System.out.println("- Error al conectar.");
    }
   
}
    // con esto nos retorna a la base de datos..
    public Connection getConnection() {
        return conn;
    }
    // metodo para desconectarse de la base de datos.
    public void desconectar() {
        conn = null;
        if(conn == null) {
            System.out.println("Conexión terminada a la base de datos.");
        }
    }
    
  
    
}