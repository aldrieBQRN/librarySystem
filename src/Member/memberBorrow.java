/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Member;

import Staff.staffBook;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author yeojvaldez
 */
public class memberBorrow extends javax.swing.JInternalFrame {

   
    private final String username;
    
    public  memberBorrow(String username) {
        initComponents();
        
        Connect();
        background();
       
        showTable();
        this.username = username;
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
        String query = "SELECT * FROM Books";
        pst = con.prepareStatement(query);
      
        rs = pst.executeQuery();

        DefaultTableModel bookModel = (DefaultTableModel) tblbook.getModel();
        bookModel.setRowCount(0); // Clear previous rows

        while (rs.next()) {
            int bookID = rs.getInt("book_id");
            String btitle = rs.getString("title");
            String author = rs.getString("author");
            String isbn = rs.getString("isbn");
            String genre = rs.getString("genre");
            int publicationYear = rs.getInt("publication_year");
            int quantity = rs.getInt("copies");
            String location = rs.getString("location");
          

            bookModel.addRow(new Object[]{bookID, btitle, author,isbn, genre, publicationYear, quantity, location});
        }
    } catch (SQLException ex) {
        Logger.getLogger(staffBook.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
   private void searchBooks() {
    String searchText = s1.getText().trim();
    String filter = cmbFilter.getSelectedItem().toString(); // Get selected filter

    String query;
    
    if (filter.equals("All") || searchText.isEmpty()) {
        // Show all books if "All" is selected or search text is empty
        query = "SELECT * FROM Books";
    } else {
        // Define the column based on the selected filter
        String column = "";
        switch (filter) {
            case "Title": column = "title"; break;
            case "Author": column = "author"; break;
            case "ISBN": column = "isbn"; break;
            case "Genre": column = "genre"; break;
            case "Year": column = "publication_year"; break;
            case "Location": column = "location"; break;
            default: column = "title"; // Default to title
        }

        query = "SELECT * FROM Books WHERE " + column + " LIKE ?";
    }

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "");
         PreparedStatement pst2 = conn.prepareStatement(query)) {

        // If filter is not "All", set search parameter
        if (!filter.equals("All") && !searchText.isEmpty()) {
            pst2.setString(1, "%" + searchText + "%"); // Wildcard search
        }

        ResultSet rs2 = pst2.executeQuery();
        
        DefaultTableModel model = (DefaultTableModel) tblbook.getModel();
        model.setRowCount(0); // Clear previous data

        while (rs2.next()) {
            Object[] row = {
                rs2.getInt("book_id"),
                rs2.getString("title"),
                rs2.getString("author"),
                rs2.getString("isbn"),
                rs2.getString("genre"),
                rs2.getInt("publication_year"),
                rs2.getInt("copies"),
                rs2.getString("location"),
                rs2.getString("status")
            };
            model.addRow(row);
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error fetching data: " + ex.getMessage());
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

        pnlhome = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtBookID = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jdcBorrowDate = new com.toedter.calendar.JDateChooser();
        jdcDueDate = new com.toedter.calendar.JDateChooser();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbook = new rojerusan.RSTableMetro();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        cmbFilter = new rojerusan.RSComboMetro();
        s1 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(240, 237, 226));
        setPreferredSize(new java.awt.Dimension(1238, 750));

        pnlhome.setBackground(new java.awt.Color(240, 237, 226));
        pnlhome.setPreferredSize(new java.awt.Dimension(1238, 701));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(50, 61, 46));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Borrowing Books");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 5, 0, new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel3.setText("BOOK ID");

        txtBookID.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        txtBookID.setPreferredSize(new java.awt.Dimension(78, 30));
        txtBookID.setSize(new java.awt.Dimension(78, 30));
        txtBookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBookIDActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel4.setText("BORROW DATE");

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel9.setText("RETURN DATE");

        jdcBorrowDate.setPreferredSize(new java.awt.Dimension(78, 30));

        jdcDueDate.setPreferredSize(new java.awt.Dimension(93, 30));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle1.setText("CONFIRM");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle2.setText("CLEAR");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        tblbook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Author", "ISBN", "Genre", "Year", "Quantity", "Location"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(50, 61, 46));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Available Books");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        jLabel5.setText("QUANTITY");

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

