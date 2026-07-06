package gestiongimnasio_proyectoprogra2;

public class Cliente extends Persona {

    private String fechaInscripcion;

    public Cliente() {
        super();
        this.fechaInscripcion = "Sin fecha";

    }

    public Cliente(String identificacion, String nombreCompleto, String telefono, String edad, String correo, String fechaInscripcion) {
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

    public void inactivar() {
        this.estado = false;
        System.out.println("Cliente " + nombreCompleto + " inactivado correctamente");
    }

    @Override
    public void mostrarResumen() {

        System.out.println("--CLIENTE--");
        System.out.println("Cliente: " + nombreCompleto);
        System.out.println("Identificación: " + identificacion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Correo: " + correo);
        System.out.println("Fecha de inscripción: " + fechaInscripcion);
        System.out.println("Estado: " + estado);

        System.out.println("------------------------------------------------");

    }

    @Override
    public String toString() {
        return identificacion + " - " + nombreCompleto + " - " + estado;
    }

}
