package gestionGimnasio;


public class Utilidades 
{
    public static boolean ValidarAcceso(Usuario u)
    {
        for(Usuario uu: DatosRep.USUARIOS.obtenerTodos())
        {
            if(u.getNombreUsuario().equals(uu.getNombreUsuario()) &&u.getContrasena().equals(uu.getContrasena()))
            {
                return true;
            }
        }
        return false;
    }
}