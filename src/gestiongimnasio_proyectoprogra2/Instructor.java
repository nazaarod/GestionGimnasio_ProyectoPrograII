package gestiongimnasio_proyectoprogra2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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

        
        // 1. Crear la ventana con un titulo
        JFrame ventana = new JFrame("Mostrar Resumen de Prueba");
        ventana.setSize(400, 200);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null); 

        
        String texto = "<html><div style='text-align: center;'>"
                     + "<h1>Instructor</h1>"
                     + "Identificacion: "+identificacion+"<br>"
                     + "Nombre Completo: "+nombreCompleto+"<br>"
                     + "Telefono: "+telefono+"<br>"
                     + "Edad: "+edad+"<br>"
                     + "correo: "+correo+"<br>"
                     + "</div></html>";

        // 3. Crear la etiqueta que sostiene el texto y centrarlo
        JLabel etiqueta = new JLabel(texto, SwingConstants.CENTER);

        // 4. Meter el texto directo a la ventana
       ventana.add(etiqueta);

        // 5. Hacerla visible
        ventana.setVisible(true);
        
        
        
        
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
