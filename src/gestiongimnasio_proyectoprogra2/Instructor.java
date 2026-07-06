package gestiongimnasio_proyectoprogra2;

public class Instructor extends Persona {

    private String especialidad;

    public Instructor(String identificacion, String nombreCompleto, String telefono,
            String edad, String correo, String especialidad) {

        super(identificacion, nombreCompleto, telefono, edad, correo);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public void mostrarResumen() {

        System.out.println("--INSTRUCTOR--");
        System.out.println("Identificacion: " + identificacion);
        System.out.println("Instructor: " + nombreCompleto);
        System.out.println("Telefono: " + telefono);
        System.out.println("Edad: " + edad);
        System.out.println("Correo: " + correo);
        System.out.println("------------------------------------------------");
    }

    @Override
    public String toString() {
        return "Instructor especialidad: " + especialidad;
    }

}
