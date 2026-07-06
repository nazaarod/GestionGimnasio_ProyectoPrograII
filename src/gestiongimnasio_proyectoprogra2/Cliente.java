package gestiongimnasio_proyectoprogra2;

public class Cliente extends Persona {

    private String fechaInscripcion;

    public Cliente() {
        super();
        this.fechaInscripcion = "Sin fecha";
        
    }
    //Constructor
    public Cliente(String identificacion, String nombreCompleto, String telefono, String edad, String correo, String fechaInscripcion) 
    {
        super(identificacion, nombreCompleto, telefono, edad, correo);
        this.fechaInscripcion = fechaInscripcion;

    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(String fechaInscripcion) {
        if (fechaInscripcion == null || fechaInscripcion.trim().equals("")) {
            System.out.println("La fecha no es válida");
        } else {
            this.fechaInscripcion = fechaInscripcion;
        }
    }

    //Un mostrar resumen temporal por consola, igual se utiliza el override para re utilizar el metodo
    @Override
    public void mostrarResumen() 
    {
        System.out.println("--CLIENTE--");
        System.out.println("Cliente: " + nombreCompleto);
        System.out.println("Identificacion: " + identificacion);
        System.out.println("Telefono: " + telefono);
        System.out.println("Correo: " + correo);
        System.out.println("Fecha de inscripcion: " + fechaInscripcion);
        System.out.println("------------------------------------------------");
    }

    @Override
    public String toString() {
        return identificacion + " - " + nombreCompleto + " - " + estado;
    }

}