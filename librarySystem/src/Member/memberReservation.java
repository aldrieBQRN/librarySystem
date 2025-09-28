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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yeojvaldez
 */
public class memberReservation extends javax.swing.JInternalFrame {

    private final String memberName;
    
    public memberReservation(String username) {
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

    public final void background(){
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UI = (BasicInternalFrameUI) this.getUI();
        UI.setNorthPane(null);
        
    }
    
    public final void showTable() {
  
        try {
            String username = this.memberName;
            String query = "SELECT reservation_id, book_id, reservation_date, status FROM Reservations WHERE username = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, username);
            rs = pst.executeQuery();
             
            
            // Create table model and add columns
            DefaultTableModel model = (DefaultTableModel) tblbook.getModel();
            model.setRowCount(0);
            
            // Add rows from the database
            while (rs.next()) {
                int id = rs.getInt("reservation_id");
               
                int bookID = rs.getInt("book_id");
                String date = rs.getString("reservation_date");
                String status = rs.getString("status");
                
                model.addRow(new Object[]{id, bookID, date, status});
            }
        } catch (SQLException ex) {
            Logger.getLogger(memberReservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlhome = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbook = new rojerusan.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();
        txtBookID = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jdcBorrowDate = new com.toedter.calendar.JDateChooser();
        jdcDueDate = new com.toedter.calendar.JDateChooser();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        pnlhome.setBackground(new java.awt.Color(240, 237, 226));
        pnlhome.setPreferredSize(new java.awt.Dimension(1238, 750));

        tblbook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reservation ID", "Book ID", "Resrvation Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblbook.setColorBackgoundHead(new java.awt.Color(255, 255, 255));
        tblbook.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tblbook.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tblbook.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblbook.setColorFilasForeground1(new java.awt.Color(61, 58, 87));
        tblbook.setColorFilasForeground2(new java.awt.Color(61, 58, 87));
        tblbook.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tblbook.setColorSelBackgound(new java.awt.Color(61, 58, 87));
        tblbook.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        tblbook.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblbook.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblbook.setGridColor(new java.awt.Color(204, 204, 204));
        tblbook.setRowHeight(30);
        tblbook.setSelectionBackground(new java.awt.Color(61, 58, 87));
        tblbook.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblbook.setShowGrid(false);
        tblbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbookMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbook);

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 61, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Borrow Resserve Book");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        txtBookID.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtBookID.setPreferredSize(new java.awt.Dimension(78, 30));
        txtBookID.setSize(new java.awt.Dimension(78, 30));
        txtBookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookIDActionPerformed(evt);
            }
        });

        txtQuantity.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtQuantity.setPreferredSize(new java.awt.Dimension(78, 30));
        txtQuantity.setSize(new java.awt.Dimension(78, 30));
        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuantityKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtQuantityKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel5.setText("QUANTITY");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel3.setText("BOOK ID");

        jdcBorrowDate.setPreferredSize(new java.awt.Dimension(78, 30));

