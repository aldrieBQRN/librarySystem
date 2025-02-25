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
public class returnBooks extends javax.swing.JInternalFrame {

    private String memberName;
    
    public returnBooks(String username) {
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
    try {
       String username = this.memberName;
        String query = "SELECT * FROM BorrowedBooks WHERE (status = ? OR status = ?) AND username = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, "Borrowed");
        pst.setString(2, "Reserved");  // Fixed spelling and index
        pst.setString(3, username);    // Correct parameter index
        rs = pst.executeQuery();


        DefaultTableModel borrowedBooksModel = (DefaultTableModel) tblBorrowedBooks.getModel();
        borrowedBooksModel.setRowCount(0); // Clear previous rows

        while (rs.next()) {
            int borrowID = rs.getInt("borrow_id");
           
            int bookID = rs.getInt("book_id");
            int quantity = rs.getInt("quantity");
            Date borrowDate = rs.getDate("borrow_date");
            Date dueDate = rs.getDate("due_date");
            

            borrowedBooksModel.addRow(new Object[]{borrowID, bookID, quantity, borrowDate, dueDate});
        }
    } catch (SQLException ex) {
        Logger.getLogger(returnBooks.class.getName()).log(Level.SEVERE, null, ex);
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
        txtbid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtbookid = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jdcborrow = new com.toedter.calendar.JDateChooser();
        jdcdue = new com.toedter.calendar.JDateChooser();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jdcreturn = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBorrowedBooks = new rojerusan.RSTableMetro();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtfine = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 237, 226));

        jPanel1.setBackground(new java.awt.Color(240, 237, 226));
        jPanel1.setPreferredSize(new java.awt.Dimension(1283, 750));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel2.setText("BORROWING ID");

        txtbid.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtbid.setPreferredSize(new java.awt.Dimension(78, 30));
        txtbid.setSize(new java.awt.Dimension(78, 30));
        txtbid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbidActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel3.setText("BOOK ID");

        txtbookid.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtbookid.setPreferredSize(new java.awt.Dimension(78, 30));
        txtbookid.setSize(new java.awt.Dimension(78, 30));
        txtbookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbookidActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel4.setText("BORROW DATE");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel9.setText("DUE DATE");

        jdcborrow.setPreferredSize(new java.awt.Dimension(78, 30));

        jdcdue.setPreferredSize(new java.awt.Dimension(93, 30));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle1.setText("RETURN");
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
        jLabel1.setText("Returning Books");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel5.setText("FINE");

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel12.setText("RETURN DATE");

