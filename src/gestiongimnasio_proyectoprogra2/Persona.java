package gestiongimnasio_proyectoprogra2;

//Se utiliza abstract para que no se pueda instanciar la clase, solo implementarla.
public abstract class Persona implements Resumible 
{
    protected String identificacion;
    protected String nombreCompleto;
    protected String telefono;
    protected String edad;
    protected String correo;
    protected boolean estado;

    public Persona() {
        this.identificacion = "";
        this.nombreCompleto = "Sin nombre";
        this.telefono = "";
        this.edad = "";
        this.correo = "";
        this.estado = false;
    }
        
    //Constructor manual de la clase.
    public Persona(String identificacion, String nombreCompleto, String telefono, String edad, String correo) 
    {

        if (identificacion == null || identificacion.trim().equals("")) {
            this.identificacion = "";
        } else {
            this.identificacion = identificacion;
        }

        if (nombreCompleto == null || nombreCompleto.trim().equals("")) {
            this.nombreCompleto = "Sin nombre";
        } else {
            this.nombreCompleto = nombreCompleto;
        }

        this.telefono = telefono;
        this.edad = edad;
        this.correo = correo;
        this.estado = true;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {

        if (nombreCompleto == null || nombreCompleto.trim().equals("")) {
            System.out.println("El nombre no es valido");
        } else {
            this.nombreCompleto = nombreCompleto;
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {

        if (correo == null || correo.trim().equals("")) {
            System.out.println("El correo no es valido");
        } else {
            this.correo = correo;
        }
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}