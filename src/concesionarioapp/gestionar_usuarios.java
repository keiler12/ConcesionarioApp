/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package concesionarioapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Reverie Pink
 */
public class gestionar_usuarios extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(gestionar_usuarios.class.getName());

       private final String RUTA_CSV = "baseDeDatos/usuarios.csv"; 
    
    /**
     * Creates new form gestionar_usuarios
     */
    public gestionar_usuarios() {
        initComponents();
        cargarUsuarios();
    }
    
      
    private void cargarUsuarios() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // Limpia la tabla antes de cargar

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_CSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) { // nombreUsuario, contraseña, tipoUsuario
                    modelo.addRow(datos);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo leer el archivo de usuarios.\n" + e.getMessage());
        }
    }

    
    private void eliminarUsuario() {
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
            return;
        }

        String usuarioEliminar = jTable1.getValueAt(fila, 0).toString();
        List<String[]> usuarios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_CSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (!datos[0].equals(usuarioEliminar)) {
                    usuarios.add(datos);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo.");
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_CSV))) {
            for (String[] u : usuarios) {
                pw.println(String.join(",", u));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar cambios.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
        cargarUsuarios();
    }

    
    private void editarUsuario() {
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar.");
            return;
        }

        String usuarioActual = jTable1.getValueAt(fila, 0).toString();

        String nuevoUsuario = JOptionPane.showInputDialog("Nuevo nombre de usuario:", usuarioActual);
        if (nuevoUsuario == null || nuevoUsuario.trim().isEmpty()) return;

        String nuevaContrasena = JOptionPane.showInputDialog("Nueva contraseña:", jTable1.getValueAt(fila, 1).toString());
        if (nuevaContrasena == null || nuevaContrasena.trim().isEmpty()) return;

        String nuevoTipo = JOptionPane.showInputDialog("Nuevo tipo de usuario (Cliente/Administrador):", jTable1.getValueAt(fila, 2).toString());
        if (nuevoTipo == null || nuevoTipo.trim().isEmpty()) return;

        List<String[]> usuarios = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_CSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[0].equals(usuarioActual)) {
                    usuarios.add(new String[]{nuevoUsuario, nuevaContrasena, nuevoTipo});
                } else {
                    usuarios.add(datos);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo de usuarios.");
            return;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(RUTA_CSV))) {
            for (String[] u : usuarios) {
                pw.println(String.join(",", u));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los cambios.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Usuario editado correctamente.");
        cargarUsuarios();
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnactualizarusuario = new javax.swing.JButton();
        btneliminarusuario = new javax.swing.JButton();
        btneditarusuario = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnactualizarusuario.setText("Actualizar usuario");
        btnactualizarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarusuarioActionPerformed(evt);
            }
        });

        btneliminarusuario.setText("Eliminar usuario");
        btneliminarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarusuarioActionPerformed(evt);
            }
        });

        btneditarusuario.setText("Editar usuario");
        btneditarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarusuarioActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnactualizarusuario)
                    .addComponent(btneditarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminarusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnactualizarusuario)
                        .addGap(67, 67, 67)
                        .addComponent(btneditarusuario)
                        .addGap(57, 57, 57)
                        .addComponent(btneliminarusuario))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnactualizarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarusuarioActionPerformed
        // TODO add your handling code here:
        
        cargarUsuarios() ;
        
    }//GEN-LAST:event_btnactualizarusuarioActionPerformed

    private void btneliminarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarusuarioActionPerformed
        // TODO add your handling code here:
        eliminarUsuario();
    }//GEN-LAST:event_btneliminarusuarioActionPerformed

    private void btneditarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarusuarioActionPerformed
        // TODO add your handling code here:
        editarUsuario();
        
    }//GEN-LAST:event_btneditarusuarioActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new gestionar_usuarios().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizarusuario;
    private javax.swing.JButton btneditarusuario;
    private javax.swing.JButton btneliminarusuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
