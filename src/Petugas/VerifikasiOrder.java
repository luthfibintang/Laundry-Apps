/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Petugas;

import Customer.*;
import Access.LoginPage;
//import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Connection.Connections;
import java.sql.Connection;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import user.UserProfile;

/**
 *
 * @author Luthfi
 */
public class VerifikasiOrder extends javax.swing.JFrame {
    
    Connection con;
//    PreparedStatement pst;
//    ResultSet rs;
    private String username;
    
    /**
     * Creates new form CustomerDashboard
     */
    
    public VerifikasiOrder() {
        initComponents();
    }
    
    public VerifikasiOrder(String username) {
        initComponents();
        con = Connections.getConnection();
        jLabel1.setText("Hi, " + username);
        this.username = username;
        displayOrderData();
        displayUnverifData();
    }
    
    private void displayOrderData(){
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = con.prepareStatement("SELECT * FROM `order` where status=?");
            pst.setString(1, "Menunggu Konfirmasi");
            rs = pst.executeQuery();

            // Creating a DefaultTableModel to hold the data for the JTable
            DefaultTableModel model = (DefaultTableModel) tbOrder.getModel();

            // Clear the existing data from the table
            model.setRowCount(0);

            while (rs.next()) {
                // Retrieve data from the ResultSet
                String tanggal = rs.getString("tanggal");
                
                int id = rs.getInt("id_order");
                String name = rs.getString("fullname");
                String phoneNumber = rs.getString("telp");
                String address = rs.getString("alamat");
                String services = getServices(rs.getString("id_services"));
                String status = rs.getString("status");

                // Add the data to the table model
                Vector<Object> row = new Vector<>();
                row.add(id);
                row.add(tanggal);
                row.add(name);
                row.add(phoneNumber);
                row.add(address);
                row.add(services);
                row.add(status);

                model.addRow(row);
            }
            
            if (model.getRowCount() == 0) {
                // If the table is empty, panel is hide
                lblEmptyOrder.setVisible(true);
                lblEmptyOrder.setText("Tidak ada pesanan laundry yang butuh di konfirmasi.");
                btnRejectOrder.setVisible(false);
                btnConfirmOrder.setVisible(false);
            } else {
                // If there is data, show the panel
                lblEmptyOrder.setVisible(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching services data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayUnverifData(){
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = con.prepareStatement("SELECT * FROM `order` where status=?");
            pst.setString(1, "Terkonfirmasi");
            rs = pst.executeQuery();

            // Creating a DefaultTableModel to hold the data for the JTable
            DefaultTableModel model = (DefaultTableModel) tbUnverif.getModel();

            // Clear the existing data from the table
            model.setRowCount(0);

            while (rs.next()) {
                // Retrieve data from the ResultSet
                String tanggal = rs.getString("tanggal");
                
                int id = rs.getInt("id_order");
                int berat = rs.getInt("berat");
                String services = getServices(rs.getString("id_services"));
                int harga = rs.getInt("harga");
                int ongkir = rs.getInt("ongkir");
                int total = rs.getInt("total_bayar");
                String status = rs.getString("status");

                // Add the data to the table model
                Vector<Object> row = new Vector<>();
                row.add(id);
                row.add(tanggal);
                row.add(services);
                row.add(berat);
                row.add(harga);
                row.add(ongkir);
                row.add(total);
                row.add(status);

                model.addRow(row);
            }
            
            if (model.getRowCount() == 0) {
                // If the table is empty, panel is hide
                lblEmptyVerif.setVisible(true);
                lblEmptyVerif.setText("Tidak ada pesanan laundry yang butuh di verifikasi.");
                btnVerif.setVisible(false);
            } else {
                // If there is data, show the panel
                lblEmptyVerif.setVisible(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching services data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String getServices(String serviceId) throws SQLException {
        // Ambil ID layanan dari database berdasarkan jenis layanan
        PreparedStatement pstgetServices;
        
        ResultSet rs;
        pstgetServices = con.prepareStatement("SELECT jenis FROM services WHERE id_services= ?");
        pstgetServices.setString(1, serviceId);
        rs = pstgetServices.executeQuery();
        if (rs.next()) {
            return rs.getString("jenis");
        }
        return null;
    }
    
    private void openOrderForm(String username){
        new OrderForm(username).setVisible(true);
        this.dispose();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        btnMenuDashboard = new javax.swing.JButton();
        btnMenuOrder = new javax.swing.JButton();
        btnMenuOrder1 = new javax.swing.JButton();
        btnOrderInProgress = new javax.swing.JButton();
        btnFinishedOrder = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnRejectOrder = new javax.swing.JButton();
        btnVerif = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblEmptyVerif = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbUnverif = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        lblEmptyOrder = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbOrder = new javax.swing.JTable();
        btnConfirmOrder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(670, 450));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setBackground(new java.awt.Color(255, 51, 51));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 130, 60));

        btnMenuDashboard.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuDashboard.setText("Dashboard");
        btnMenuDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 130, 60));

        btnMenuOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuOrder.setForeground(new java.awt.Color(102, 127, 255));
        btnMenuOrder.setText("Verifikasi Order");
        btnMenuOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuOrderActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 130, 60));

