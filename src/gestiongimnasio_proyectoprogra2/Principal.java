package gestiongimnasio_proyectoprogra2;

public class Principal {

    public static void main(String[] args) {

        Instructor instructor1 = new Instructor("3-456-987", "Marcos Hernandez ", "87-56-74-23", "30", "Marco1001@gmail.com", "Fuerza");
        Cliente cliente1 = new Cliente("3-678-236", "Ana Calvo", "60-45-67-89", "28", "AnaCalvo1@gmail.com ", "04/07/2026");

        instructor1.mostrarResumen();
        System.out.println(instructor1.toString());
        instructor1.inactivar();

        cliente1.mostrarResumen();
        System.out.println(cliente1.toString());
        cliente1.inactivar();

    }
}
