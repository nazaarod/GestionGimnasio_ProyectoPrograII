
package gestionGimnasio;

/**
 *
 * @author fabri
 */
public class Usuario extends Persona
{
    private String nombreUsuario;
    private String contrasena;
    private String rol;
    private String estadoUss;

    
    public Usuario(String nombreUsuario, String contrasena, String rol, String estadoUss) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.estadoUss = estadoUss;
    }
    
    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstadoUss() {
        return estadoUss;
    }

    public void setEstadoUss(String estadoUss) {
        this.estadoUss = estadoUss;
    }
    
    
    
    
    
    
    @Override
    public void mostrarResumen() 
    {
        System.out.println(" Usuario ");
        System.out.println("Usuario: " + nombreUsuario);
        System.out.println("Contrasena: " + contrasena);
        System.out.println("Rol: " + rol);
        System.out.println("Estado Usuario: " + estadoUss);
        System.out.println("------------------------------------------------");

    }

    @Override
    public String toString() {
        return "Usuario: "+nombreUsuario +" Contrasena: "+contrasena +" Rol: "+rol +" Estado: "+estadoUss;
    }
    
    
    
    
    
}
