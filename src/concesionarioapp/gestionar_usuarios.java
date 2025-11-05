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
        btnRetroceder = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cboFiltro = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        lbllogo = new javax.swing.JLabel();
        lblfonfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnactualizarusuario.setBackground(new java.awt.Color(51, 153, 255));
        btnactualizarusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/concesionarioapp/actualizar.png"))); // NOI18N
        btnactualizarusuario.setText("Actualizar usuario");
        btnactualizarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnactualizarusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, -1, -1));

        btneliminarusuario.setBackground(new java.awt.Color(255, 153, 153));
        btneliminarusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/concesionarioapp/cancelar.png"))); // NOI18N
        btneliminarusuario.setText("Eliminar usuario");
        btneliminarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(btneliminarusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, 170, -1));

        btneditarusuario.setBackground(new java.awt.Color(255, 255, 204));
        btneditarusuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/concesionarioapp/editar.png"))); // NOI18N
        btneditarusuario.setText("Editar usuario");
        btneditarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarusuarioActionPerformed(evt);
            }
        });
        getContentPane().add(btneditarusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 230, 170, -1));

        jTable1.setBackground(new java.awt.Color(153, 204, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Usuarios", "Contraseñas", "Tipo de usuarios"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 142, 530, 450));

        btnRetroceder.setBackground(new java.awt.Color(153, 204, 255));
        btnRetroceder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/concesionarioapp/retroceder.png"))); // NOI18N
        btnRetroceder.setText("Retroceder");
        btnRetroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetrocederActionPerformed(evt);
            }
        });
        getContentPane().add(btnRetroceder, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Filtro");
        jLabel1.setBorder(new javax.swing.border.MatteBorder(null));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 380, 80, -1));

        cboFiltro.setBackground(new java.awt.Color(51, 153, 255));
        cboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Administrador", "Cliente" }));
        getContentPane().add(cboFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 420, 140, -1));

        btnFiltrar.setBackground(new java.awt.Color(204, 255, 204));
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, -1, -1));

        lbllogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/concesionarioapp/fondo1.png"))); // NOI18N
        getContentPane().add(lbllogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 100, 60));

        lblfonfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/concesionarioapp/imagen_fondo.png"))); // NOI18N
        getContentPane().add(lblfonfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, 2, 960, 640));

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

    private void btnRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetrocederActionPerformed
        // TODO add your handling code here:
        new interfaz_administrador().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnRetrocederActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        // TODO add your handling code here:
        String filtroSeleccionado = "Todos";
        if (cboFiltro.getSelectedItem() != null) {
            filtroSeleccionado = cboFiltro.getSelectedItem().toString().trim();
        }

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0); // limpia la tabla antes de aplicar filtro

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_CSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Si la línea está vacía la saltamos
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                String tipo = datos[2].trim();

                    // Normalizamos para comparar (acepta "Usuario"/"Usuarios", mayúsc/minúsc)
                    String filtroLower = filtroSeleccionado.toLowerCase();
                    String tipoLower = tipo.toLowerCase();

                    boolean coincidir = false;
                    if (filtroLower.contains("todo")) {
                        coincidir = true;
                    } else if (filtroLower.contains("client") && tipoLower.startsWith("client")) {
                        coincidir = true;
                    } else if (filtroLower.contains("admin") && tipoLower.startsWith("admin")) {
                        coincidir = true;
                    }

                    if (coincidir) {
                    // Agregamos fila asegurando que el número de columnas coincida con la tabla
                    // En tu caso la tabla tiene 3 columnas: usuario, contraseña, tipo
                        Object[] fila = new Object[] { datos[0], datos[1], datos[2] };
                        modelo.addRow(fila);
                }
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error al filtrar usuarios: " + e.getMessage());
    }
    }//GEN-LAST:event_btnFiltrarActionPerformed

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
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnRetroceder;
    private javax.swing.JButton btnactualizarusuario;
    private javax.swing.JButton btneditarusuario;
    private javax.swing.JButton btneliminarusuario;
    private javax.swing.JComboBox<String> cboFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblfonfo;
    private javax.swing.JLabel lbllogo;
    // End of variables declaration//GEN-END:variables
}
