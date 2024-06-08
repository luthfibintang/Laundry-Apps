/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Customer;

//import javax.swing.JOptionPane;
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
public class OrderForm extends javax.swing.JFrame {
    
    Connection con;
    private String username;
    /**
     * Creates new form CustomerDashboard
     */
    public OrderForm() {
        initComponents();
        con = Connections.getConnection();
        updateComboServices();
    }
    
    public OrderForm(String username) {
        initComponents();
        this.username = username;
        jLabel3.setText("Hi, " + username);
        con = Connections.getConnection();
        updateComboServices();
    }
    
    private void updateComboServices(){
        PreparedStatement pst;
        ResultSet rs;
        try{
            pst = con.prepareStatement("SELECT * FROM services");
            rs = pst.executeQuery();
            while(rs.next()){
                cbServices.addItem(rs.getString("jenis"));
            }
        } catch (SQLException ex){
            System.out.println("Error " + ex);
        }
    }
    
    private void createOrder() {
        PreparedStatement pst;
        
        try {
            // Ambil data dari form
            String fullName = tfFullName.getText();
            String phoneNumber = tfPhoneNumber.getText();
            String address = tfAddress.getText();
            String selectedService = (String) cbServices.getSelectedItem();
            int berat = (int) spBerat.getValue();
            boolean pickupDelivery = rbYes.isSelected();
            
            // Hitung harga dari database services
            int harga = (int) (getHarga(selectedService)*berat);
            
            // Hitung ongkir
            int ongkir = pickupDelivery ? 15000 : 0;
            
            // Hitung total bayar
            int totalBayar = (int) ((getHarga(selectedService) * berat) + ongkir);
            
            // Validasi
            if(fullName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty()){
                JOptionPane.showMessageDialog(this, "Tolong Masukan Semua Data", "Coba Lagi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(berat <= 0 || berat > 30){
                JOptionPane.showMessageDialog(this, "Berat Minimal 1kg dan maksimal 30kg", "Coba Lagi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if(!rbYes.isSelected() && !rbNo.isSelected()){
                JOptionPane.showMessageDialog(this, "Tolong pilih delivery&pickup atau tidak", "Coba Lagi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // mendapatkan tanggal saat ini
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            pst = con.prepareStatement("INSERT INTO `order` (tanggal, id_user, fullname, telp, alamat, id_services, berat, harga, ongkir, total_bayar, status) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            
            pst.setDate(1, sqlDate); // Tanggal saat ini
            pst.setInt(2, getIdUser(username));
            pst.setString(3, fullName);
            pst.setString(4, phoneNumber);
            pst.setString(5, address);
            pst.setInt(6, getIdServices(selectedService));
            pst.setInt(7, berat);
            pst.setInt(8, harga);
            pst.setInt(9, ongkir);
            pst.setInt(10, totalBayar);
            pst.setString(11, "Menunggu Konfirmasi");  // Status default ketika user pertama kali order
            
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Order success, please wait ", "Success", JOptionPane.INFORMATION_MESSAGE);
            new OrderHistory(username).setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error saat menyimpan order: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private int getIdUser(String username) throws SQLException {
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
    
    private double getHarga(String serviceName) throws SQLException {
        // Ambil harga dari database berdasarkan jenis layanan
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT harga FROM services WHERE jenis = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, serviceName);
        rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("harga");
        }
        return 0;
    }
    
    private int getIdServices(String serviceName) throws SQLException {
        // Ambil ID layanan dari database berdasarkan jenis layanan
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT id_services FROM services WHERE jenis = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, serviceName);
        rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_services");
        }
        return 0;
    }
    
    private void fillForm() {
        PreparedStatement pst;
        ResultSet rs;

        try {
            pst = con.prepareStatement("SELECT * FROM `user` WHERE username = ?");
            pst.setString(1, username);
            rs = pst.executeQuery();

            if (rs.next()) {
                // Mengisi text field dengan data dari database
                tfFullName.setText(rs.getString("firstname") + " " + rs.getString("lastname"));
                tfPhoneNumber.setText(rs.getString("telp"));
                tfAddress.setText(rs.getString("alamat"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error fetching order details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        btnMenuOrder = new javax.swing.JButton();
        btnMenuProfile = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAutoFill = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rbYes = new javax.swing.JRadioButton();
        rbNo = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        tfAddress = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfFullName = new javax.swing.JTextField();
        tfPhoneNumber = new javax.swing.JTextField();
        cbServices = new javax.swing.JComboBox<>();
        spBerat = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        btnOrder = new javax.swing.JButton();

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
        jLabel2.setText("Order Form");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 50));

        btnAutoFill.setText("Auto Fill");
        btnAutoFill.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAutoFill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAutoFillActionPerformed(evt);
            }
        });
        jPanel3.add(btnAutoFill, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 90, 30));

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

        jLabel1.setText("Pickup & Delivery");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel4.setText("Phone Number");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jLabel5.setText("Address");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        jLabel6.setText("Services");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        buttonGroup1.add(rbYes);
        rbYes.setText("Ya");
        rbYes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rbYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbYesActionPerformed(evt);
            }
        });
        jPanel4.add(rbYes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        buttonGroup1.add(rbNo);
        rbNo.setText("Tidak");
        rbNo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.add(rbNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("/KG");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 30, 30));
        jPanel4.add(tfAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 640, 30));

        jLabel8.setText("Full Name");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        jPanel4.add(tfFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 640, 30));
        jPanel4.add(tfPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 640, 30));

        jPanel4.add(cbServices, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 640, 30));
        jPanel4.add(spBerat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 100, 30));

        jLabel9.setText("Berat");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        btnOrder.setBackground(new java.awt.Color(102, 127, 255));
        btnOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnOrder.setText("Order");
        btnOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });
        jPanel4.add(btnOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 670, 30));

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
        new OrderHistory(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuOrderActionPerformed

    private void btnMenuProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuProfileActionPerformed
        // TODO add your handling code here:
        UserProfile profile = new UserProfile(username);
        profile.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuProfileActionPerformed

    private void rbYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbYesActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        // TODO add your handling code here:
        createOrder();
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnAutoFillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAutoFillActionPerformed
        // TODO add your handling code here:
        fillForm();
    }//GEN-LAST:event_btnAutoFillActionPerformed

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
                new OrderForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAutoFill;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenuDashboard;
    private javax.swing.JButton btnMenuOrder;
    private javax.swing.JButton btnMenuProfile;
    private javax.swing.JButton btnOrder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbServices;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton rbNo;
    private javax.swing.JRadioButton rbYes;
    private javax.swing.JSpinner spBerat;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfFullName;
    private javax.swing.JTextField tfPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
