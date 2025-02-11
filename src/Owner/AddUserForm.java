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
public class AddUserForm extends javax.swing.JFrame {
    
    Connection con;
    private String username;
    /**
     * Creates new form CustomerDashboard
     */
    public AddUserForm() {
        initComponents();
        con = Connections.getConnection();
    }
    
    public AddUserForm(String username) {
        initComponents();
        this.username = username;
        jLabel3.setText("Hi, " + username);
        con = Connections.getConnection();
    }
    
    private void addUser() {
        PreparedStatement pst;
        
        try {
            // Ambil data dari form
            String firstname = tfFirstname.getText();
            String lastname = tfLastname.getText();
            String uname = tfUsername.getText();
            String password = pfPassword.getText();
            String confirmPassword = pfConfirmPassword.getText();
            String phone = tfPhoneNumber.getText();
            String address = tfAddress.getText();
            String role = (String) cbLevel.getSelectedItem();
            
            if(firstname.isEmpty() || lastname.isEmpty() || uname.isEmpty() || password.isEmpty() ||
               confirmPassword.isEmpty() || phone.isEmpty() || address.isEmpty()){
                JOptionPane.showMessageDialog(this, "Tolong isi semua input", "Try Again", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Confirm Password doesn't match", "Try Again", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            pst = con.prepareStatement("INSERT INTO `user` (firstname, lastname, username, password, telp, alamat, level) VALUES (?,?,?,?,?,?,?)");
            
            pst.setString(1, firstname);
            pst.setString(2, lastname);
            pst.setString(3, uname);
            pst.setString(4, password);
            pst.setString(5, phone);
            pst.setString(6, address);
            pst.setString(7, role);
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Berhasil menambahkan user", "Success", JOptionPane.INFORMATION_MESSAGE);
            new ManageUser(username).setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saat menyimpan order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        btnManageOrder = new javax.swing.JButton();
        btnMenuProfile = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfFirstname = new javax.swing.JTextField();
        tfPhoneNumber = new javax.swing.JTextField();
        cbLevel = new javax.swing.JComboBox<>();
        btnAddUser = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfLastname = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pfPassword = new javax.swing.JPasswordField();
        pfConfirmPassword = new javax.swing.JPasswordField();

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

        btnManageOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnManageOrder.setForeground(new java.awt.Color(102, 127, 255));
        btnManageOrder.setText("Manage User");
        btnManageOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManageOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageOrderActionPerformed(evt);
            }
        });
        jPanel1.add(btnManageOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 130, 60));

        btnMenuProfile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuProfile.setText("Laporan");
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
        jLabel2.setText("Add User Form");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 50));

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

        jLabel4.setText("Nomor Handphone");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel5.setText("Alamat");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel6.setText("Role");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));
        jPanel4.add(tfAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 640, 30));

        jLabel8.setText("Username");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));
        jPanel4.add(tfFirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 300, 30));
        jPanel4.add(tfPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 640, 30));

        cbLevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Petugas" }));
        jPanel4.add(cbLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 640, 30));

        btnAddUser.setBackground(new java.awt.Color(102, 127, 255));
        btnAddUser.setForeground(new java.awt.Color(255, 255, 255));
        btnAddUser.setText("Tambah User");
        btnAddUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });
        jPanel4.add(btnAddUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 640, 30));

        jLabel10.setText("Firstname");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel4.add(tfUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 640, 30));

        jLabel9.setText("Lastname");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));
        jPanel4.add(tfLastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 320, 30));

        jLabel11.setText("Password");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel12.setText("Confirm Password");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 110, -1));
        jPanel4.add(pfPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 300, 30));
        jPanel4.add(pfConfirmPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 310, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 690, 420));

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
        new OwnerDashboard(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuDashboardActionPerformed

    private void btnManageOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageOrderActionPerformed
        // TODO add your handling code here:
        new ManageUser(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnManageOrderActionPerformed

    private void btnMenuProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuProfileActionPerformed
        // TODO add your handling code here:
        new GenerateReport(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuProfileActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        addUser();
    }//GEN-LAST:event_btnAddUserActionPerformed

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
                new AddUserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageOrder;
    private javax.swing.JButton btnMenuDashboard;
    private javax.swing.JButton btnMenuProfile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbLevel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPasswordField pfConfirmPassword;
    private javax.swing.JPasswordField pfPassword;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfFirstname;
    private javax.swing.JTextField tfLastname;
    private javax.swing.JTextField tfPhoneNumber;
    private javax.swing.JTextField tfUsername;
    // End of variables declaration//GEN-END:variables
}