        jdcreturn.setPreferredSize(new java.awt.Dimension(93, 30));
        jdcreturn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jdcreturnPropertyChange(evt);
            }
        });

        tblBorrowedBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Borrow ID", "Book ID", "Quantity", "Borrow Date", "Return Date"
            }
        ));
        tblBorrowedBooks.setColorBackgoundHead(new java.awt.Color(255, 255, 255));
        tblBorrowedBooks.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tblBorrowedBooks.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tblBorrowedBooks.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblBorrowedBooks.setColorFilasForeground1(new java.awt.Color(61, 58, 87));
        tblBorrowedBooks.setColorFilasForeground2(new java.awt.Color(61, 58, 87));
        tblBorrowedBooks.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tblBorrowedBooks.setColorSelBackgound(new java.awt.Color(61, 58, 87));
        tblBorrowedBooks.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        tblBorrowedBooks.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblBorrowedBooks.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblBorrowedBooks.setGridColor(new java.awt.Color(204, 204, 204));
        tblBorrowedBooks.setRowHeight(30);
        tblBorrowedBooks.setSelectionBackground(new java.awt.Color(61, 58, 87));
        tblBorrowedBooks.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblBorrowedBooks.setShowGrid(false);
        tblBorrowedBooks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBorrowedBooksMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBorrowedBooks);

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(50, 61, 46));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Borrowed Book");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(50, 61, 46));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Details");

        txtfine.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtfine.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtfine.setText("0.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jdcborrow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdcdue, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdcreturn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtfine, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtbookid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtbid, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(446, 446, 446)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtbid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtbookid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdcborrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcdue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jdcreturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(txtfine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
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

    private void txtbookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbookidActionPerformed

    private void txtbidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbidActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
      
  try {
    // Validate input fields
    if (txtbid.getText().trim().isEmpty() || 
        txtbookid.getText().trim().isEmpty() || 
        jdcborrow.getDate() == null || 
        jdcdue.getDate() == null ||
        jdcreturn.getDate() == null ||
        txtfine.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Parse borrow ID and book ID
        int borrowID = Integer.parseInt(txtbid.getText().trim());
        int bookID = Integer.parseInt(txtbookid.getText().trim());

        // Format return and due dates
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String returnDate = sdf.format(jdcreturn.getDate());
        String dueDate = sdf.format(jdcdue.getDate()); 

        // Parse fine amount
        double fine = Double.parseDouble(txtfine.getText().trim());

        // Calculate overdue days
        long diffInMillies = jdcreturn.getDate().getTime() - jdcdue.getDate().getTime();
        int daysOverdue = (int) (diffInMillies / (1000 * 60 * 60 * 24));
        if (daysOverdue < 0) {
            daysOverdue = 0; // No fine if returned before or on due date
            fine = 0.0;
        }

        // Confirm return action
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to return this book?", 
                                                    "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        con.setAutoCommit(false); // Start transaction

        // Get the quantity of books borrowed
        String getQuantityQuery = "SELECT quantity FROM BorrowedBooks WHERE borrow_id = ?";
        pst = con.prepareStatement(getQuantityQuery);
        pst.setInt(1, borrowID);
        ResultSet rsQty = pst.executeQuery();

        int borrowedQuantity = 1; // Default value
        if (rsQty.next()) {
            borrowedQuantity = rsQty.getInt("quantity"); // Get the exact borrowed quantity
        }

        // Insert fine into Fines table
        String insertFineQuery = "INSERT INTO Fines (username, book_id, due_date, return_date, days_overdue, fine_amount, status) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, 'Pending')";
        pst = con.prepareStatement(insertFineQuery);
        pst.setString(1, this.memberName); // Assuming this.memberName holds the returner's username
        pst.setInt(2, bookID);
        pst.setString(3, dueDate);
        pst.setString(4, returnDate);
        pst.setInt(5, daysOverdue);
        pst.setDouble(6, fine);
        pst.executeUpdate();

        // Check if the book has a reservation
        String checkReservation = "SELECT username FROM Reservations WHERE book_id = ?";
        pst = con.prepareStatement(checkReservation);
        pst.setInt(1, bookID);
        ResultSet rs2 = pst.executeQuery();
        
        boolean hasReservation = false;
        String reserverUsername = null;
        
        if (rs2.next()) {
            hasReservation = true;
            reserverUsername = rs2.getString("username");
        }

        // Determine new status
        String newBorrowedBookStatus = hasReservation ? "Returned/Reserved" : "Returned";
        String newBookStatus = hasReservation ? "Reserved" : "Available";

        // Update BorrowedBooks status
        String updateBorrowedBooks = "UPDATE BorrowedBooks SET status = ? WHERE borrow_id = ?";
        pst = con.prepareStatement(updateBorrowedBooks);
        pst.setString(1, newBorrowedBookStatus);
        pst.setInt(2, borrowID);
        pst.executeUpdate();

        // Update Books status and increase copies by the borrowed quantity
        String updateBookStatus = "UPDATE Books SET status = ?, copies = copies + ? WHERE book_id = ?";
        pst = con.prepareStatement(updateBookStatus);
        pst.setString(1, newBookStatus);
        pst.setInt(2, borrowedQuantity);
        pst.setInt(3, bookID);
        pst.executeUpdate();

        // If the book has a reservation, update its reservation status to "Available"
        if (hasReservation) {
            String updateReservation = "UPDATE Reservations SET status = 'Available' WHERE book_id = ?";
            pst = con.prepareStatement(updateReservation);
            pst.setInt(1, bookID);
            pst.executeUpdate();
        }

        // Insert notification for the returner
        String returnerMessage = "You have successfully returned " + borrowedQuantity + " copies of Book ID: " + bookID;
        if (fine > 0) {
            returnerMessage += ". Overdue fine: $" + fine;
        }
        String insertReturnNotification = "INSERT INTO Notifications (username, message, status) VALUES (?, ?, 'Unread')";
        pst = con.prepareStatement(insertReturnNotification);
        pst.setString(1, this.memberName);
        pst.setString(2, returnerMessage);
        pst.executeUpdate();

        // Insert notification for the reserver (if exists)
        if (hasReservation) {
            String reserverMessage = "The book you reserved (Book ID: " + bookID + ") is now available.";
            pst = con.prepareStatement(insertReturnNotification);
            pst.setString(1, reserverUsername);
            pst.setString(2, reserverMessage);
            pst.executeUpdate();
        }

        con.commit(); // Commit transaction

        JOptionPane.showMessageDialog(null, "Book return recorded successfully!");
        showTable(); // Refresh table after update
        txtbid.setText("");
    txtbookid.setText("");
    jdcborrow.setDate(null);
    jdcdue.setDate(null);
    jdcreturn.setDate(null);
    txtfine.setText("");

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid number format in Borrow ID or Fine!", "Input Error", JOptionPane.ERROR_MESSAGE);
        con.rollback(); // Rollback in case of error
    }

} catch (SQLException ex) {
    Logger.getLogger(returnBooks.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Error processing the return!", "Database Error", JOptionPane.ERROR_MESSAGE);
    try {
        con.rollback(); // Rollback in case of error
    } catch (SQLException ex1) {
        Logger.getLogger(returnBooks.class.getName()).log(Level.SEVERE, null, ex1);
    }
} finally {
    try {
        con.setAutoCommit(true); // Reset auto-commit
    } catch (SQLException ex) {
        Logger.getLogger(returnBooks.class.getName()).log(Level.SEVERE, null, ex);
    }
}


        
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        txtbid.setText("");
    txtbookid.setText("");
    jdcborrow.setDate(null);
    jdcdue.setDate(null);
    jdcreturn.setDate(null);
    txtfine.setText("");
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void tblBorrowedBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBorrowedBooksMouseClicked
        int selectedRow = tblBorrowedBooks.getSelectedRow();
    if (selectedRow != -1) {
        txtbid.setText(tblBorrowedBooks.getValueAt(selectedRow, 0).toString());
        txtbookid.setText(tblBorrowedBooks.getValueAt(selectedRow, 1).toString());

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            // Convert string dates to Date objects for JDateChooser
            Date borrowDate = sdf.parse(tblBorrowedBooks.getValueAt(selectedRow, 3).toString());
            Date dueDate = sdf.parse(tblBorrowedBooks.getValueAt(selectedRow, 4).toString());
            
            jdcborrow.setDate(borrowDate);
            jdcdue.setDate(dueDate);
        } catch (ParseException ex) {
            Logger.getLogger(returnBooks.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Clear return date & fine field initially (if return hasn't happened yet)
        jdcreturn.setDate(null);
        txtfine.setText("");
    }
    }//GEN-LAST:event_tblBorrowedBooksMouseClicked

    private void jdcreturnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jdcreturnPropertyChange
       if (jdcdue.getDate() == null || jdcreturn.getDate() == null) {
    return; 
}

java.util.Date dueUtilDate = jdcdue.getDate();
java.util.Date returnUtilDate = jdcreturn.getDate();

java.sql.Date due = new java.sql.Date(dueUtilDate.getTime());
java.sql.Date rtrn = new java.sql.Date(returnUtilDate.getTime());

// Calculate days overdue
long elapsedTime = returnUtilDate.getTime() - dueUtilDate.getTime();
long elapsedDays = TimeUnit.MILLISECONDS.toDays(elapsedTime);

if (elapsedDays < 0) {
    JOptionPane.showMessageDialog(this, "Return date must be equal to or greater than the due date!", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Fine calculation (only if overdue)
double fine = elapsedDays > 0 ? elapsedDays * 50 : 0;
txtfine.setText(String.valueOf(fine));

        
       
    }//GEN-LAST:event_jdcreturnPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcborrow;
    private com.toedter.calendar.JDateChooser jdcdue;
    private com.toedter.calendar.JDateChooser jdcreturn;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSTableMetro tblBorrowedBooks;
    private javax.swing.JTextField txtbid;
    private javax.swing.JTextField txtbookid;
    private javax.swing.JLabel txtfine;
    // End of variables declaration//GEN-END:variables
}
