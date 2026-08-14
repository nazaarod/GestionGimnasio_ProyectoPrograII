/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gestionGimnasio.GUI;

import gestionGimnasio.DatosRep;
import gestionGimnasio.Membresia;
import javax.swing.JOptionPane;
import gestionGimnasio.Usuario;
import gestionGimnasio.Utilidades;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ideapad S145
 */
public class FrmMembresias extends javax.swing.JFrame {
    private int filaSeleccionada = -1;
    Usuario usuarioActivo;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmMembresias.class.getName());

    /**
     * Creates new form FrmMembresias
     */
    public FrmMembresias(Usuario u) {
        
        usuarioActivo= u;
        
        initComponents();
        setLocationRelativeTo(null);
        configurarTabla();
        cargarEstados();
        cargarTabla();
        limpiarFormulario();  
    }
    
    private void configurarTabla(){
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{
                    "Codigo",
                    "Nombre del plan",
                    "Vigencia",
                    "Precio",
                    "Descripcion",
                    "Estado"
                }, 0        
        ){        
            @Override
            public boolean isCellEditable(int fila, int columna){
                return false;
            }
        };    
        tblMembresias.setModel(modelo);
        tblMembresias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }    
    private void cargarEstados(){
        cmbEstado.removeAllItems();
        cmbEstado.addItem("Activo");
        cmbEstado.addItem("Inactivo");
    }    
    private void cargarTabla(){
        DefaultTableModel modelo = (DefaultTableModel)tblMembresias.getModel();
        modelo.setRowCount(0);
        for (Membresia membresia : DatosRep.MEMBRESIAS.obtenerTodos()){
            modelo.addRow(new Object[]{
                membresia.getCodigoMembresia(),
                membresia.getNombrePlan(),
                membresia.getVigenciaDias(),
                String.format("%.2f", membresia.getPrecioPlan()),
                membresia.getDescripcionPlan(),
                membresia.getEstado()
            });        
        }    
    }    
    
    private boolean validarCampos(){
        if (txtCodigoMembresia.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Digite el codigo de la membresia", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            txtCodigoMembresia.requestFocus();
            return false;
        }    
        if (txtNombrePlan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Digite el plan requerido", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            txtNombrePlan.requestFocus();
            return false;
        }
        if (txtVigenciaDias.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Ingrese la vigencia en dias", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            txtVigenciaDias.requestFocus();
            return false;
        }
        if (txtPrecio.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Digite el precio del plan", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return false;
        }
        if (txtDescripcionPlan.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this,"Digite la descripcion del plan", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            txtDescripcionPlan.requestFocus();
            return false;
        }
        if (cmbEstado.getSelectedIndex() < 0){
            JOptionPane.showMessageDialog(this,"Debe seleccionar el estado", "Dato requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }    
        return true;
    }    
    
    private Membresia crearMembresiaDesdeFormulario(){
        return new Membresia(
                txtCodigoMembresia.getText().trim(),
                txtNombrePlan.getText().trim(),
                Integer.parseInt(txtVigenciaDias.getText().trim()),
                Double.parseDouble(txtPrecio.getText().trim()),
                txtDescripcionPlan.getText().trim(),
                cmbEstado.getSelectedItem().toString()
        );        
    }    
    private void cargarMembresiaSeleccionada(){
        filaSeleccionada = tblMembresias.getSelectedRow();
        if(filaSeleccionada < 0){
            return;
        }        
        Membresia membresia = DatosRep.MEMBRESIAS.obtener(filaSeleccionada);
        if (membresia == null){
            return;
        }    
        txtCodigoMembresia.setText(membresia.getCodigoMembresia());
        txtNombrePlan.setText(membresia.getNombrePlan());
        txtVigenciaDias.setText(String.valueOf(membresia.getVigenciaDias()));
        txtPrecio.setText(String.valueOf(membresia.getPrecioPlan()));
        txtDescripcionPlan.setText(membresia.getDescripcionPlan());
        cmbEstado.setSelectedItem(membresia.getEstado());
    }    
    
    private void limpiarFormulario(){
        txtCodigoMembresia.setText("");
        txtNombrePlan.setText("");
        txtVigenciaDias.setText("");
        txtPrecio.setText("");
        txtDescripcionPlan.setText("");
        if (cmbEstado.getItemCount() > 0){
            cmbEstado.setSelectedIndex(0);
        }    
        tblMembresias.clearSelection();
        filaSeleccionada = -1;
        txtCodigoMembresia.requestFocus();
    }           

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNombrePlan = new javax.swing.JLabel();
        txtNombrePlan = new javax.swing.JTextField();
        txtCodigoMembresia = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        txtVigenciaDias = new javax.swing.JTextField();
        lblVigenciaDias = new javax.swing.JLabel();
        txtDescripcionPlan = new javax.swing.JTextField();
        lblDescripcionPlan = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMembresias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GestionGimnasio - Gestión de Membresias");

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setText("Administracion de Membresias");

        lblNombrePlan.setText("Nombre del plan:");

        lblCodigo.setText("Codigo:");

        lblVigenciaDias.setText("Vigencia:");

        lblDescripcionPlan.setText("Descripcion del plan:");

        lblPrecio.setText("Precio:");

        lblEstado.setText("Estado:");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGuardar.setText("Guardar:");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnActualizar.setText("Actualizar:");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnEliminar.setText("Eliminar:");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnNuevo.setText("Nuevo:");
        btnNuevo.setToolTipText("");
        btnNuevo.addActionListener(this::btnNuevoActionPerformed);

        btnCerrar.setText("Cerrar:");
        btnCerrar.addActionListener(this::btnCerrarActionPerformed);

        tblMembresias.setModel(new javax.swing.table.DefaultTableModel(
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
        tblMembresias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMembresiasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblMembresias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombrePlan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVigenciaDias, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescripcionPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEstado))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcionPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombrePlan, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVigenciaDias, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNuevo)
                        .addGap(36, 36, 36)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnEliminar)
                        .addGap(53, 53, 53)
                        .addComponent(btnCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(lblTitulo)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodigo))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombrePlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombrePlan))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblVigenciaDias)
                            .addComponent(txtVigenciaDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDescripcionPlan)
                            .addComponent(txtDescripcionPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPrecio))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstado)
                            .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnGuardar)
                            .addComponent(btnActualizar))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar)
                            .addComponent(btnCerrar)))
                    .addComponent(jScrollPane1))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("GestionGimnasio - Gestión de Membresias");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        if(!Utilidades.ValidarPermiso(usuarioActivo))
        {
            JOptionPane.showMessageDialog(this, "No tiene los permisos necesarios", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            return;
        }
        limpiarFormulario();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(!Utilidades.ValidarPermiso(usuarioActivo))
        {
            JOptionPane.showMessageDialog(this, "No tiene los permisos necesarios", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validarCampos()){
            return;
        }    
        Membresia membresia = crearMembresiaDesdeFormulario();
        DatosRep.MEMBRESIAS.agregar(membresia);
        cargarTabla();
        limpiarFormulario();
        JOptionPane.showMessageDialog(this, "La membresia fue registrada correctamente" );
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if(!Utilidades.ValidarPermiso(usuarioActivo))
        {
            JOptionPane.showMessageDialog(this, "No tiene los permisos necesarios", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (filaSeleccionada < 0){
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione una membresia en la tabla"
            );        
            return;
        }        
        if (!validarCampos()){
            return;
        }    
        Membresia membresiaAct = crearMembresiaDesdeFormulario();
        boolean actualizado = DatosRep.MEMBRESIAS.actualizar(
                filaSeleccionada,
                membresiaAct
        );        
        
        if (actualizado){
            cargarTabla();
            limpiarFormulario();
            JOptionPane.showMessageDialog(
                    this,
                    "Membresia actualizada"
            );        
            
        } else {    
            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar la membresia"
            );         
        }    
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if(!Utilidades.ValidarPermiso(usuarioActivo))
        {
            JOptionPane.showMessageDialog(this, "No tiene los permisos necesarios", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Es necesario elegir una membresia"
                );
                return;
            }
            Membresia membresia = DatosRep.MEMBRESIAS.obtener(filaSeleccionada);
            if (membresia == null) {
                return;
            }
            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar la membresia " + membresia.getNombrePlan() + "?",
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta != JOptionPane.YES_OPTION) {
                return;
            }

            boolean eliminado = DatosRep.MEMBRESIAS.eliminar(filaSeleccionada);
            if (eliminado) {
                cargarTabla();
                limpiarFormulario();
                JOptionPane.showMessageDialog(
                        this,
                        "Membresia eliminada correctamente"
                );

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error al eliminar la membresia"
                );
            }
           
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void tblMembresiasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMembresiasMouseClicked
        cargarMembresiaSeleccionada();
    }//GEN-LAST:event_tblMembresiasMouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDescripcionPlan;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombrePlan;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVigenciaDias;
    private javax.swing.JTable tblMembresias;
    private javax.swing.JTextField txtCodigoMembresia;
    private javax.swing.JTextField txtDescripcionPlan;
    private javax.swing.JTextField txtNombrePlan;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtVigenciaDias;
    // End of variables declaration//GEN-END:variables
}
