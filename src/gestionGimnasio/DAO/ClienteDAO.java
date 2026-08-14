package gestionGimnasio.DAO;

import gestionGimnasio.BD.ConexionBD;
import gestionGimnasio.Cliente;
import gestionGimnasio.Membresia;
import gestionGimnasio.Rutina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO 
{
    public ArrayList<Cliente> listar() {

        ArrayList<Cliente> clientes
                = new ArrayList<Cliente>();

        String sql
                = "SELECT "
                + "c.Identificacion AS IdCliente, "
                + "c.Nombre AS NombreCliente, "
                + "c.Telefono, "
                + "c.FechaInscripcion, "
                + "c.Estado, "
                + "m.IdMembresia, "
                + "m.nombre AS NombreMembresia, "
                + "r.IdRutina, "
                + "r.Nombre AS NombreRutina "
                + "FROM dbo.Clientes c "
                + "INNER JOIN dbo.Membresia m "
                + "ON m.Identificacion "
                + " = c.IdentificacionMembresia "
                + "INNER JOIN dbo.Rutina r "
                + "ON r.Identificacion "
                + " = c.IdentificacionRutina "
                + "ORDER BY c.Nombre";

        try (
                Connection conexion
                        = ConexionBD.conectar();

                PreparedStatement sentencia
                        = conexion.prepareStatement(sql);

                ResultSet resultado
                        = sentencia.executeQuery()
        ) {

            while (resultado.next()) {

                Membresia membresiaAsignada
                        = new Membresia(
                                resultado.getString(
                                        "IdMembresia"
                                ),
                                resultado.getString(
                                        "NombreMembresia"
                                )
                        );

                // constructor dentro de clase Rutina con solo esos dos atributos
                Rutina rutinaAsignada
                        = new Rutina(
                                resultado.getString(
                                        "IdRutina"
                                ),
                                resultado.getString(
                                        "NombreRutina"
                                )
                        );
                
                Cliente cliente
                        = new Cliente(
                                resultado.getString(
                                        "IdCliente"
                                ),
                                resultado.getString(
                                        "NombreCliente"
                                ),
                                resultado.getString(
                                        "Telefono"
                                ),
                                resultado.getDate(
                                        "FechaInscripcion"
                                ).toLocalDate(),
                                
                                membresiaAsignada,
                                rutinaAsignada
                        );

                clientes.add(cliente);
            }

        } catch (Exception ex) {

            System.out.println(
                    "Error al listar clientes: "
                    + ex.getMessage()
            );
        }

        return clientes;
    }
    
    public boolean insertar(
            Cliente cliente
    ) {

        String sql
                = "INSERT INTO dbo.Clientes "
                + "(Identificacion, Nombre, Telefono, FechaInscripcion, "
                + "IdentificacionMembresia, IdentificacionRutina) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conexion
                        = ConexionBD.conectar();

                PreparedStatement sentencia
                        = conexion.prepareStatement(sql)
        ) {

            
            sentencia.setString(
                    1,
                    cliente.getIdentificacion()
            );
            
            sentencia.setString(
                    2,
                    cliente.getNombre()
            );

            sentencia.setString(
                    3,
                    cliente.getTelefono()
            );
            
            sentencia.setDate(
                    4,
                    java.sql.Date.valueOf(
                            cliente.getFechaInscripcion()
                    )
            );

            sentencia.setString(
                    5,
                    cliente.getMembresiaAsignada()
                            .getCodigoMembresia()
            );
            
            sentencia.setString(
                    6,
                    cliente.getRutinaAsignada()
                            .getIdentificacion()
            );

            return sentencia.executeUpdate() > 0;

        } catch (Exception ex) {

            System.out.println(
                    "Error al insertar cliente: "
                    + ex.getMessage()
            );

            return false;
        }
    }
}
  