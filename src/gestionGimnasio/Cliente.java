package gestionGimnasio;

import java.time.LocalDate;

public class Cliente extends Persona {
    
    private LocalDate fechaInscripcion;
    private Membresia membresiaAsignada;
    private Rutina rutinaAsignada;

    public Cliente() {
        super();
        this.fechaInscripcion = LocalDate.now();
        this.membresiaAsignada = null;
    }
    
    //Constructor lleno
    public Cliente(String identificacion, String nombreCompleto, String telefono, String edad, String correo, LocalDate fechaInscripcion, Membresia membresiaAsignada) 
    {
        super(identificacion, nombreCompleto, telefono, edad, correo);
        this.fechaInscripcion = LocalDate.now();
        this.membresiaAsignada = membresiaAsignada;
    }
    
    //comstructor para BD
    public Cliente(String identificacion, String nombreCompleto, String telefono, LocalDate fechaInscripcion, Membresia membresiaAsignada, Rutina rutinaAsignada) 
    {
        super(identificacion, nombreCompleto, telefono);
        this.fechaInscripcion = LocalDate.now();
        this.membresiaAsignada = membresiaAsignada;
        this.rutinaAsignada = rutinaAsignada;
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
    
    public Membresia getMembresiaAsignada() {
        return membresiaAsignada;
    }

    public void setMembresiaAsignada(Membresia membresiaAsignada) {
        this.membresiaAsignada = membresiaAsignada;
    }
    
    public Rutina getRutinaAsignada() {
        return rutinaAsignada;
    }

    public void setRutinaAsignada(Rutina rutinaAsignada) {
        this.rutinaAsignada = rutinaAsignada;
    }

    //Un mostrar resumen temporal por consola, igual se utiliza el override para re utilizar el metodo
    @Override
    public void mostrarResumen() 
    {
        System.out.println("--CLIENTE--");
        System.out.println("Cliente: " + nombre);
        System.out.println("Identificacion: " + identificacion);
        System.out.println("Telefono: " + telefono);
        System.out.println("Fecha de inscripcion: " + fechaInscripcion);
        System.out.println("------------------------------------------------");
    }

    @Override
    public String toString() {
        return identificacion + " - " + nombre + " - " + estado;
    }
}