package gestionGimnasio;

/**
 *
 * @author jeank
 */
import java.time.LocalDate;

public class Pago implements Resumible {

    private Cliente cliente;
    private Membresia membresia;
    private LocalDate fechaPago;
    private double monto;
    private String metodoPago;

    public Pago() {

        this.metodoPago = "";
        this.monto = 0.0;
        this.fechaPago = LocalDate.now();

    }

    public Pago(Cliente cliente, Membresia membresia, LocalDate fechaPago, String metodoPago, double monto) {

        this.cliente = cliente;
        this.membresia = membresia;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodoPago = metodoPago;

    }

    public Cliente getCliente() {

        return cliente;
    }

    public void setCliente(Cliente cliente) {

        if (cliente == null) {
            System.out.println(" El cliente no puede ser null ");

        } else {

            this.cliente = cliente;
        }
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        if (membresia == null) {
            System.out.println(" La membresia no puede ser null ");

        } else {

            this.membresia = membresia;
        }
    }

    public LocalDate getFechaPago() {

        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {

        this.fechaPago = fechaPago;

    }

    public double getMonto() {

        return monto;
    }

    public void setMonto(double monto) {

        if (monto < 0) {
            System.out.println(" El monto no puede ser negativo ");

        } else {

            this.monto = monto;
        }

    }

    public String getMetodoPago() {

        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {

        this.metodoPago = metodoPago;

    }

    @Override
    public void mostrarResumen() {
        System.out.println("Cliente: " + cliente);
        System.out.println("Membresia: " + membresia);
        System.out.println("Fecha: " + fechaPago);
        System.out.println("Monto: " + monto);
        System.out.println("Metodo de pago: " + metodoPago);
        System.out.println("---------------------------------------------------");
    }

    @Override
    public String toString() {
        return " Pago " + "cliente= " + cliente + "membresia=" + membresia + "monto= " + monto + "metodoPago= " + metodoPago;
    }

    public double mostrarMonto() {
        return monto;
    }

}