        btnMenuOrder1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuOrder1.setText("Order");
        btnMenuOrder1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuOrder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuOrder1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuOrder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 130, 60));

        btnOrderInProgress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOrderInProgress.setText("Order Proses");
        btnOrderInProgress.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOrderInProgress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderInProgressActionPerformed(evt);
            }
        });
        jPanel1.add(btnOrderInProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 130, 60));

        btnFinishedOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFinishedOrder.setText("Order Selesai");
        btnFinishedOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFinishedOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishedOrderActionPerformed(evt);
            }
        });
        jPanel1.add(btnFinishedOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 130, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 550));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel2.setText("Verifikasi Order");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 690, 70));

        jPanel2.setBackground(new java.awt.Color(102, 127, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome, ");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 440, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 730, 90));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnRejectOrder.setBackground(new java.awt.Color(255, 51, 51));
        btnRejectOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnRejectOrder.setText("Tolak Order");
        btnRejectOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRejectOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectOrderActionPerformed(evt);
            }
        });
        jPanel4.add(btnRejectOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 100, 30));

        btnVerif.setBackground(new java.awt.Color(102, 127, 255));
        btnVerif.setForeground(new java.awt.Color(255, 255, 255));
        btnVerif.setText("Verifikasi Order");
        btnVerif.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifActionPerformed(evt);
            }
        });
        jPanel4.add(btnVerif, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, 110, 30));

        jLabel4.setText("Orderan Masuk");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 30));

        lblEmptyVerif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmptyVerif.setText("jLabel5");
        jPanel4.add(lblEmptyVerif, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 650, -1));

        tbUnverif.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Tanggal Order", "Services", "Berat", "Harga", "Ongkir", "Total", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbUnverif);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 650, 140));

        jLabel5.setText("Order yang sudah diverifikasi");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 30));

        lblEmptyOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmptyOrder.setText("jLabel3");
        jPanel4.add(lblEmptyOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 650, -1));

        tbOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Tanggal Order", "Nama", "No Telp", "Alamat", "Services", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbOrder);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 650, 130));

        btnConfirmOrder.setBackground(new java.awt.Color(102, 127, 255));
        btnConfirmOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmOrder.setText("Konfirmasi Order");
        btnConfirmOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmOrderActionPerformed(evt);
            }
        });
        jPanel4.add(btnConfirmOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, -1, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 690, 410));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
    
        if (option == JOptionPane.YES_OPTION) {
            // Tutup koneksi database jika diperlukan
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing database connection: " + ex.getMessage());
            }

            // Kembali ke halaman login
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            this.dispose(); // Nutup frame dashboard
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnMenuDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDashboardActionPerformed
        // TODO add your handling code here:
        PetugasDashboard dashboard = new PetugasDashboard(username);
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuDashboardActionPerformed

    private void btnMenuOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuOrderActionPerformed

    private void btnVerifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifActionPerformed
        PreparedStatement pst;
        ResultSet rs;
        
        int selectedRow = tbUnverif.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Tolong pilih baris yang ingin diverifikasi", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int orderId = (int) tbUnverif.getValueAt(selectedRow, 0);
        
        VerifikasiOrderForm verifForm = new VerifikasiOrderForm(username, orderId);
        verifForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVerifActionPerformed

    private void btnRejectOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectOrderActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbOrder.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Tolong pilih baris yang ingin di tolak.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int orderId = (int) tbOrder.getValueAt(selectedRow, 0);

        // Konfirmasi penghapusan data
        int option = JOptionPane.showConfirmDialog(this, "Anda yakin menolak orderan ini?", "Tolak Order", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            try {
                // Hapus data dari database
                PreparedStatement pstDelete = con.prepareStatement("UPDATE `order` SET status=? WHERE id_order=?");
                pstDelete.setString(1, "Ditolak");
                pstDelete.setInt(2, orderId);
                int rowsAffected = pstDelete.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Order Berhasil ditolak.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayOrderData();
                    displayUnverifData();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal Menolak Order.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error Updating order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRejectOrderActionPerformed

    private void btnConfirmOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmOrderActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbOrder.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Tolong pilih baris yang ingin dikonfirmasi.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int orderId = (int) tbOrder.getValueAt(selectedRow, 0);

        // Konfirmasi penghapusan data
        int option = JOptionPane.showConfirmDialog(this, "Anda yakin mengkonfirmasi orderan ini?", "Konfirmasi Order", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            try {
                // Hapus data dari database
                PreparedStatement pstConfirm = con.prepareStatement("UPDATE `order` SET status=? WHERE id_order=?");
                pstConfirm.setString(1, "Terkonfirmasi");
                pstConfirm.setInt(2, orderId);
                int rowsAffected = pstConfirm.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Order Berhasil dikonfirmasi.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayOrderData(); 
                    displayUnverifData();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal mengkonfirmasi Order.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error Updating order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnConfirmOrderActionPerformed

    private void btnMenuOrder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuOrder1ActionPerformed
        // TODO add your handling code here:
        new PetugasOrderForm(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuOrder1ActionPerformed

    private void btnOrderInProgressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderInProgressActionPerformed
        // TODO add your handling code here:
        new OrderProcess(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnOrderInProgressActionPerformed

    private void btnFinishedOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinishedOrderActionPerformed
        // TODO add your handling code here:
        new FinishedOrder(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnFinishedOrderActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VerifikasiOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerifikasiOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerifikasiOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerifikasiOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VerifikasiOrder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmOrder;
    private javax.swing.JButton btnFinishedOrder;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenuDashboard;
    private javax.swing.JButton btnMenuOrder;
    private javax.swing.JButton btnMenuOrder1;
    private javax.swing.JButton btnOrderInProgress;
    private javax.swing.JButton btnRejectOrder;
    private javax.swing.JButton btnVerif;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblEmptyOrder;
    private javax.swing.JLabel lblEmptyVerif;
    private javax.swing.JTable tbOrder;
    private javax.swing.JTable tbUnverif;
    // End of variables declaration//GEN-END:variables
}
