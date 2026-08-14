package gestionGimnasio;

import java.util.ArrayList;

public class Rutina implements Resumible
{
    private String identificacion;
    private String nombre;
    private String descripcion;
    private ArrayList<Ejercicio> ejerciciosAsociados;
    private Instructor instructor;
    private boolean estado;

    // sin descripcion
    public Rutina(String identificacion, String nombre, ArrayList<Ejercicio> ejerciciosAsociados, Instructor instructor, boolean estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.ejerciciosAsociados = ejerciciosAsociados;
        this.instructor = instructor;
        this.estado = estado;
    }
    
    // se usa para la base de datos
    public Rutina (String identificacion, String nombre){
        this.identificacion = identificacion;
        this.nombre = nombre;
    }    
    
    //completo 
    public Rutina(String identificacion, String nombre, String descripcion, ArrayList<Ejercicio> ejerciciosAsociados, Instructor instructor, boolean estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ejerciciosAsociados = ejerciciosAsociados;
        this.instructor = instructor;
        this.estado = estado;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Ejercicio> getEjerciciosAsociados() {
        return ejerciciosAsociados;
    }

    public void setEjerciciosAsociados(ArrayList<Ejercicio> ejerciciosAsociados) {
        this.ejerciciosAsociados = ejerciciosAsociados;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public void mostrarResumen() 
    {
        System.out.println("--Rutina--");
        System.out.println("Identificacion: " + identificacion);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Instructor: " + instructor);
        System.out.println("------------------------------------------------");
    }
    
    
    @Override
    public String toString() {
        return "Rutina{" + "identificacion=" + identificacion + ", nombre=" + nombre + ", descripcion=" + descripcion + ", ejerciciosAsociados=" + ejerciciosAsociados + ", instructor=" + instructor + ", estado=" + estado + '}';
    }
    
    
    
    
}
