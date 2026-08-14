package gestionGimnasio;

import javax.swing.*;

public class Instructor extends Persona 
{ 
    private String especialidad;
    //Constructor
    public Instructor(String identificacion, String nombre, String telefono, String especialidad, String estado) 
    {

        super(identificacion, nombre, telefono, estado);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() 
    {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) 
    {
        this.especialidad = especialidad;
    }
    
    //Overrride para utilizar el metodo con modificaciones
    @Override
    public void mostrarResumen() 
    {
        //Creacion de la ventana
        JFrame ventana = new JFrame("Mostrar Resumen de Prueba");
        ventana.setSize(400, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null); 

        //Guardado de texto en la variable "texto"
        String texto = "<html><div style='text-align: center;'>"
                     + "<h1>Instructor</h1>"
                     + "Identificacion: "+identificacion+"<br>"
                     + "Telefono: "+telefono+"<br>"
                     + "</div></html>";

        //Se proceden a enviar los cambios a la etiqueta y luego se muestra en la pantalla
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);

        //  Meter el texto directo a la ventana
        ventana.add(etiqueta);

        // Hacerla visible
        ventana.setVisible(true);
    }

    @Override
    public String toString() {
        return "Instructor especialidad: " + especialidad;
    }

}
