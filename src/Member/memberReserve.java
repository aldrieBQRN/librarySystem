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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yeojvaldez
 */
public class memberReserve extends javax.swing.JInternalFrame {
    private String member;
   
    public memberReserve(String username) {
        this.member = username;
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
        String name = this.member;
        String query  = "SELECT bb.borrow_id, bb.username, bb.book_id, b.title, bb.quantity, bb.borrow_date, bb.due_date, bb.status " +
               "FROM BorrowedBooks bb " +
               "JOIN Books b ON bb.book_id = b.book_id " +
               "WHERE bb.status = 'Borrowed' && username != ?";


        pst = con.prepareStatement(query);
         pst.setString(1, name);
        rs = pst.executeQuery();

        // Get Table Model
        DefaultTableModel model = (DefaultTableModel) tblBorrowedBooks.getModel();
        model.setRowCount(0); // Clear previous data

        while (rs.next()) {
            int borrowID = rs.getInt("borrow_id");
            String username = rs.getString("username");
            int bookID = rs.getInt("book_id"); // Get book ID
            String bookTitle = rs.getString("title"); // Get book title
            int quantity = rs.getInt("quantity");
            String borrowDate = rs.getString("borrow_date");
            String dueDate = rs.getString("due_date");
            

            // Add row to table including Book ID and Title
            model.addRow(new Object[]{borrowID, username, bookID, bookTitle, quantity, borrowDate, dueDate});
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error fetching borrowed books: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel1 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBorrowedBooks = new rojerusan.RSTableMetro();
        jLabel2 = new javax.swing.JLabel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel8 = new javax.swing.JLabel();
        txtBookID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jdcReserveDate = new com.toedter.calendar.JDateChooser();
        txtborrowingid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(240, 237, 226));
        jPanel1.setPreferredSize(new java.awt.Dimension(1238, 750));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 61, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reserve Books");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle1.setText("RESERVE");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        tblBorrowedBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Borrowing ID", "Username", "Book ID", "Title", "Quantity", "Borrow Date", "Return Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel2.setText("BOOK ID");

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle2.setText("CLEAR");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(50, 61, 46));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Borrowed Books");

        txtBookID.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtBookID.setPreferredSize(new java.awt.Dimension(78, 30));
        txtBookID.setSize(new java.awt.Dimension(78, 30));
        txtBookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookIDActionPerformed(evt);
            }
        });
        txtBookID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBookIDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBookIDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBookIDKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel4.setText("RESERVATION DATE");

        jdcReserveDate.setPreferredSize(new java.awt.Dimension(78, 30));

        txtborrowingid.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtborrowingid.setPreferredSize(new java.awt.Dimension(78, 30));
        txtborrowingid.setSize(new java.awt.Dimension(78, 30));
        txtborrowingid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtborrowingidActionPerformed(evt);
            }
        });
        txtborrowingid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtborrowingidKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtborrowingidKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtborrowingidKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel3.setText("Borrowing ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(425, 425, 425)
                            .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(66, 66, 66)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(474, 474, 474)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtborrowingid, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBookID, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdcReserveDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 520, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(511, 511, 511))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtborrowingid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBookID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jdcReserveDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
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

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
      try {
    // Check if Book ID and Borrowing ID are empty
    if (txtBookID.getText().trim().isEmpty() || txtborrowingid.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Borrowing ID and Book ID cannot be empty!", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Check if the return date picker is empty
    if (jdcReserveDate.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Please select a return date!", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Parse Book ID safely
    int bookId;
    try {
        bookId = Integer.parseInt(txtBookID.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Book ID! Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Get username
    String username = this.member;
    int borrowingID = Integer.parseInt(txtborrowingid.getText().trim());

    // Format the return date (taken directly from jdcReserveDate)
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String returnDate = sdf.format(jdcReserveDate.getDate());

    // Show confirmation dialog
    int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to reserve this book?\nBook ID: " + bookId + 
            "\nReturn Date: " + returnDate, 
            "Confirm Reservation", 
            JOptionPane.YES_NO_OPTION);

    // If user selects "No", cancel the operation
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    // SQL query to insert reservation
    String sqlInsert = "INSERT INTO Reservations (username, book_id, reservation_date) VALUES (?, ?, ?)";
    pst = con.prepareStatement(sqlInsert);
    pst.setString(1, username);
    pst.setInt(2, bookId);
    pst.setString(3, returnDate);

    // Execute insertion
    int rowsInserted = pst.executeUpdate();

    if (rowsInserted > 0) {
        // Now update the BorrowedBooks table to set the status to 'Reserved'
        String sqlUpdate = "UPDATE BorrowedBooks SET status = 'Reserved' WHERE borrow_id = ? AND book_id = ?";
        pst = con.prepareStatement(sqlUpdate);
        pst.setInt(1, borrowingID);
        pst.setInt(2, bookId);

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Reservation added and book status updated to Reserved!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // **Insert Notification into Database**
            String sqlNotify = "INSERT INTO Notifications (username, message, status) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sqlNotify);
            pst.setString(1, username);
            pst.setString(2, "Your book (ID: " + bookId + ") has been reserved. Return date: " + returnDate + ".");
            pst.setString(3, "Unread"); // Status can be 'Unread' or 'Read'

            pst.executeUpdate();
        } else {
            JOptionPane.showMessageDialog(this, "Reservation added, but no matching borrowed book found to update status.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Failed to add reservation.", "Error", JOptionPane.ERROR_MESSAGE);
    } 

    showTable(); // Refresh the table

} catch (SQLException ex) {
    Logger.getLogger(memberReserve.class.getName()).log(Level.SEVERE, null, ex);
    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
}

        
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txtBookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookIDActionPerformed

    private void txtBookIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBookIDKeyPressed

    }//GEN-LAST:event_txtBookIDKeyPressed

    private void txtBookIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBookIDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookIDKeyReleased

    private void txtBookIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBookIDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookIDKeyTyped

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void tblBorrowedBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBorrowedBooksMouseClicked
     int selectedRow = tblBorrowedBooks.getSelectedRow();
if (selectedRow != -1) {
     txtborrowingid.setText(tblBorrowedBooks.getValueAt(selectedRow, 0).toString());

    txtBookID.setText(tblBorrowedBooks.getValueAt(selectedRow, 2).toString());

    // Convert the date from table (String) to Date format
    try {
        String dateStr = tblBorrowedBooks.getValueAt(selectedRow, 6).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Adjust format as needed
        Date date = sdf.parse(dateStr);
        jdcReserveDate.setDate(date); // Set date in JDateChooser
    } catch (ParseException ex) {
        JOptionPane.showMessageDialog(null, "Invalid date format!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    }//GEN-LAST:event_tblBorrowedBooksMouseClicked

    private void txtborrowingidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtborrowingidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtborrowingidActionPerformed

    private void txtborrowingidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtborrowingidKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtborrowingidKeyPressed

    private void txtborrowingidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtborrowingidKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtborrowingidKeyReleased

    private void txtborrowingidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtborrowingidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtborrowingidKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdcReserveDate;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSTableMetro tblBorrowedBooks;
    private javax.swing.JTextField txtBookID;
    private javax.swing.JTextField txtborrowingid;
    // End of variables declaration//GEN-END:variables
}
