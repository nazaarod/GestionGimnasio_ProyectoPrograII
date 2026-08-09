/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionGimnasio.GUI;

import gestionGimnasio.Pago;
import gestionGimnasio.Cliente;
import gestionGimnasio.Usuario;
import gestionGimnasio.Membresia;
import gestionGimnasio.DatosRep;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author jeank
 */
public class FrmPagos extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmPagos.class.getName());
    private int filaSeleccionada = -1;

    private final ArrayList<Cliente> clientesDisponibles = new ArrayList<>();
    private final ArrayList<Usuario> usuariosDisponibles = new ArrayList<>();

    /**
     * Creates new form FrmPagos
     */
    public FrmPagos() {
        initComponents();

        configurarSpinnerMonto();
        configurarTabla();
        cargarClientes();
        cargarUsuarios();
        cargarMetodosPago();
        cargarTabla();
        limpiarFormulario();

        setLocationRelativeTo(null);

    }

    private void configurarSpinnerMonto() {

        spnMonto.setModel(new SpinnerNumberModel(
                0.0,
                0.0,
                10000000.0,
                500.0));

        spnMonto.setEditor(new JSpinner.NumberEditor(spnMonto, "0.00"));
    }

    private void configurarTabla() {

        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{
                    "Nombre",
                    "Membresia",
                    "Usuario",
                    "Fecha",
                    "Metodo Pago",
                    "Monto"
                },
                0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tblPagos.setModel(modelo);
        tblPagos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPagos.getTableHeader().setReorderingAllowed(false);
    }

    private void cargarClientes() {

        clientesDisponibles.clear();
        cmbCliente.removeAllItems();

//        clientesDisponibles.addAll(DatosRep.CLIENTES.obtenerTodos());
        for (Cliente cliente : clientesDisponibles) {
            cmbCliente.addItem(cliente.getIdentificacion() + " - " + cliente.getNombreCompleto());
        }
    }

    private void cargarUsuarios() {

        usuariosDisponibles.clear();
        cmbUsuario.removeAllItems();

        usuariosDisponibles.addAll(DatosRep.USUARIOS.obtenerTodos());

        for (Usuario usuario : usuariosDisponibles) {
            cmbUsuario.addItem(usuario.getNombreUsuario());
        }
    }

    private void cargarMetodosPago() {

        cmbMetodoPago.removeAllItems();
        cmbMetodoPago.addItem("Efectivo");
        cmbMetodoPago.addItem("Tarjeta");
        cmbMetodoPago.addItem("Transferencia");
        cmbMetodoPago.addItem("Sinpe Movil");
    }

    private void cargarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) tblPagos.getModel();
        modelo.setRowCount(0);

        for (Pago pago : DatosRep.PAGOS.obtenerTodos()) {
            String nombreCliente = pago.getCliente() == null ? "No registrado" : pago.getCliente().getNombreCompleto();
            String nombreUsuario = pago.getUsuarioRegistra() == null ? "No registrado" : pago.getUsuarioRegistra().getNombreUsuario();

            modelo.addRow(new Object[]{
                nombreCliente,
                //                nombreMembresia,
                nombreUsuario,
                pago.getFechaPago(),
                pago.getMetodoPago(),
                String.format("₡ %.2f", pago.getMonto())
            });
        }
    }

    private Pago crearPagoDesdeFormulario() {

        Cliente clienteSeleccionado = clientesDisponibles.get(cmbCliente.getSelectedIndex());
        Usuario usuarioSeleccionado = usuariosDisponibles.get(cmbUsuario.getSelectedIndex());
        String metodoPagoSeleccionado = (String) cmbMetodoPago.getSelectedItem();

        double monto = ((Number) spnMonto.getValue()).doubleValue();

        return new Pago(
                clienteSeleccionado,
                LocalDate.now(),
                monto,
                metodoPagoSeleccionado,
                usuarioSeleccionado
        );
    }

    private boolean validarCampos() {

        if (cmbCliente.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cmbUsuario.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cmbMetodoPago.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un metodo de pago.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        double monto = ((Number) spnMonto.getValue()).doubleValue();

        if (monto <= 0) {
            JOptionPane.showMessageDialog(this, "El monto debe ser mayor que cero.", "Monto invalido", JOptionPane.WARNING_MESSAGE);
            spnMonto.requestFocus();
            return false;
        }

        return true;
    }

    private int buscarIndiceCliente(Cliente cliente) {

        if (cliente == null) {
            return -1;
        }

        for (int i = 0; i < clientesDisponibles.size(); i++) {

            if (cliente.getIdentificacion().equals(clientesDisponibles.get(i).getIdentificacion())) {
                return i;
            }
        }
        return -1;
    }

    private int buscarIndiceUsuario(Usuario usuario) {

        if (usuario == null) {
            return -1;
        }

        for (int i = 0; i < usuariosDisponibles.size(); i++) {

            if (usuario.getNombreUsuario().equals(usuariosDisponibles.get(i).getNombreUsuario())) {
                return i;
            }
        }

        return -1;
    }

    private void cargarPagosSeleccionada() {

        int fila = tblPagos.getSelectedRow();

        if (fila < 0) {
            return;
        }

        filaSeleccionada = fila;

        Pago pago = DatosRep.PAGOS.obtener(filaSeleccionada);

        if (pago == null) {
            return;
        }

        spnMonto.setValue(pago.getMonto());
        cmbMetodoPago.setSelectedItem(pago.getMetodoPago());

        int indiceCliente = buscarIndiceCliente(pago.getCliente());
        if (indiceCliente >= 0) {
            cmbCliente.setSelectedIndex(indiceCliente);
        }

        int indiceUsuario = buscarIndiceUsuario(pago.getUsuarioRegistra());
        if (indiceUsuario >= 0) {
            cmbUsuario.setSelectedIndex(indiceUsuario);
        }
    }

    private void limpiarFormulario() {

        txtMembresia.setText("");
        txtFechaPago.setText(LocalDate.now().toString());
        spnMonto.setValue(0.0);

        if (cmbCliente.getItemCount() > 0) {
            cmbCliente.setSelectedIndex(0);
        }

        if (cmbUsuario.getItemCount() > 0) {
            cmbUsuario.setSelectedIndex(0);
        }

        if (cmbMetodoPago.getItemCount() > 0) {
            cmbMetodoPago.setSelectedIndex(0);
        }

        tblPagos.clearSelection();
        filaSeleccionada = -1;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spnMonto = new javax.swing.JSpinner();
        cmbUsuario = new javax.swing.JComboBox<>();
        lblUsuario = new javax.swing.JLabel();
        lblMetodoPago = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        cmbCliente = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPagos = new javax.swing.JTable();
        lblMembresia = new javax.swing.JLabel();
        txtMembresia = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        txtFechaPago = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(875, 576));

        cmbUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblUsuario.setText("Usuario:");

        lblMetodoPago.setText("Metodo de Pago:");
        lblMetodoPago.setRequestFocusEnabled(false);

        lblCliente.setText("Cliente:");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        cmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setText("Administracion de Pagos");

        tblPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPagosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPagos);

        lblMembresia.setText("Membresia:");

        lblFecha.setText("Fecha:");

        lblMonto.setText("Monto:");

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMetodoPago))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(398, 398, 398))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnNuevo)
                        .addGap(76, 76, 76)
                        .addComponent(btnGuardar)
                        .addGap(91, 91, 91)
                        .addComponent(btnActualizar)
                        .addGap(80, 80, 80)
                        .addComponent(btnEliminar)
                        .addGap(69, 69, 69)
                        .addComponent(btnCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(lblTitulo)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFecha)
                            .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCliente)
                            .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsuario)
                            .addComponent(lblMetodoPago)
                            .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMembresia)
                        .addComponent(lblMonto)
                        .addComponent(spnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCerrar))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarFormulario();
        cargarTabla();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!validarCampos()) {
            return;
        }

        Pago pago = crearPagoDesdeFormulario();
        DatosRep.PAGOS.agregar(pago);

        cargarTabla();
        limpiarFormulario();

        JOptionPane.showMessageDialog(this, "Pago registrado correctamente.", "Registro realizado", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un pago en la tabla.", "Pago no seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!validarCampos()) {
            return;
        }

        Pago pagoActualizado = crearPagoDesdeFormulario();
        boolean actualizado = DatosRep.PAGOS.actualizar(filaSeleccionada, pagoActualizado);

        if (actualizado) {
            cargarTabla();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Pago actualizado correctamente.", "Actualizacion realizada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void tblPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPagosMouseClicked
        cargarPagosSeleccionada();
    }//GEN-LAST:event_tblPagosMouseClicked

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un pago en la tabla.", "Pago no seleccionado", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pago pago = DatosRep.PAGOS.obtener(filaSeleccionada);
        if (pago == null) {
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar el pago?"
                + pago.getCliente() + "-" + pago.getCliente().getNombreCompleto()
                + "?",
                "Confirmar eliminacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado = DatosRep.PAGOS.eliminar(filaSeleccionada);

        if (eliminado) {
            cargarTabla();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Pago eliminado correctamente.", "Eliminacion realizada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el pago.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmPagos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JComboBox<String> cmbUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMembresia;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JSpinner spnMonto;
    private javax.swing.JTable tblPagos;
    private javax.swing.JTextField txtFechaPago;
    private javax.swing.JTextField txtMembresia;
    // End of variables declaration//GEN-END:variables
}
