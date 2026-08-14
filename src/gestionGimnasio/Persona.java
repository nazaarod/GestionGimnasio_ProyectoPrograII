package gestionGimnasio;

//Se utiliza abstract para que no se pueda instanciar la clase, solo implementarla.
public abstract class Persona implements Resumible 
{
    protected String identificacion;
    protected String nombre;
    protected String telefono;
    protected String estado;

    public Persona() {
        this.identificacion = "";
        this.nombre = "Sin nombre";
        this.telefono = "";
        this.estado = "Inactivo";
    }
        
    //Constructor manual de la clase.
    public Persona(String identificacion, String nombre, String telefono, String correo, String estado) 
    {

        if (identificacion == null || identificacion.trim().equals("")) {
            this.identificacion = "";
        } else {
            this.identificacion = identificacion;
        }

        if (nombre == null || nombre.trim().equals("")) {
            this.nombre = "Sin nombre";
        } else {
            this.nombre = nombre;
        }

        this.telefono = telefono;
        this.estado = estado;
    }
    
    //Constructor para BD
    public Persona(String identificacion, String nombre, String telefono) 
    {

        if (identificacion == null || identificacion.trim().equals("")) {
            this.identificacion = "";
        } else {
            this.identificacion = identificacion;
        }

        if (nombre == null || nombre.trim().equals("")) {
            this.nombre = "Sin nombre";
        } else {
            this.nombre = nombre;
        }

        this.telefono = telefono;
    }
    public Persona(String identificacion, String nombre, String telefono, String correo) 
    {

        if (identificacion == null || identificacion.trim().equals("")) {
            this.identificacion = "";
        } else {
            this.identificacion = identificacion;
        }

        if (nombre == null || nombre.trim().equals("")) {
            this.nombre = "Sin nombre";
        } else {
            this.nombre = nombre;
        }

        this.telefono = telefono;
        this.estado = "Inactivo";
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {

        if (identificacion == null || identificacion.trim().equals("")) {
            System.out.println("La identificacion no es valida");
        } else {
            this.identificacion = identificacion;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre == null || nombre.trim().equals("")) {
            System.out.println("El nombre no es valido");
        } else {
            this.nombre = nombre;
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {

        if (telefono == null || telefono.trim().equals("")) {
            System.out.println("El telefono no es válido");
        } else {
            this.telefono = telefono;
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}