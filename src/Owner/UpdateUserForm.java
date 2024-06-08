/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Owner;

//import javax.swing.JOptionPane;
import Customer.*;
import Access.LoginPage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Connection.Connections;
import java.sql.Connection;
import java.util.Date;
import javax.swing.JOptionPane;
import user.UserProfile;

/**
 *
 * @author Luthfi
 */
public class UpdateUserForm extends javax.swing.JFrame {
    
    Connection con;
    private int userId;
    private String username;
    /**
     * Creates new form CustomerDashboard
     */
    public UpdateUserForm() {
        initComponents();
    }
    
    public UpdateUserForm(String username, int userId) {
        initComponents();
        this.username = username;
        this.userId = userId;
        jLabel3.setText("Update User ID : " + userId);
        con = Connections.getConnection();
        fillForm();
    }
    
    private void fillForm() {
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = con.prepareStatement("SELECT * FROM `user` WHERE id_user = ?");
            pst.setInt(1, userId);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Mengisi text field dengan data dari database
                    tfFirstname.setText(rs.getString("firstname"));
                    tfLastname.setText(rs.getString("lastname"));
                    tfUsername.setText(rs.getString("username"));
                    tfPhoneNumber.setText(rs.getString("telp"));
                    tfAddress.setText(rs.getString("alamat"));
                    cbLevel.setSelectedItem(rs.getString("level"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching order details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateUser(){
        PreparedStatement pst;
    
        try {
            // Ambil data dari form
            String firstname = tfFirstname.getText();
            String lastname = tfLastname.getText();
            String uname = tfUsername.getText();
            String phoneNumber = tfPhoneNumber.getText();
            String address = tfAddress.getText();
            String role = (String) cbLevel.getSelectedItem();

            // Validasi
            if (firstname.isEmpty() || lastname.isEmpty() || uname.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tolong Masukan Semua Data", "Coba Lagi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pst = con.prepareStatement("UPDATE `user` SET firstname=?, lastname=?, username=?, telp=?, alamat=?, level=? WHERE id_user=?");

            pst.setString(1, firstname);
            pst.setString(2, lastname);
            pst.setString(3, uname);
            pst.setString(4, phoneNumber);
            pst.setString(5, address);
            pst.setString(6, role);
            pst.setInt(7, userId);

            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "User Berhasil Diubah", "Success", JOptionPane.INFORMATION_MESSAGE);
                new ManageUser(username).setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "User Gagal Diubah", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JButton();
        btnMenuDashboard = new javax.swing.JButton();
        btnManageUser = new javax.swing.JButton();
        btnLaporan = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfFirstname = new javax.swing.JTextField();
        tfPhoneNumber = new javax.swing.JTextField();
        btnOrder = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfLastname = new javax.swing.JTextField();
        cbLevel = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(670, 450));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLogout.setBackground(new java.awt.Color(255, 51, 51));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 130, 60));

        btnMenuDashboard.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuDashboard.setText("Dashboard");
        btnMenuDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 130, 60));

        btnManageUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnManageUser.setForeground(new java.awt.Color(102, 127, 255));
        btnManageUser.setText("Manage User");
        btnManageUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageUserActionPerformed(evt);
            }
        });
        jPanel1.add(btnManageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 130, 60));

        btnLaporan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLaporan.setText("Laporan");
        btnLaporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLaporanActionPerformed(evt);
            }
        });
        jPanel1.add(btnLaporan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 130, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 550));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel2.setText("Update User Form");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 220, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 690, 70));

        jPanel2.setBackground(new java.awt.Color(102, 127, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Welcome, ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 440, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 730, 90));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Phone Number");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, -1));

        jLabel5.setText("Address");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));
        jPanel4.add(tfAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 640, 30));

        jLabel8.setText("Username");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));
        jPanel4.add(tfFirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 640, 30));
        jPanel4.add(tfPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 640, 30));

        btnOrder.setBackground(new java.awt.Color(102, 127, 255));
        btnOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnOrder.setText("Ubah User");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });
        jPanel4.add(btnOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 640, 30));

        jLabel10.setText("Firstname");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        jPanel4.add(tfUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 640, 30));

        jLabel9.setText("Lastname");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        jPanel4.add(tfLastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 640, 30));

        cbLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Petugas" }));
        jPanel4.add(cbLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 640, 30));

        jLabel6.setText("Role");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

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

    private void btnManageUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageUserActionPerformed
        // TODO add your handling code here:
        new ManageUser(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageUserActionPerformed

    private void btnLaporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLaporanActionPerformed
        // TODO add your handling code here:
       new GenerateReport(username).setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnLaporanActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        // TODO add your handling code here:
        updateUser();
    }//GEN-LAST:event_btnOrderActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateUserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLaporan;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageUser;
    private javax.swing.JButton btnMenuDashboard;
    private javax.swing.JButton btnOrder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbLevel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfFirstname;
    private javax.swing.JTextField tfLastname;
    private javax.swing.JTextField tfPhoneNumber;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
