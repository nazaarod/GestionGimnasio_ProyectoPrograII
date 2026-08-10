package gestionGimnasio.BD;
 
import java.sql.Connection;
 
public class PruebaConexion {
 
    public static void main(String[] args) {
 
        try (
                Connection conexion
                        = ConexionBD.conectar()
        ) {
 
            System.out.println(
                    "Conexión realizada correctamente."
            );
 
            System.out.println(
                    "Base de datos: "
                    + conexion.getCatalog()
            );
 
        } catch (Exception ex) {
 
            System.out.println(
                    "No se pudo conectar."
            );
 
            System.out.println(
                    "Detalle: "
                    + ex.getMessage()
            );
        }
    }
}