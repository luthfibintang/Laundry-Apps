/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package user;

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
import javax.swing.JOptionPane;

/**
 *
 * @author Luthfi
 */
public class UserProfile extends javax.swing.JFrame {
    
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private String username;
    
    /**
     * Creates new form CustomerDashboard
     */
    
    public UserProfile() {
        initComponents();
    }
    
    public UserProfile(String username) {
        initComponents();
        con = Connections.getConnection();
        this.username = username;
        Profile();
        fillForm();
    }
    
    private void Profile() {
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = con.prepareStatement("SELECT * FROM `user` WHERE username = ?");
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Mengisi text field dengan data dari database
                lblFullname.setText(rs.getString("firstname") + " " + rs.getString("lastname"));
                lblRole.setText(rs.getString("level"));
                lblUsername.setText(rs.getString("username"));
                lblPhoneNumber.setText(rs.getString("telp"));
                taAddress.setText(rs.getString("alamat"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching order details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void fillForm() {
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = con.prepareStatement("SELECT * FROM `user` WHERE username=?");
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Mengisi text field dengan data dari database
                tfFirstname.setText(rs.getString("firstname"));
                tfLastname.setText(rs.getString("lastname"));
                tfUname.setText(rs.getString("username"));
                tfPhoneNumber.setText(rs.getString("telp"));
                tfAddress.setText(rs.getString("alamat"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Update Data : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateUser(){
        try {
            // Mendapatkan data dari input fields
            String newFirstname = tfFirstname.getText();
            String newLastname = tfLastname.getText();
            String newUsername = tfUname.getText();
            String newPhoneNumber = tfPhoneNumber.getText();
            String newAddress = tfAddress.getText();
            
            //Dapatkan user id
            int userId = getUserId(username);

            // Validasi: Username baru harus unik
            if (!newUsername.equals(username) && isUsernameExists(newUsername)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update data pengguna dalam database
            String updateQuery = "UPDATE user SET firstname=?, lastname=?, username=?, telp=?, alamat=? WHERE id_user=?";
            pst = con.prepareStatement(updateQuery);
            pst.setString(1, newFirstname);
            pst.setString(2, newLastname);
            pst.setString(3, newUsername);
            pst.setString(4, newPhoneNumber);
            pst.setString(5, newAddress);
            pst.setInt(6, userId);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "User profile updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new UserProfile(newUsername).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update user profile.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating user profile: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int getUserId(String username) throws SQLException {
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT id_user FROM user WHERE username = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, username);
        rs = pst.executeQuery();
        if(rs.next()){
            return rs.getInt("id_user");
        }
        return 0;
    }
    
    private boolean isUsernameExists(String newUsername) throws SQLException {
        String query = "SELECT * FROM user WHERE username=?";
        pst = con.prepareStatement(query);
        pst.setString(1, newUsername);
        rs = pst.executeQuery();
        return rs.next();
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
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        Jlabel1 = new javax.swing.JLabel();
        lblFullname = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPhoneNumber = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAddress = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfFirstname = new javax.swing.JTextField();
        tfUname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfPhoneNumber = new javax.swing.JTextField();
        btnEditProfile = new javax.swing.JButton();
        tfLastname = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tfAddress = new javax.swing.JTextArea();

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
        btnMenuOrder.setText("Order");
        btnMenuOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenuOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuOrderActionPerformed(evt);
            }
        });
        jPanel1.add(btnMenuOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 130, 60));

        btnMenuProfile.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMenuProfile.setForeground(new java.awt.Color(102, 127, 255));
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

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel7.setText("User Profile");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 690, 70));

        jPanel2.setBackground(new java.awt.Color(102, 127, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 730, 90));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setText("Address :");

        lblRole.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblRole.setForeground(new java.awt.Color(153, 153, 153));
        lblRole.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRole.setText("roles");
        lblRole.setToolTipText("");

        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel10.setText("Fullname :");

        jLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel.setText("Username : ");

        Jlabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        Jlabel1.setText("Phone Number : ");

        lblFullname.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblFullname.setText("first + lastname");

        lblUsername.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUsername.setText("username");

        lblPhoneNumber.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblPhoneNumber.setText("telp");

        jButton1.setText("Change Password");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        taAddress.setColumns(20);
        taAddress.setLineWrap(true);
        taAddress.setRows(5);
        taAddress.setAutoscrolls(false);
        taAddress.setBorder(null);
        taAddress.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        taAddress.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        taAddress.setEnabled(false);
        jScrollPane1.setViewportView(taAddress);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(Jlabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(lblRole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblFullname))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel)
                    .addComponent(lblUsername))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jlabel1)
                    .addComponent(lblPhoneNumber))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 270, 410));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Address");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jLabel3.setText("Firstname");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel4.setText("Lastname");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel6.setText("Phone Number");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, 20));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Edit Profile");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 30));
        jPanel5.add(tfFirstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 380, 30));
        jPanel5.add(tfUname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 380, 30));

        jLabel8.setText("Username");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, -1));
        jPanel5.add(tfPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 380, 30));

        btnEditProfile.setBackground(new java.awt.Color(102, 127, 255));
        btnEditProfile.setForeground(new java.awt.Color(255, 255, 255));
        btnEditProfile.setText("Save ");
        btnEditProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfileActionPerformed(evt);
            }
        });
        jPanel5.add(btnEditProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 80, 30));
        jPanel5.add(tfLastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 380, 30));

        tfAddress.setColumns(20);
        tfAddress.setLineWrap(true);
        tfAddress.setRows(5);
        jScrollPane2.setViewportView(tfAddress);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 380, 50));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, 400, 410));

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
        new CustomerDashboard(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuDashboardActionPerformed

    private void btnMenuOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuOrderActionPerformed
        // TODO add your handling code here:
        OrderHistory orderHistory = new OrderHistory(username);
        orderHistory.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuOrderActionPerformed

    private void btnMenuProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuProfileActionPerformed

    private void btnEditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfileActionPerformed
        updateUser();
    }//GEN-LAST:event_btnEditProfileActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new ChangePassword(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(UserProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserProfile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserProfile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jlabel1;
    private javax.swing.JButton btnEditProfile;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenuDashboard;
    private javax.swing.JButton btnMenuOrder;
    private javax.swing.JButton btnMenuProfile;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextArea taAddress;
    private javax.swing.JTextArea tfAddress;
    private javax.swing.JTextField tfFirstname;
    private javax.swing.JTextField tfLastname;
    private javax.swing.JTextField tfPhoneNumber;
    private javax.swing.JTextField tfUname;
    // End of variables declaration//GEN-END:variables
}
