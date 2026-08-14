package gestionGimnasio;

public class Ejercicio implements Resumible
{
    private String identificacion;
    private String nombre;
    private String dificultad;
    private String descripcion;

    public Ejercicio()
    {
        this.identificacion = "";
        this.nombre = "";
        this.dificultad = "";
        this.descripcion = "";
    }

    public Ejercicio(String identificacion, String nombre, String dificultad, String descripcion)
    {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.descripcion = descripcion;
    }

    
    public String getIdentificacion()
    {
        return identificacion;
    }

    public void setIdentificacion(String identificacion)
    {
        this.identificacion = identificacion;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDificultad()
    {
        return dificultad;
    }

    public void setDificultad(String dificultad)
    {
        this.dificultad = dificultad;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    @Override
    public void mostrarResumen()
    {
        System.out.println(" Ejercicio ");
        System.out.println("Codigo: " + identificacion);
        System.out.println("Nombre: " + nombre);
        System.out.println("Dificultad: " + dificultad);
        System.out.println("Descripcion: " + descripcion);
        System.out.println("------------------------------------------------");
    }

    @Override
    public String toString()
    {
        return "Ejercicio: " + nombre + " Dificultad: " + dificultad;
    }
}
