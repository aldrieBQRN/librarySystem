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
public class staffReservation extends javax.swing.JInternalFrame {

   
    public staffReservation() {
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
        String sql = "SELECT reservation_id, username, book_id, reservation_date, status FROM Reservations";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();

        // Get table model
        DefaultTableModel model = (DefaultTableModel) reservationsTable.getModel();
        model.setRowCount(0); // Clear existing data

        // Populate table with data from result set
        while (rs.next()) {
            Object[] row = {
                rs.getInt("reservation_id"),
                rs.getString("username"),
                rs.getInt("book_id"),
                rs.getDate("reservation_date"),
                rs.getString("status")
            };
            model.addRow(row);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
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
        reservationsTable = new rojerusan.RSTableMetro();

        jPanel1.setBackground(new java.awt.Color(240, 237, 226));
        jPanel1.setPreferredSize(new java.awt.Dimension(1238, 750));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 61, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reservation History");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        reservationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reservation ID", "Username", "Book ID", "Reservation Date", "Status"
            }
        ));
        reservationsTable.setColorBackgoundHead(new java.awt.Color(255, 255, 255));
        reservationsTable.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        reservationsTable.setColorBordeHead(new java.awt.Color(255, 255, 255));
        reservationsTable.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        reservationsTable.setColorFilasForeground1(new java.awt.Color(61, 58, 87));
        reservationsTable.setColorFilasForeground2(new java.awt.Color(61, 58, 87));
        reservationsTable.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        reservationsTable.setColorSelBackgound(new java.awt.Color(61, 58, 87));
        reservationsTable.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        reservationsTable.setFuenteFilas(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reservationsTable.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reservationsTable.setGridColor(new java.awt.Color(204, 204, 204));
        reservationsTable.setRowHeight(30);
        reservationsTable.setSelectionBackground(new java.awt.Color(61, 58, 87));
        reservationsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        reservationsTable.setShowGrid(false);
        reservationsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reservationsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(reservationsTable);

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
                        .addGap(457, 457, 457)
                        .addComponent(jLabel1)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
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

    private void reservationsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reservationsTableMouseClicked
        
    }//GEN-LAST:event_reservationsTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSTableMetro reservationsTable;
    // End of variables declaration//GEN-END:variables
}
