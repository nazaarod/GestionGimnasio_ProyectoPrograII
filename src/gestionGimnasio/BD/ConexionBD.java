package gestionGimnasio.BD;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public final class ConexionBD {
 
    private static final String URL
            = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=GestionGimnasio;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
 
    private static final String USUARIO
            = "gestionGimnasio_app";
 
    private static final String CLAVE
            = "Gest.Gimnasio#2026";
 
    private ConexionBD() {
    }
 
    public static Connection conectar()
            throws SQLException {
 
        return DriverManager.getConnection(
                URL,
                USUARIO,
                CLAVE
        );
    }
}