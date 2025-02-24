/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Member;

import Staff.staffBook;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yeojvaldez
 */
public class memberFine extends javax.swing.JInternalFrame {

    private String memberName;
    
    public memberFine(String username) {
        this.memberName = username;
        initComponents();
        background();
        Connect();
        showTable();
    }
    
     Connection con; 
    PreparedStatement pst;
    ResultSet rs; 
    
    public final void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/libraryManagementSystem", "root", "");
            System.out.println("Database connection successful.");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(staffBook.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error connecting to database: " + ex.getMessage());
        }
    }
    
    public final void showTable() {
  
    DefaultTableModel model = (DefaultTableModel) finesTable.getModel();
    model.setRowCount(0); // Clear previous data

    String query = "SELECT fine_id, book_id, due_date, return_date, days_overdue, fine_amount, status " +
                   "FROM Fines WHERE username = ? && fine_amount >0";

    try {
        pst = con.prepareStatement(query);
        pst.setString(1, this.memberName);
        rs = pst.executeQuery();

        while (rs.next()) {
            int fineID = rs.getInt("fine_id");
            int bookID = rs.getInt("book_id");
            String dueDate = rs.getString("due_date");
            String returnDate = rs.getString("return_date");
            int daysOverdue = rs.getInt("days_overdue");
            double fineAmount = rs.getDouble("fine_amount");
            String status = rs.getString("status");

            model.addRow(new Object[]{fineID, bookID, dueDate, returnDate, daysOverdue, fineAmount, status});
        }

    } catch (SQLException ex) {
        Logger.getLogger(memberFine.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Error fetching fines!", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}




     public final void background(){
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UI = (BasicInternalFrameUI) this.getUI();
        UI.setNorthPane(null);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtfid = new javax.swing.JTextField();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        finesTable = new rojerusan.RSTableMetro();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtbid = new javax.swing.JTextField();
        txtdays = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtpay = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtchange = new javax.swing.JTextField();

        setBackground(new java.awt.Color(240, 237, 226));

        jPanel1.setBackground(new java.awt.Color(240, 237, 226));
        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 750));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel2.setText("FINE ID");

        txtfid.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtfid.setPreferredSize(new java.awt.Dimension(78, 30));
        txtfid.setSize(new java.awt.Dimension(78, 30));
        txtfid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfidActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle1.setText("CONFIRM PAYMENT");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle2.setText("CLEAR DETAILS");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 61, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Payment ");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        finesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fine ID", "Book ID", "Due Date", "Return Date", "Overdue Days", "Total Fine", "Status"
            }
        ));
        finesTable.setColorBackgoundHead(new java.awt.Color(255, 255, 255));
        finesTable.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        finesTable.setColorBordeHead(new java.awt.Color(255, 255, 255));
        finesTable.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        finesTable.setColorFilasForeground1(new java.awt.Color(61, 58, 87));
        finesTable.setColorFilasForeground2(new java.awt.Color(61, 58, 87));
        finesTable.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        finesTable.setColorSelBackgound(new java.awt.Color(61, 58, 87));
        finesTable.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        finesTable.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        finesTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        finesTable.setGridColor(new java.awt.Color(204, 204, 204));
        finesTable.setRowHeight(30);
        finesTable.setSelectionBackground(new java.awt.Color(61, 58, 87));
        finesTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        finesTable.setShowGrid(false);
        finesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                finesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(finesTable);

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(50, 61, 46));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Overdue and Fine");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel8.setText("BOOK ID");

        txtbid.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtbid.setPreferredSize(new java.awt.Dimension(78, 30));
        txtbid.setSize(new java.awt.Dimension(78, 30));
        txtbid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbidActionPerformed(evt);
            }
        });

        txtdays.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtdays.setPreferredSize(new java.awt.Dimension(78, 30));
        txtdays.setSize(new java.awt.Dimension(78, 30));
        txtdays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdaysActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel10.setText(" DAYS");

        jLabel11.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel11.setText("TOTAL FINE");

        txttotal.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txttotal.setPreferredSize(new java.awt.Dimension(78, 30));
        txttotal.setSize(new java.awt.Dimension(78, 30));
        txttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel13.setText("PAYMENT");

        txtpay.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtpay.setPreferredSize(new java.awt.Dimension(78, 30));
        txtpay.setSize(new java.awt.Dimension(78, 30));
        txtpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpayActionPerformed(evt);
            }
        });
        txtpay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtpayKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel14.setText("CHANGE");

        txtchange.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtchange.setPreferredSize(new java.awt.Dimension(78, 30));
        txtchange.setSize(new java.awt.Dimension(78, 30));
        txtchange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtchangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE))
                .addGap(84, 84, 84))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtfid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtbid, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtdays, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtchange, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(84, 84, 84)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(471, 471, 471)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtpay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(txtchange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtfid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtbid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtdays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(40, 40, 40)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfidActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed

   int fineID = Integer.parseInt(txtfid.getText().trim());

String updateFineQuery = "UPDATE Fines SET status = 'Paid' WHERE fine_id = ?";
String insertNotificationQuery = "INSERT INTO Notifications (username, message, status) VALUES (?, ?, 'Unread')";
String getUserQuery = "SELECT username, fine_amount FROM Fines WHERE fine_id = ?";

try {
    con.setAutoCommit(false); // Start transaction

    // Update fine status
    pst = con.prepareStatement(updateFineQuery);
    pst.setInt(1, fineID);
    int updatedRows = pst.executeUpdate();

    if (updatedRows > 0) {
        // Fetch username and fine amount
        pst = con.prepareStatement(getUserQuery);
        pst.setInt(1, fineID);
     rs = pst.executeQuery();

        if (rs.next()) {
            String username = rs.getString("username");
            double fineAmount = rs.getDouble("fine_amount");

            // Insert notification
            String notificationMessage = "You have successfully paid your overdue fine of " + fineAmount;
            pst = con.prepareStatement(insertNotificationQuery);
            pst.setString(1, username);
            pst.setString(2, notificationMessage);
            pst.executeUpdate();
        }

        con.commit(); // Commit transaction
        JOptionPane.showMessageDialog(null, "Fine marked as Paid successfully!");
        showTable(); // Refresh table
        txtfid.setText("");
    txtbid.setText("");
    txtdays.setText("");
    txttotal.setText("");
        
    } else {
        JOptionPane.showMessageDialog(null, "No fine found with the given ID!", "Error", JOptionPane.ERROR_MESSAGE);
    }

} catch (SQLException ex) {
    try {
        con.rollback(); // Rollback if an error occurs
    } catch (SQLException ex1) {
        Logger.getLogger(memberFine.class.getName()).log(Level.SEVERE, null, ex1);
    }
    Logger.getLogger(memberFine.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(null, "Error updating fine status!", "Database Error", JOptionPane.ERROR_MESSAGE);
} finally {
    try {
        con.setAutoCommit(true); // Reset auto-commit
    } catch (SQLException ex) {
        Logger.getLogger(memberFine.class.getName()).log(Level.SEVERE, null, ex);
    }
}


      
  


        
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void finesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finesTableMouseClicked
        int selectedRow = finesTable.getSelectedRow();
    if (selectedRow != -1) {
        txtfid.setText(finesTable.getValueAt(selectedRow, 0).toString());
        txtbid.setText(finesTable.getValueAt(selectedRow, 1).toString());
        txtdays.setText(finesTable.getValueAt(selectedRow, 4).toString());
        txttotal.setText(finesTable.getValueAt(selectedRow, 5).toString());

      

      
        
    }
    }//GEN-LAST:event_finesTableMouseClicked

    private void txtbidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbidActionPerformed

    private void txtdaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdaysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdaysActionPerformed

    private void txttotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalActionPerformed

    private void txtpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpayActionPerformed

    private void txtchangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtchangeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtchangeActionPerformed

    private void txtpayKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpayKeyReleased
        double totalAmount = Double.parseDouble(txttotal.getText());

            String payText = txtpay.getText();           

            double payAmount = Double.parseDouble(payText);
            double changeAmount = payAmount - totalAmount;

            txtchange.setText(String.format("%.2f", changeAmount));
    }//GEN-LAST:event_txtpayKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSTableMetro finesTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private javax.swing.JTextField txtbid;
    private javax.swing.JTextField txtchange;
    private javax.swing.JTextField txtdays;
    private javax.swing.JTextField txtfid;
    private javax.swing.JTextField txtpay;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
