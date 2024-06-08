/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;

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
public class OrderHistory extends javax.swing.JFrame {
    
    Connection con;
//    PreparedStatement pst;
//    ResultSet rs;
    private String username;
    
    /**
     * Creates new form CustomerDashboard
     */
    
    public OrderHistory() {
        initComponents();
    }
    
    public OrderHistory(String username) {
        initComponents();
        con = Connections.getConnection();
        jLabel1.setText("Hi, " + username);
        this.username = username;
        displayOrderData();
        displayFinishedOrderData();
    }
    
    private void displayOrderData(){
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = con.prepareStatement("SELECT * FROM `order` WHERE id_user = ? AND status NOT IN (?, ?)");
            pst.setInt(1, getIdUser(username));
            pst.setString(2, "Selesai");
            pst.setString(3, "Ditolak");
            rs = pst.executeQuery();

            // Creating a DefaultTableModel to hold the data for the JTable
            DefaultTableModel model = (DefaultTableModel) tbOrder.getModel();

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
                lblEmptyOrder.setVisible(true);
                lblEmptyOrder.setText("Tidak ada pesanan laundry yang sedang dalam proses, klik tombol order now pada kanan atas untuk memesan laundry");
                btnUpdate.setVisible(false);
                btnDelete.setVisible(false);
            } else {
                // If there is data, show the panel
                lblEmptyOrder.setVisible(false);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching services data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void displayFinishedOrderData(){
        PreparedStatement pst;
        ResultSet rs;
        try {
            pst = con.prepareStatement("SELECT * FROM `order` WHERE id_user = ? AND (status=? OR status=?)");
            pst.setInt(1, getIdUser(username));
            pst.setString(2, "Selesai");
            pst.setString(3, "Ditolak");
            rs = pst.executeQuery();

            // Creating a DefaultTableModel to hold the data for the JTable
            DefaultTableModel model = (DefaultTableModel) tbFinishedOrder.getModel();

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
                finishedOrderPanel.setVisible(false);
            } else {
                // If there is data, show the panel
                finishedOrderPanel.setVisible(true);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching services data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int getIdUser(String username) throws SQLException {
        PreparedStatement pstIdUser;

        ResultSet rs;
        pstIdUser = con.prepareStatement("SELECT id_user FROM user WHERE username = ?");
        pstIdUser.setString(1, username);
        rs = pstIdUser.executeQuery();
        if(rs.next()){
            return rs.getInt("id_user");
        }
        return 0;
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
        btnMenuProfile = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnOrderForm = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblEmptyOrder = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbOrder = new javax.swing.JTable();
        finishedOrderPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFinishedOrder = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

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
        jPanel1.add(btnMenuDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 130, 60));

        btnMenuOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuOrder.setForeground(new java.awt.Color(102, 127, 255));
        btnMenuOrder.setText("Order");
        btnMenuOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuOrderActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 130, 60));

        btnMenuProfile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuProfile.setText("Profile");
        btnMenuProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuProfileActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 130, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 550));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel2.setText("Order History");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 160, 50));

        btnOrderForm.setText("Order Now");
        btnOrderForm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOrderForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderFormActionPerformed(evt);
            }
        });
        jPanel3.add(btnOrderForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 20, 90, 30));

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

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel4.add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 80, 30));

        btnUpdate.setText("Update");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel4.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 80, 30));

        jLabel4.setText("Order dalam proses");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 30));

        lblEmptyOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmptyOrder.setText("jLabel5");
        jPanel4.add(lblEmptyOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 120, 640, -1));

        tbOrder.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbOrder);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 650, 140));

        finishedOrderPanel.setBackground(new java.awt.Color(255, 255, 255));
        finishedOrderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbFinishedOrder.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbFinishedOrder);

        finishedOrderPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 650, 140));

        jLabel3.setText("Order yang sudah selesai");
        finishedOrderPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 30));

        jPanel4.add(finishedOrderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 690, 180));

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
        CustomerDashboard dashboard = new CustomerDashboard(username);
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuDashboardActionPerformed

    private void btnMenuOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuOrderActionPerformed

    private void btnMenuProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuProfileActionPerformed
        // TODO add your handling code here:
        UserProfile profile = new UserProfile(username);
        profile.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuProfileActionPerformed

    private void btnOrderFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderFormActionPerformed
        // TODO add your handling code here:
        openOrderForm(username);
    }//GEN-LAST:event_btnOrderFormActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        PreparedStatement pst;
        ResultSet rs;
        
        int selectedRow = tbOrder.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int orderId = (int) tbOrder.getValueAt(selectedRow, 0);
        
        // Validasi edit hanya bisa dilakukan jika status menunggu konfirmasi
        try {
            pst = con.prepareStatement("SELECT * FROM `order` WHERE id_order = ?");
            pst.setInt(1, orderId);
            rs = pst.executeQuery();
            
            if(rs.next()) {
                if(!rs.getString("status").equals("Menunggu Konfirmasi")){
                    JOptionPane.showMessageDialog(this, "Order tidak bisa diubah karena sudah di konfirmasi", "Try Again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }
        
        UpdateForm updateForm = new UpdateForm(username, orderId);
        updateForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tbOrder.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Tolong pilih baris yang ingin di hapus.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int orderId = (int) tbOrder.getValueAt(selectedRow, 0);

        // Validasi delete hanya bisa dilakukan jika status menunggu konfirmasi
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM `order` WHERE id_order = ?");
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                if (!rs.getString("status").equals("Menunggu Konfirmasi")) {
                    JOptionPane.showMessageDialog(this, "Order tidak bisa dihapus karena sudah di konfirmasi", "Try Again", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        } catch (SQLException ex) {
            System.out.println("" + ex);
        }

        // Konfirmasi penghapusan data
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this order?", "Delete Order", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            try {
                // Hapus data dari database
                PreparedStatement pstDelete = con.prepareStatement("DELETE FROM `order` WHERE id_order = ?");
                pstDelete.setInt(1, orderId);
                int rowsAffected = pstDelete.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Order deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    displayOrderData(); // Refresh tampilan tabel setelah penghapusan
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete order.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderHistory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenuDashboard;
    private javax.swing.JButton btnMenuOrder;
    private javax.swing.JButton btnMenuProfile;
    private javax.swing.JButton btnOrderForm;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel finishedOrderPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEmptyOrder;
    private javax.swing.JTable tbFinishedOrder;
    private javax.swing.JTable tbOrder;
    // End of variables declaration//GEN-END:variables
}
