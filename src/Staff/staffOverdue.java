/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Staff;

import Member.*;
import Staff.staffBook;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class staffOverdue extends javax.swing.JInternalFrame {

   
    public staffOverdue() {
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

    String query = "SELECT * FROM Fines WHERE fine_amount >0";

    try {
        pst = con.prepareStatement(query);
       
        rs = pst.executeQuery();

        while (rs.next()) {
            int fineID = rs.getInt("fine_id");
            String username = rs.getString("username");
            int bookID = rs.getInt("book_id");
            String dueDate = rs.getString("due_date");
            String returnDate = rs.getString("return_date");
            int daysOverdue = rs.getInt("days_overdue");
            double fineAmount = rs.getDouble("fine_amount");
            String status = rs.getString("status");

            model.addRow(new Object[]{fineID, username, bookID, dueDate, returnDate, daysOverdue, fineAmount, status});
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        finesTable = new rojerusan.RSTableMetro();

        jPanel1.setBackground(new java.awt.Color(240, 237, 226));
        jPanel1.setPreferredSize(new java.awt.Dimension(1238, 750));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 61, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Overdue and Fine");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        finesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fine ID", "Username", "Book ID", "Due Date", "Return Date", "Overdue Days", "Total Fine", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(457, 457, 457)
                .addComponent(jLabel1)
                .addContainerGap(577, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1124, Short.MAX_VALUE)
                    .addGap(57, 57, 57)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addContainerGap(658, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(134, 134, 134)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(83, Short.MAX_VALUE)))
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

    private void finesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_finesTableMouseClicked
      
    }//GEN-LAST:event_finesTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSTableMetro finesTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