        jdcDueDate.setPreferredSize(new java.awt.Dimension(93, 30));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle2.setText("CLEAR");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle1.setText("CONFIRM");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel4.setText("BORROW DATE");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel9.setText("RETURN DATE");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(50, 61, 46));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Reserve Books");

        javax.swing.GroupLayout pnlhomeLayout = new javax.swing.GroupLayout(pnlhome);
        pnlhome.setLayout(pnlhomeLayout);
        pnlhomeLayout.setHorizontalGroup(
            pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlhomeLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(pnlhomeLayout.createSequentialGroup()
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(jLabel1))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlhomeLayout.createSequentialGroup()
                                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlhomeLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(txtBookID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlhomeLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(56, 56, 56)
                                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(103, 103, 103)
                                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jdcBorrowDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlhomeLayout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlhomeLayout.setVerticalGroup(
            pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlhomeLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcBorrowDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcDueDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(19, 19, 19)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlhome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlhome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookIDActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed

    }//GEN-LAST:event_txtQuantityKeyPressed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void txtQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityKeyTyped

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        txtBookID.setText("");
        txtQuantity.setText("");
        jdcBorrowDate.setDate(null);
        jdcDueDate.setDate(null);
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed

      try {
    // Validate that all fields are filled
    if (txtBookID.getText().trim().isEmpty() ||
        txtQuantity.getText().trim().isEmpty() ||
        jdcBorrowDate.getDate() == null ||
        jdcDueDate.getDate() == null) {

        JOptionPane.showMessageDialog(this, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String memberName = this.memberName;  // Assuming username is stored in the class
    int bookID;
    int quantity;

    try {
        bookID = Integer.parseInt(txtBookID.getText().trim());
        quantity = Integer.parseInt(txtQuantity.getText().trim());

        if (quantity <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be greater than 0!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid input! Enter a valid number for Book ID and Quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convert JDateChooser date to String (YYYY-MM-DD format)
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String borrowDate = sdf.format(jdcBorrowDate.getDate());
    String dueDate = sdf.format(jdcDueDate.getDate());

    con.setAutoCommit(false); // Start transaction

    // Check available copies
    String checkCopiesQuery = "SELECT copies FROM Books WHERE book_id = ?";
    pst = con.prepareStatement(checkCopiesQuery);
    pst.setInt(1, bookID);
    rs = pst.executeQuery();

    if (rs.next()) {
        int availableCopies = rs.getInt("copies");

        if (availableCopies < quantity) {
            JOptionPane.showMessageDialog(this, "Not enough copies available!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Insert into BorrowedBooks
        String insertQuery = "INSERT INTO BorrowedBooks (username, book_id, quantity, borrow_date, due_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(insertQuery);
        pst.setString(1, memberName);
        pst.setInt(2, bookID);
        pst.setInt(3, quantity);
        pst.setString(4, borrowDate);
        pst.setString(5, dueDate);
        pst.setString(6, "Borrowed");

        int k = pst.executeUpdate();
        if (k == 1) {
            // Update Book Copies
            int newQuantity = availableCopies - quantity;
            String updateCopiesQuery = "UPDATE Books SET copies = ?, status = ? WHERE book_id = ?";
            pst = con.prepareStatement(updateCopiesQuery);
            pst.setInt(1, newQuantity);
            pst.setString(2, newQuantity == 0 ? "Unavailable" : "Available");
            pst.setInt(3, bookID);
            pst.executeUpdate();

            // Check if book is reserved and update reservation status
            String checkReservationQuery = "SELECT COUNT(*) FROM Reservations WHERE book_id = ? AND username = ?";
            pst = con.prepareStatement(checkReservationQuery);
            pst.setInt(1, bookID);
            pst.setString(2, memberName);
            rs = pst.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Update reservation status to "Complete"
                String updateReservationQuery = "UPDATE Reservations SET status = 'Complete' WHERE book_id = ? AND username = ?";
                pst = con.prepareStatement(updateReservationQuery);
                pst.setInt(1, bookID);
                pst.setString(2, memberName);
                pst.executeUpdate();
            }

            // **Insert Notification into Database**
            String sqlNotify = "INSERT INTO Notifications (username, message, status) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sqlNotify);
            pst.setString(1, memberName);
            pst.setString(2, "You have successfully borrowed book (ID: " + bookID + "). Due Date: " + dueDate + ".");
            pst.setString(3, "Unread"); // Status can be 'Unread' or 'Read'
            pst.executeUpdate();

            con.commit(); // Commit transaction

            JOptionPane.showMessageDialog(this, "Book borrowed successfully!");

            // Clear input fields
            txtBookID.setText("");
            txtQuantity.setText("");
            jdcBorrowDate.setDate(null);
            jdcDueDate.setDate(null);

            // Refresh Table
            showTable();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Book ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }
} catch (SQLException ex) {
    try {
        con.rollback(); // Rollback in case of error
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException ex1) {
        Logger.getLogger(memberBorrow.class.getName()).log(Level.SEVERE, null, ex1);
    }
} finally {
    try {
        con.setAutoCommit(true); // Reset auto-commit
    } catch (SQLException ex) {
        Logger.getLogger(memberBorrow.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void tblbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbookMouseClicked
                                     
       int selectedRow = tblbook.getSelectedRow();
    if (selectedRow != -1) {
        txtBookID.setText(tblbook.getValueAt(selectedRow, 1).toString());
    }
    
    }//GEN-LAST:event_tblbookMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcBorrowDate;
    private com.toedter.calendar.JDateChooser jdcDueDate;
    private javax.swing.JPanel pnlhome;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSTableMetro tblbook;
    private javax.swing.JTextField txtBookID;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
