
package Member;

import LoginAcc.SigninAcc;
import Staff.staffBook;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;


public class memberHome extends javax.swing.JFrame {

    private final String memberName;
    
    public memberHome(String username) {
         
        initComponents();
        this.memberName = username;
        txtname.setText(username);
        Connect();
         countTotalBooks();
         countAvailableBooks();
         countUserReservations();
         countUserBorrowedBooks();
         fetchUnreadNotifications();
      
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
    
     public final void countTotalBooks() {
    String query = "SELECT COUNT(*) FROM Books";
    
    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            int totalBooks = rs.getInt(1);
            txtTotalBooks1.setText(String.valueOf(totalBooks));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching book count: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

public final void countAvailableBooks() {
    String query = "SELECT COUNT(*) FROM Books WHERE copies > 0";
    
    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            int availableBooks = rs.getInt(1);
            txtAvailableBooks.setText(String.valueOf(availableBooks));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching available books count: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
    public final void countUserBorrowedBooks() {
        String name = this.memberName;
    String query = "SELECT COUNT(*) FROM BorrowedBooks WHERE username = ? AND (status = 'Borrowed' OR status = 'Reserved')";
    
    try {
        pst = con.prepareStatement(query);
        pst.setString(1, name); // Set the username parameter
        rs = pst.executeQuery();
        
        if (rs.next()) {
            int totalBorrowed = rs.getInt(1); // Get the count result
            txtBorrowedBooks.setText(String.valueOf(totalBorrowed)); // Display in text field
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching borrowed book count: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
   
    
    public final void countUserReservations() {
        

        try {
            
        String name = this.memberName;
        String query = "SELECT COUNT(*) FROM Reservations WHERE username = ? AND status != 'Complete'";
            pst = con.prepareStatement(query);
            pst.setString(1, name); // Set the username parameter
            rs = pst.executeQuery();

            if (rs.next()) {
                int totalReservations = rs.getInt(1); // Get the count result
                txtUserReservations.setText(String.valueOf(totalReservations)); // Display in text field
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching reservation count: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
}
    
    private void fetchUnreadNotifications() {
    try {
        DefaultTableModel model = (DefaultTableModel) tblNotifications.getModel();
        model.setRowCount(0); // Clear table

        String query = "SELECT message FROM Notifications WHERE username = ? AND status = 'Unread'";
        pst = con.prepareStatement(query);
        pst.setString(1, this.memberName);
        rs = pst.executeQuery();

        while (rs.next()) {
           
            String message = rs.getString("message");
           

            model.addRow(new Object[]{ message});
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching notifications: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
    }
}






    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtname = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTotalBooks = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlhm = new javax.swing.JPanel();
        txthome = new javax.swing.JLabel();
        pnlborrow = new javax.swing.JPanel();
        txtborrow = new javax.swing.JLabel();
        pnlreturn = new javax.swing.JPanel();
        txtreturn = new javax.swing.JLabel();
        pnlreserve = new javax.swing.JPanel();
        txtreserve = new javax.swing.JLabel();
        pnlreservation = new javax.swing.JPanel();
        txtreservaton = new javax.swing.JLabel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        pnlhistory = new javax.swing.JPanel();
        txthistory = new javax.swing.JLabel();
        pnloverdue = new javax.swing.JPanel();
        txtoverdue = new javax.swing.JLabel();
        panelhome = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtAvailableBooks = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtBorrowedBooks = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtUserReservations = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNotifications = new rojerusan.RSTableMetro();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtTotalBooks1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1440, 830));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1440, 80));

        jLabel5.setBackground(new java.awt.Color(224, 153, 103));
        jLabel5.setFont(new java.awt.Font("Arial Unicode MS", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(224, 153, 103));
        jLabel5.setText("Libra");

        jLabel6.setBackground(new java.awt.Color(50, 61, 46));
        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(50, 61, 46));
        jLabel6.setText("Ry");

        txtname.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        txtname.setForeground(new java.awt.Color(50, 61, 46));
        txtname.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtname.setText("Default Name");

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(50, 61, 46));

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TOTAL BOOKS");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtTotalBooks.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtTotalBooks.setForeground(new java.awt.Color(44, 42, 61));
        txtTotalBooks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTotalBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalBooks, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(963, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(txtname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(50, 61, 46));
        jPanel4.setForeground(new java.awt.Color(50, 61, 46));

        pnlhm.setBackground(new java.awt.Color(50, 61, 46));

        txthome.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txthome.setForeground(new java.awt.Color(255, 255, 255));
        txthome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txthome.setText("HOME");
        txthome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txthomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txthomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txthomeMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlhmLayout = new javax.swing.GroupLayout(pnlhm);
        pnlhm.setLayout(pnlhmLayout);
        pnlhmLayout.setHorizontalGroup(
            pnlhmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(pnlhmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlhmLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txthome, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlhmLayout.setVerticalGroup(
            pnlhmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnlhmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlhmLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txthome, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlborrow.setBackground(new java.awt.Color(50, 61, 46));

        txtborrow.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txtborrow.setForeground(new java.awt.Color(255, 255, 255));
        txtborrow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtborrow.setText("BORROW");
        txtborrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtborrowMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtborrowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtborrowMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlborrowLayout = new javax.swing.GroupLayout(pnlborrow);
        pnlborrow.setLayout(pnlborrowLayout);
        pnlborrowLayout.setHorizontalGroup(
            pnlborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(pnlborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlborrowLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtborrow, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlborrowLayout.setVerticalGroup(
            pnlborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnlborrowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlborrowLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtborrow, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlreturn.setBackground(new java.awt.Color(50, 61, 46));

        txtreturn.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txtreturn.setForeground(new java.awt.Color(255, 255, 255));
        txtreturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtreturn.setText("RETURN");
        txtreturn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtreturnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtreturnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtreturnMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlreturnLayout = new javax.swing.GroupLayout(pnlreturn);
        pnlreturn.setLayout(pnlreturnLayout);
        pnlreturnLayout.setHorizontalGroup(
            pnlreturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(pnlreturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlreturnLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtreturn, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlreturnLayout.setVerticalGroup(
            pnlreturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnlreturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlreturnLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtreturn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlreserve.setBackground(new java.awt.Color(50, 61, 46));

        txtreserve.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txtreserve.setForeground(new java.awt.Color(255, 255, 255));
        txtreserve.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtreserve.setText("RESERVE");
        txtreserve.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtreserveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtreserveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtreserveMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlreserveLayout = new javax.swing.GroupLayout(pnlreserve);
        pnlreserve.setLayout(pnlreserveLayout);
        pnlreserveLayout.setHorizontalGroup(
            pnlreserveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(pnlreserveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlreserveLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtreserve, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlreserveLayout.setVerticalGroup(
            pnlreserveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnlreserveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlreserveLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtreserve, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlreservation.setBackground(new java.awt.Color(50, 61, 46));

        txtreservaton.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txtreservaton.setForeground(new java.awt.Color(255, 255, 255));
        txtreservaton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtreservaton.setText("RESERVATION");
        txtreservaton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtreservatonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtreservatonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtreservatonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlreservationLayout = new javax.swing.GroupLayout(pnlreservation);
        pnlreservation.setLayout(pnlreservationLayout);
        pnlreservationLayout.setHorizontalGroup(
            pnlreservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(pnlreservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlreservationLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtreservaton, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlreservationLayout.setVerticalGroup(
            pnlreservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnlreservationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlreservationLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtreservaton, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonRectangle2.setForeground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle2.setText("logout");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        pnlhistory.setBackground(new java.awt.Color(50, 61, 46));

        txthistory.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txthistory.setForeground(new java.awt.Color(255, 255, 255));
        txthistory.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txthistory.setText("HISTORY");
        txthistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txthistoryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txthistoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txthistoryMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlhistoryLayout = new javax.swing.GroupLayout(pnlhistory);
        pnlhistory.setLayout(pnlhistoryLayout);
        pnlhistoryLayout.setHorizontalGroup(
            pnlhistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(pnlhistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlhistoryLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txthistory, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlhistoryLayout.setVerticalGroup(
            pnlhistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnlhistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlhistoryLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txthistory, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnloverdue.setBackground(new java.awt.Color(50, 61, 46));

        txtoverdue.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txtoverdue.setForeground(new java.awt.Color(255, 255, 255));
        txtoverdue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtoverdue.setText("OVERDUE");
        txtoverdue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtoverdueMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtoverdueMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtoverdueMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnloverdueLayout = new javax.swing.GroupLayout(pnloverdue);
        pnloverdue.setLayout(pnloverdueLayout);
        pnloverdueLayout.setHorizontalGroup(
            pnloverdueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(pnloverdueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnloverdueLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtoverdue, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnloverdueLayout.setVerticalGroup(
            pnloverdueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnloverdueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnloverdueLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtoverdue, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlhm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlborrow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlreturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlreserve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlreservation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlhistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnloverdue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(pnlhm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlborrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlreturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnloverdue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlreserve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlreservation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlhistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        panelhome.setBackground(new java.awt.Color(240, 237, 226));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(50, 61, 46));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("AVAILABLE BOOK");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtAvailableBooks.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtAvailableBooks.setForeground(new java.awt.Color(44, 42, 61));
        txtAvailableBooks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAvailableBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAvailableBooks, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(50, 61, 46));

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("YOUR BORROW");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtBorrowedBooks.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtBorrowedBooks.setForeground(new java.awt.Color(44, 42, 61));
        txtBorrowedBooks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBorrowedBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBorrowedBooks, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jPanel25.setBackground(new java.awt.Color(50, 61, 46));

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("YOUR RESERVATION");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtUserReservations.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtUserReservations.setForeground(new java.awt.Color(44, 42, 61));
        txtUserReservations.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUserReservations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserReservations, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        jLabel1.setText("Notification");

        tblNotifications.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Messages"
            }
        ));
        tblNotifications.setColorBackgoundHead(new java.awt.Color(255, 255, 255));
        tblNotifications.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tblNotifications.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tblNotifications.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblNotifications.setColorFilasForeground1(new java.awt.Color(61, 58, 87));
        tblNotifications.setColorFilasForeground2(new java.awt.Color(61, 58, 87));
        tblNotifications.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tblNotifications.setColorSelBackgound(new java.awt.Color(61, 58, 87));
        tblNotifications.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        tblNotifications.setFuenteFilas(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNotifications.setFuenteFilasSelect(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNotifications.setGridColor(new java.awt.Color(204, 204, 204));
        tblNotifications.setRowHeight(30);
        tblNotifications.setSelectionBackground(new java.awt.Color(61, 58, 87));
        tblNotifications.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblNotifications.setShowGrid(false);
        tblNotifications.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNotificationsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNotifications);

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle3.setText("make as read");
        rSMaterialButtonRectangle3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonRectangle3MouseClicked(evt);
            }
        });
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(470, 470, 470)
                        .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        jPanel31.setBackground(new java.awt.Color(50, 61, 46));

        jLabel26.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("TOTAL BOOKS");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtTotalBooks1.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtTotalBooks1.setForeground(new java.awt.Color(44, 42, 61));
        txtTotalBooks1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTotalBooks1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalBooks1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelhomeLayout = new javax.swing.GroupLayout(panelhome);
        panelhome.setLayout(panelhomeLayout);
        panelhomeLayout.setHorizontalGroup(
            panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelhomeLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelhomeLayout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(82, 82, 82)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68))
        );
        panelhomeLayout.setVerticalGroup(
            panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelhomeLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(panelhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panelhome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelhome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void txtborrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtborrowMouseClicked
        String username = this.memberName;
        memberBorrow frame = new memberBorrow(username);

        frame.setSize(1236, 750); // Set exact size
        frame.setPreferredSize(new Dimension(1236, 750));

        panelhome.removeAll();
        panelhome.add(frame);
        frame.setVisible(true);

        panelhome.revalidate();
        panelhome.repaint();

    }//GEN-LAST:event_txtborrowMouseClicked

    private void txtreturnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreturnMouseClicked
        String name  = this.memberName;
        
        returnBooks frame = new returnBooks(name);
        panelhome.removeAll();
        panelhome.add(frame);
        frame.setVisible(true);
    }//GEN-LAST:event_txtreturnMouseClicked

    private void txtreserveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreserveMouseClicked
        String name  = this.memberName;
        memberReserve memberReserveFrame = new memberReserve(name);
        panelhome.removeAll();
        panelhome.add(memberReserveFrame);
        memberReserveFrame.setVisible(true);
    }//GEN-LAST:event_txtreserveMouseClicked

    private void txtreservatonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreservatonMouseClicked
        String name  = this.memberName;
        memberReservation Frame = new memberReservation(name);
        panelhome.removeAll();
        panelhome.add(Frame);

        Frame.setVisible(true);
    }//GEN-LAST:event_txtreservatonMouseClicked

    private void txtreservatonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreservatonMouseEntered
        pnlreservation.setBackground(new Color(204, 204, 204)); 
       txtreservaton.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txtreservatonMouseEntered

    private void tblNotificationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNotificationsMouseClicked
        
        
    }//GEN-LAST:event_tblNotificationsMouseClicked

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            window.dispose();
        }
        new SigninAcc().setVisible(true);
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void txthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthomeMouseClicked
        
        
        panelhome.removeAll(); 
        panelhome.revalidate();
        panelhome.repaint();
        
         countTotalBooks();
         countAvailableBooks();
         countUserReservations();
         countUserBorrowedBooks();
         fetchUnreadNotifications();
         
          panelhome.add(jPanel30); 
          panelhome.add(jPanel13); 
           panelhome.add(jPanel19); 
           panelhome.add(jPanel23); 
           panelhome.add(jPanel7); 
           
           panelhome.revalidate();
        panelhome.repaint();
    }//GEN-LAST:event_txthomeMouseClicked

    private void txthistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthistoryMouseClicked
        String name  = this.memberName;
         memberHistory Frame = new memberHistory(name);
        panelhome.removeAll();
        panelhome.add(Frame);

        Frame.setVisible(true);
    }//GEN-LAST:event_txthistoryMouseClicked

    private void txthistoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthistoryMouseEntered
        pnlhistory.setBackground(new Color(204, 204, 204)); 
       txthistory.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txthistoryMouseEntered

    private void txtoverdueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtoverdueMouseClicked
         String name  = this.memberName;
         memberFine Frame = new memberFine(name);
        panelhome.removeAll();
        panelhome.add(Frame);

        Frame.setVisible(true);
    }//GEN-LAST:event_txtoverdueMouseClicked

    private void txthomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthomeMouseEntered
       pnlhm.setBackground(new Color(204, 204, 204)); 
       txthome.setForeground(new Color(50,61,46));
        
    }//GEN-LAST:event_txthomeMouseEntered

    private void txthomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthomeMouseExited
         pnlhm.setBackground(new Color(50,61,46)); 
       txthome.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txthomeMouseExited

    private void txtborrowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtborrowMouseEntered
        pnlborrow.setBackground(new Color(204, 204, 204)); 
       txtborrow.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txtborrowMouseEntered

    private void txtborrowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtborrowMouseExited
       pnlborrow.setBackground(new Color(50,61,46)); 
       txtborrow.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txtborrowMouseExited

    private void txtreturnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreturnMouseEntered
      pnlreturn.setBackground(new Color(204, 204, 204)); 
       txtreturn.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txtreturnMouseEntered

    private void txtreturnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreturnMouseExited
         pnlreturn.setBackground(new Color(50,61,46)); 
       txtreturn.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txtreturnMouseExited

    private void txtoverdueMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtoverdueMouseEntered
       pnloverdue.setBackground(new Color(204, 204, 204)); 
       txtoverdue.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txtoverdueMouseEntered

    private void txtoverdueMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtoverdueMouseExited
         pnloverdue.setBackground(new Color(50,61,46)); 
       txtoverdue.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txtoverdueMouseExited

    private void txtreserveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreserveMouseEntered
       pnlreserve.setBackground(new Color(204, 204, 204)); 
       txtreserve.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txtreserveMouseEntered

    private void txtreserveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreserveMouseExited
         pnlreserve.setBackground(new Color(50,61,46)); 
       txtreserve.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txtreserveMouseExited

    private void txtreservatonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreservatonMouseExited
       pnlreservation.setBackground(new Color(50,61,46)); 
       txtreservaton.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txtreservatonMouseExited

    private void txthistoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthistoryMouseExited
        pnlhistory.setBackground(new Color(50,61,46)); 
       txthistory.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txthistoryMouseExited

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
     
    int selectedRow = tblNotifications.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a notification to mark as read.", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Get the selected message from the table
    String selectedMessage = tblNotifications.getValueAt(selectedRow, 0).toString();
    
    String query = "UPDATE Notifications SET status = 'Read' WHERE message = ? AND username = ?";

    try (PreparedStatement pst2 = con.prepareStatement(query)) {
        pst2.setString(1, selectedMessage);
        pst2.setString(2, this.memberName);

        int updatedRows = pst2.executeUpdate();
        
        if (updatedRows > 0) {
            JOptionPane.showMessageDialog(this, "Notification marked as read.", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Refresh notifications
            fetchUnreadNotifications();
        } else {
            JOptionPane.showMessageDialog(this, "Error: Could not update notification.", "Update Failed", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void rSMaterialButtonRectangle3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            String memberName = "Default Name";
            new memberHome(memberName).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelhome;
    private javax.swing.JPanel pnlborrow;
    private javax.swing.JPanel pnlhistory;
    private javax.swing.JPanel pnlhm;
    private javax.swing.JPanel pnloverdue;
    private javax.swing.JPanel pnlreservation;
    private javax.swing.JPanel pnlreserve;
    private javax.swing.JPanel pnlreturn;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private rojerusan.RSTableMetro tblNotifications;
    private javax.swing.JLabel txtAvailableBooks;
    private javax.swing.JLabel txtBorrowedBooks;
    private javax.swing.JLabel txtTotalBooks;
    private javax.swing.JLabel txtTotalBooks1;
    private javax.swing.JLabel txtUserReservations;
    private javax.swing.JLabel txtborrow;
    private javax.swing.JLabel txthistory;
    private javax.swing.JLabel txthome;
    private javax.swing.JLabel txtname;
    private javax.swing.JLabel txtoverdue;
    private javax.swing.JLabel txtreservaton;
    private javax.swing.JLabel txtreserve;
    private javax.swing.JLabel txtreturn;
    // End of variables declaration//GEN-END:variables
}
