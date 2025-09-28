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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yeojvaldez
 */
public class memberHistory extends javax.swing.JInternalFrame {

   private String membername;
    
    public memberHistory(String username) {
        initComponents();
        this.membername = username;
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
       
        String query = "SELECT * FROM BorrowedBooks WHERE username = ?";
        pst = con.prepareStatement(query);
        pst.setString(1, this.membername);
      
      
        rs = pst.executeQuery();


        DefaultTableModel borrowedBooksModel = (DefaultTableModel) tblBorrowedBooks.getModel();
        borrowedBooksModel.setRowCount(0); // Clear previous rows

        while (rs.next()) {
            int borrowID = rs.getInt("borrow_id");
           
            int bookID = rs.getInt("book_id");
            int quantity = rs.getInt("quantity");
            Date borrowDate = rs.getDate("borrow_date");
            Date dueDate = rs.getDate("due_date");
            String status = rs.getString("status");
            

            borrowedBooksModel.addRow(new Object[]{borrowID, bookID, quantity, borrowDate, dueDate, status});
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBorrowedBooks = new rojerusan.RSTableMetro();

        jPanel1.setBackground(new java.awt.Color(240, 237, 226));
        jPanel1.setPreferredSize(new java.awt.Dimension(1238, 750));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 61, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Borrowing Books");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        tblBorrowedBooks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Borrow ID", "Book ID", "Quantity", "Borrow Date", "Return Date", "Status"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(jLabel1)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
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

    private void tblBorrowedBooksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBorrowedBooksMouseClicked
        
    }//GEN-LAST:event_tblBorrowedBooksMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSTableMetro tblBorrowedBooks;
    // End of variables declaration//GEN-END:variables
}
