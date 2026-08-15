/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionGimnasio.GUI;

import gestionGimnasio.Pago;
import gestionGimnasio.Cliente;
import gestionGimnasio.Membresia;
import gestionGimnasio.DatosRep;
import java.time.LocalDate;
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

    private final ArrayList<Membresia> membresiasDisponibles = new ArrayList<Membresia>();
    private final ArrayList<Cliente> clientesDisponibles = new ArrayList<Cliente>();

    /**
     * Creates new form FrmPagos
     */
    public FrmPagos() {

        initComponents();

        configurarSpinnerMonto();
        configurarTabla();
        cargarTabla();
        cargarClientes();
        cargarMetodosPago();
        cargarMembresias();

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
                    "Cliente",
                    "Membresia",
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

        tblPagos.getColumnModel().getColumn(0).setPreferredWidth(150);
        tblPagos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblPagos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblPagos.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblPagos.getColumnModel().getColumn(4).setPreferredWidth(150);
    }

    private void cargarClientes() {

        clientesDisponibles.clear();
        cmbCliente.removeAllItems();

        clientesDisponibles.addAll(DatosRep.CLIENTES.obtenerTodos());

        for (Cliente cliente : clientesDisponibles) {
            cmbCliente.addItem(cliente.getIdentificacion() + " - " + cliente.getNombre());
        }

    }

    private void cargarMembresias() {

        membresiasDisponibles.clear();
        cmbMembresia.removeAllItems();

        membresiasDisponibles.addAll(DatosRep.MEMBRESIAS.obtenerTodos());

        for (Membresia membresia : membresiasDisponibles) {
            cmbMembresia.addItem(membresia.getCodigoMembresia() + " - " + membresia.getNombrePlan());

        }
    }

    private void cargarMetodosPago() {

        cmbMetodoPago.removeAllItems();
        cmbMetodoPago.addItem("Tarjeta");
        cmbMetodoPago.addItem("Efectivo");
        cmbMetodoPago.addItem("Transferencia");
        cmbMetodoPago.addItem("Sinpe Movil");
        cmbMetodoPago.addItem("Paypal");
    }

    private void cargarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) tblPagos.getModel();
        modelo.setRowCount(0);

        for (Pago pago : DatosRep.PAGOS.obtenerTodos()) {

            String nombreCliente = pago.getCliente() == null ? "No registrado" : pago.getCliente().getNombre();
            String nombreMembresia = pago.getMembresia() == null ? "No registrada" : pago.getMembresia().getNombrePlan();

            modelo.addRow(new Object[]{
                nombreCliente,
                nombreMembresia,
                pago.getFechaPago(),
                pago.getMetodoPago(),
                String.format("₡ %.2f", pago.getMonto())
            });
        }
    }

    private Pago crearPagoDesdeFormulario() {

        Cliente clienteSeleccionado = clientesDisponibles.get(cmbCliente.getSelectedIndex());
        Membresia membresiaSeleccionado = membresiasDisponibles.get(cmbMembresia.getSelectedIndex());
        String metodoPagoSeleccionado = (String) cmbMetodoPago.getSelectedItem();

        double monto = ((Number) spnMonto.getValue()).doubleValue();

        return new Pago(
                clienteSeleccionado,
                membresiaSeleccionado,
                LocalDate.now(),
                metodoPagoSeleccionado,
                monto
        );
    }

    private boolean validarCampos() {

        if (cmbCliente.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (cmbMetodoPago.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un metodo de pago.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (cmbMembresia.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una membresia.", "Dato requerido", JOptionPane.WARNING_MESSAGE);
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

    private int buscarIndiceMembresia(Membresia membresia) {
        if (membresia == null) {
            return -1;
        }

        for (int i = 0; i < membresiasDisponibles.size(); i++) {

            if (membresia.getCodigoMembresia().equals(membresiasDisponibles.get(i).getCodigoMembresia())) {
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
        int indiceMembresia = buscarIndiceMembresia(pago.getMembresia());
        if (indiceMembresia >= 0) {
            cmbMembresia.setSelectedIndex(indiceMembresia);
        }
    }

    private void limpiarFormulario() {

        txtFechaPago.setText(LocalDate.now().toString());

        if (cmbCliente.getItemCount() > 0) {
            cmbCliente.setSelectedIndex(0);
        }

        if (cmbMetodoPago.getItemCount() > 0) {
            cmbMetodoPago.setSelectedIndex(0);
        }

        if (cmbMembresia.getItemCount() > 0) {
            cmbMembresia.setSelectedIndex(0);
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
        lblFecha = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        txtFechaPago = new javax.swing.JTextField();
        btnEliminar = new javax.swing.JButton();
        cmbMembresia = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestionGimnasio - Gestión de Pagos");

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

        cmbMembresia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(lblMetodoPago)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCliente, 0, 191, Short.MAX_VALUE)
                            .addComponent(txtFechaPago, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(cmbMetodoPago, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(btnActualizar)
                        .addGap(63, 63, 63)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTitulo)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCliente)
                            .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMembresia)
                            .addComponent(cmbMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMonto)
                            .addComponent(spnMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMetodoPago)
                            .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFecha)
                        .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCerrar)
                    .addComponent(btnNuevo))
                .addGap(31, 31, 31))
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
                + pago.getCliente() + "-" + pago.getCliente().getNombre()
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
    private javax.swing.JComboBox<String> cmbMembresia;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMembresia;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JSpinner spnMonto;
    private javax.swing.JTable tblPagos;
    private javax.swing.JTextField txtFechaPago;
    // End of variables declaration//GEN-END:variables
}
