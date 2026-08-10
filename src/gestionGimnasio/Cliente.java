package gestionGimnasio;

import java.time.LocalDate;

public class Cliente extends Persona {

    
    private LocalDate fechaInscripcion;

    public Cliente() {
        super();
        this.fechaInscripcion = LocalDate.now();
        
    }
    //Constructor
    public Cliente(String identificacion, String nombreCompleto, String telefono, String edad, String correo, LocalDate fechaInscripcion) 
    {
        super(identificacion, nombreCompleto, telefono, edad, correo);
        this.fechaInscripcion = LocalDate.now();

    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        if (fechaInscripcion == null || fechaInscripcion.equals("")) {
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