        cmbFilter.setBackground(new java.awt.Color(50, 61, 46));
        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "ID", "Title", "Author", "ISBN", "Genre", "Year", "Location" }));
        cmbFilter.setToolTipText("");
        cmbFilter.setColorArrow(new java.awt.Color(50, 61, 46));
        cmbFilter.setColorBorde(new java.awt.Color(240, 237, 226));
        cmbFilter.setColorFondo(new java.awt.Color(50, 61, 46));
        cmbFilter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cmbFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFilterActionPerformed(evt);
            }
        });

        s1.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        s1.setForeground(new java.awt.Color(153, 153, 153));
        s1.setText("Search here...");
        s1.setPreferredSize(new java.awt.Dimension(119, 35));
        s1.setSelectionColor(new java.awt.Color(50, 61, 46));
        s1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                s1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                s1FocusLost(evt);
            }
        });
        s1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                s1MouseEntered(evt);
            }
        });
        s1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s1ActionPerformed(evt);
            }
        });
        s1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                s1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                s1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlhomeLayout = new javax.swing.GroupLayout(pnlhome);
        pnlhome.setLayout(pnlhomeLayout);
        pnlhomeLayout.setHorizontalGroup(
            pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlhomeLayout.createSequentialGroup()
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlhomeLayout.createSequentialGroup()
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlhomeLayout.createSequentialGroup()
                                .addGap(277, 277, 277)
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
                                .addGap(452, 452, 452)
                                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(168, 168, 168))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlhomeLayout.createSequentialGroup()
                                .addComponent(cmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(s1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlhomeLayout.createSequentialGroup()
                                .addGap(406, 406, 406)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1098, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 70, Short.MAX_VALUE))
        );
        pnlhomeLayout.setVerticalGroup(
            pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlhomeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
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
                .addGap(50, 50, 50)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(s1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
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
            .addComponent(pnlhome, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBookIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBookIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBookIDActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
         txtBookID.setText("");
                txtQuantity.setText("");
                jdcBorrowDate.setDate(null);
                jdcDueDate.setDate(null);
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

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

    String memberName = this.username;  // Assuming username is stored in the class
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
            pst.setString(2, "Borrowed");
            pst.setInt(3, bookID);
            pst.executeUpdate();

            // Insert notification
            String notificationMessage = "You have successfully borrowed Book ID: " + bookID + ". Due date: " + dueDate;
            String insertNotificationQuery = "INSERT INTO Notifications (username, message, status) VALUES (?, ?, ?)";
            pst = con.prepareStatement(insertNotificationQuery);
            pst.setString(1, memberName);
            pst.setString(2, notificationMessage);
            pst.setString(3, "Unread");
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

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        
        
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void tblbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbookMouseClicked
       int selectedRow = tblbook.getSelectedRow();
    if (selectedRow != -1) {
        txtBookID.setText(tblbook.getValueAt(selectedRow, 0).toString());
        
    }
    }//GEN-LAST:event_tblbookMouseClicked

    private void txtQuantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityKeyTyped

    private void s1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s1FocusGained
        if(s1.getText().equals("Search here...")){
            s1.setText("");
            setForeground(new Color(152,153,153));
        }
    }//GEN-LAST:event_s1FocusGained

    private void s1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_s1FocusLost
        if(s1.getText().equals("")){
            s1.setText("Search here...");
            setForeground(new Color(152,153,153));
        }
    }//GEN-LAST:event_s1FocusLost

    private void s1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s1ActionPerformed

    private void s1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        searchBooks(); // Call your search function
    }
    }//GEN-LAST:event_s1KeyPressed

    private void s1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_s1MouseEntered
        
    }//GEN-LAST:event_s1MouseEntered

    private void s1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_s1KeyTyped
     
    }//GEN-LAST:event_s1KeyTyped

    private void cmbFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFilterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSComboMetro cmbFilter;
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
    private javax.swing.JTextField s1;
    private rojerusan.RSTableMetro tblbook;
    private javax.swing.JTextField txtBookID;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
