package gestionGimnasio;

/**
 *
 * @author jeank
 */
import java.time.LocalDate;

public class Pago implements Resumible {

    private Cliente cliente;
    private LocalDate fechaPago;
    private double monto;
    private String metodoPago;
    private Usuario usuarioRegistra;

    public Pago() {

        this.metodoPago = "";
        this.monto = 0.0;
        this.fechaPago = LocalDate.now();

    }

    public Pago(Cliente cliente, LocalDate fechaPago, double monto, String metodoPago, Usuario usuarioRegistra) {
        this.cliente = cliente;
        this.fechaPago = LocalDate.now();
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.usuarioRegistra = usuarioRegistra;

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

    public Usuario getUsuarioRegistra() {

        return usuarioRegistra;
    }

    public void setUsuarioRegistra(Usuario usuarioRegistra) {

        this.usuarioRegistra = usuarioRegistra;

    }

    @Override
    public void mostrarResumen() {
        System.out.println("Nombre: " + cliente);
        System.out.println("Fecha: " + fechaPago);
        System.out.println("Monto: " + monto);
        System.out.println("Metodo de pago: " + metodoPago);
        System.out.println("Usuario: " + usuarioRegistra);
        System.out.println("---------------------------------------------------");
    }

    @Override
    public String toString() {
        return " Pago " + "cliente= " + "nombre= " + cliente + "monto= " + monto + "metodoPago= " + metodoPago;
    }

    public double mostrarMonto() {
        return monto;
    }

}
