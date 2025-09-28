/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Staff;

import java.awt.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.data.category.*;

import org.jfree.ui.RectangleEdge; 
import LoginAcc.SigninAcc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.HorizontalAlignment;



/**
 *
 * @author yeojvaldez
 */
public class staffHome extends javax.swing.JFrame {

  
    public staffHome() {
        
        initComponents();
        Connect();
        
        countTotalBooks();  
    countAvailableBooks();  
    getMostBorrowedBook();  
    getLowStockBooks();  
    countTotalOverdueBooks();  
    computeTotalFine();  
    countTotalUsers();  
    countTotalReservations(); 
    showPieChart();
    showLineChart();
    showBarChart();
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
    
  


public final void showPieChart() {
    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "");
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        String query = "SELECT book_id, COUNT(*) AS borrow_count FROM BorrowedBooks GROUP BY book_id";
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        boolean hasData = false;

        // Populate the pie chart
        while (rs.next()) {
            int bookID = rs.getInt("book_id");
            int borrowCount = rs.getInt("borrow_count");

            pieDataset.setValue("Book " + bookID, borrowCount);
            hasData = true;
        }

        // If no data, show default message
        if (!hasData) {
            pieDataset.setValue("No Borrowed Books", 1);
        }

        // Create pie chart
        JFreeChart pieChartObject = ChartFactory.createPieChart(null, pieDataset, true, true, false);
        PiePlot piePlot = (PiePlot) pieChartObject.getPlot();
        piePlot.setBackgroundPaint(Color.WHITE);
        piePlot.setOutlineVisible(false);

        // Centering the legend
        pieChartObject.getLegend().setFrame(BlockBorder.NONE);
        pieChartObject.getLegend().setPosition(RectangleEdge.BOTTOM); // Move to bottom
        pieChartObject.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER); // Center it

        // Set the pie chart panel
        ChartPanel chartPanel = new ChartPanel(pieChartObject);
        chartPanel.setPreferredSize(new java.awt.Dimension(290, 251));

        chartAvailable.removeAll();
        chartAvailable.setLayout(new BorderLayout());
        chartAvailable.add(chartPanel, BorderLayout.CENTER);
        chartAvailable.validate();
        chartAvailable.repaint();

        rs.close();
        pst.close();
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(staffHome.class.getName()).log(Level.SEVERE, null, ex);
    }
}


public final void showLineChart() {
    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "");
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Query to sum fines per month
        String query = "SELECT MONTHNAME(due_date) AS month, SUM(fine_amount) AS total_fine " +
                       "FROM Fines GROUP BY MONTH(due_date) ORDER BY MONTH(due_date)";
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        boolean hasData = false;

        // Populate the dataset
        while (rs.next()) {
            String month = rs.getString("month");
            double totalFine = rs.getDouble("total_fine");
            dataset.setValue(totalFine, "Fine Amount", month);
            hasData = true;
        }

        // If no data, show a default message
        if (!hasData) {
            dataset.setValue(0, "Fine Amount", "No Data");
        }

        // Create line chart WITHOUT a title
        JFreeChart lineChart = ChartFactory.createLineChart(null, "Month", "Total Fine", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        // Customize the chart
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);

        // Customize the line appearance
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) plot.getRenderer();
        lineRenderer.setSeriesPaint(0, new Color(204, 0, 51));

        // Center the legend at the bottom
        lineChart.getLegend().setFrame(BlockBorder.NONE);
        lineChart.getLegend().setPosition(RectangleEdge.BOTTOM);

        // Display the chart
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(350, 300));

        panelLineChart.removeAll();
        panelLineChart.setLayout(new BorderLayout());
        panelLineChart.add(chartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
        panelLineChart.repaint();

        rs.close();
        pst.close();
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(staffHome.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public final void showBarChart() {
    try {
        // Establish database connection
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryManagementSystem", "root", "");

        // Query to count reservations per month
        String query = "SELECT DATE_FORMAT(reservation_date, '%M') AS month, COUNT(*) AS reservation_count " +
                       "FROM Reservations GROUP BY month ORDER BY STR_TO_DATE(month, '%M')";

        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        // Create dataset for the bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        while (rs.next()) {
            String month = rs.getString("month"); // Month name
            int count = rs.getInt("reservation_count"); // Count of reservations

            dataset.setValue(count, "Reservations", month);
        }

        // Create bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                null, // No title
                "Month", // X-axis label
                "Reservations", // Y-axis label
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        // Customize chart appearance
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setSeriesPaint(0, new Color(204, 0, 51)); // Red color for bars

        // Create chart panel
        ChartPanel barChartPanel = new ChartPanel(chart);
        barChartPanel.setPreferredSize(new Dimension(350, 300));

        // Display chart in the panel
        jPanel14.removeAll();
        jPanel14.setLayout(new BorderLayout());
        jPanel14.add(barChartPanel, BorderLayout.CENTER);
        jPanel14.validate();
        jPanel14.repaint();

        // Close resources
        rs.close();
        pst.close();
        con.close();
    } catch (SQLException ex) {
        Logger.getLogger(staffHome.class.getName()).log(Level.SEVERE, null, ex);
    }
}




    
    public final void countTotalBooks() {
    String query = "SELECT COUNT(*) FROM Books";
    
    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            int totalBooks = rs.getInt(1);
            txtTotalBooks.setText(String.valueOf(totalBooks));
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
public final void getMostBorrowedBook() {
    String query = "SELECT book_id FROM BorrowedBooks " +
                   "GROUP BY book_id " +
                   "ORDER BY COUNT(book_id) DESC " +
                   "LIMIT 1";

    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        if (rs.next()) {
            int mostBorrowedBookId = rs.getInt("book_id");
            txtMostBorrowedBook.setText("Book " + mostBorrowedBookId);
        } else {
            txtMostBorrowedBook.setText("N/A");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching most borrowed book: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


public final void getLowStockBooks() {
    String query = "SELECT book_id FROM Books WHERE copies <= 5 ORDER BY copies ASC LIMIT 1";

    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        if (rs.next()) {
            int lowStockBookId = rs.getInt("book_id");
            txtLowStockBook.setText("Book " + lowStockBookId);
        } else {
            txtLowStockBook.setText("N/A");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching low stock book: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}





public final void countTotalOverdueBooks() {
    String query = "SELECT COUNT(*) FROM Fines WHERE status = 'Pending'";

    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        if (rs.next()) {
            int overdueBooks = rs.getInt(1);
            txtOverdueBooks.setText(String.valueOf(overdueBooks));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching overdue books count: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


public final void computeTotalFine() {
    String query = "SELECT SUM(fine_amount) FROM Fines";

    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();

        if (rs.next()) {
            double totalFine = rs.getDouble(1); // Get the sum of fine_amount
            txtTotalFine.setText(String.valueOf(totalFine)); // Set value in text field
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error computing total fine: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


public final void countTotalUsers() {
    String query = "SELECT COUNT(*) FROM Users";
    
    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            int totalUsers = rs.getInt(1);
            txtTotalUsers.setText(String.valueOf(totalUsers));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching user count: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

public final void countTotalReservations() {
    String query = "SELECT COUNT(*) FROM Reservations WHERE Status != 'Complete'";
    
    try {
        pst = con.prepareStatement(query);
        rs = pst.executeQuery();
        
        if (rs.next()) {
            int totalReservations = rs.getInt(1);
            txtTotalReservations.setText(String.valueOf(totalReservations));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching total reservations: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}




   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pnlhm = new javax.swing.JPanel();
        txthome = new javax.swing.JLabel();
        pnlbook = new javax.swing.JPanel();
        txtbook = new javax.swing.JLabel();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        pnluser = new javax.swing.JPanel();
        txtuser = new javax.swing.JLabel();
        pnlreserve = new javax.swing.JPanel();
        txtreserve = new javax.swing.JLabel();
        pnlhistory = new javax.swing.JPanel();
        txthistory = new javax.swing.JLabel();
        pnloverdue = new javax.swing.JPanel();
        txtoverdue = new javax.swing.JLabel();
        pnlhome = new javax.swing.JPanel();
        chartAvailable = new javax.swing.JPanel();
        panelLineChart = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTotalBooks = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtAvailableBooks = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtMostBorrowedBook = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtLowStockBook = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtTotalReservations = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtTotalUsers = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtTotalFine = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtOverdueBooks = new javax.swing.JLabel();

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

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(50, 61, 46));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Staff");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-user-32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1012, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGap(0, 192, Short.MAX_VALUE)
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

        pnlbook.setBackground(new java.awt.Color(50, 61, 46));

        txtbook.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txtbook.setForeground(new java.awt.Color(255, 255, 255));
        txtbook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtbook.setText("BOOKS");
        txtbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtbookMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtbookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtbookMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlbookLayout = new javax.swing.GroupLayout(pnlbook);
        pnlbook.setLayout(pnlbookLayout);
        pnlbookLayout.setHorizontalGroup(
            pnlbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(pnlbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlbookLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtbook, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlbookLayout.setVerticalGroup(
            pnlbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnlbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlbookLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtbook, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(255, 255, 255));
        rSMaterialButtonRectangle2.setForeground(new java.awt.Color(50, 61, 46));
        rSMaterialButtonRectangle2.setText("LOgOUT");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        pnluser.setBackground(new java.awt.Color(50, 61, 46));

        txtuser.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txtuser.setForeground(new java.awt.Color(255, 255, 255));
        txtuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtuser.setText("USER");
        txtuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtuserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtuserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtuserMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnluserLayout = new javax.swing.GroupLayout(pnluser);
        pnluser.setLayout(pnluserLayout);
        pnluserLayout.setHorizontalGroup(
            pnluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 192, Short.MAX_VALUE)
            .addGroup(pnluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnluserLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtuser, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnluserLayout.setVerticalGroup(
            pnluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(pnluserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnluserLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(txtuser, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pnlreserve.setBackground(new java.awt.Color(50, 61, 46));

        txtreserve.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 13)); // NOI18N
        txtreserve.setForeground(new java.awt.Color(255, 255, 255));
        txtreserve.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtreserve.setText("RESERVATION");
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
                    .addComponent(pnlhm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlbook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnluser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlreserve, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGap(103, 103, 103)
                .addComponent(pnlhm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlbook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnluser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnloverdue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlreserve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlhistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pnlhome.setBackground(new java.awt.Color(240, 237, 226));

        chartAvailable.setPreferredSize(new java.awt.Dimension(350, 300));
        chartAvailable.setRequestFocusEnabled(false);

        javax.swing.GroupLayout chartAvailableLayout = new javax.swing.GroupLayout(chartAvailable);
        chartAvailable.setLayout(chartAvailableLayout);
        chartAvailableLayout.setHorizontalGroup(
            chartAvailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        chartAvailableLayout.setVerticalGroup(
            chartAvailableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        panelLineChart.setPreferredSize(new java.awt.Dimension(350, 300));

        javax.swing.GroupLayout panelLineChartLayout = new javax.swing.GroupLayout(panelLineChart);
        panelLineChart.setLayout(panelLineChartLayout);
        panelLineChartLayout.setHorizontalGroup(
            panelLineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        panelLineChartLayout.setVerticalGroup(
            panelLineChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

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
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel18.setBackground(new java.awt.Color(50, 61, 46));

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("AVAILABLE BOOKS");

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

        jPanel14.setPreferredSize(new java.awt.Dimension(350, 300));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(50, 61, 46));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("MOST BORROW");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtMostBorrowedBook.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtMostBorrowedBook.setForeground(new java.awt.Color(44, 42, 61));
        txtMostBorrowedBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtMostBorrowedBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMostBorrowedBook, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(50, 61, 46));

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("LOW STOCK");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtLowStockBook.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtLowStockBook.setForeground(new java.awt.Color(44, 42, 61));
        txtLowStockBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtLowStockBook, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLowStockBook, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        jPanel26.setBackground(new java.awt.Color(50, 61, 46));

        jLabel24.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("RESERVE BOOK");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtTotalReservations.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtTotalReservations.setForeground(new java.awt.Color(44, 42, 61));
        txtTotalReservations.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTotalReservations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalReservations, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jPanel28.setBackground(new java.awt.Color(50, 61, 46));

        jLabel25.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("USERS");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtTotalUsers.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtTotalUsers.setForeground(new java.awt.Color(44, 42, 61));
        txtTotalUsers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTotalUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jPanel30.setBackground(new java.awt.Color(50, 61, 46));

        jLabel26.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("FEES ACCUMULATED");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtTotalFine.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtTotalFine.setForeground(new java.awt.Color(44, 42, 61));
        txtTotalFine.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTotalFine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalFine, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jPanel32.setBackground(new java.awt.Color(50, 61, 46));

        jLabel27.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("OVERDUE");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtOverdueBooks.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        txtOverdueBooks.setForeground(new java.awt.Color(44, 42, 61));
        txtOverdueBooks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtOverdueBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOverdueBooks, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlhomeLayout = new javax.swing.GroupLayout(pnlhome);
        pnlhome.setLayout(pnlhomeLayout);
        pnlhomeLayout.setHorizontalGroup(
            pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlhomeLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addComponent(chartAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(panelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(86, 86, 86)
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(79, 79, 79)
                        .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlhomeLayout.setVerticalGroup(
            pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlhomeLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlhomeLayout.createSequentialGroup()
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addGroup(pnlhomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLineChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
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
                        .addComponent(pnlhome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlhome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbookMouseClicked
        staffBook Frame = new staffBook();   
        pnlhome.removeAll();
        pnlhome.add(Frame);
        Frame.setVisible(true);
    }//GEN-LAST:event_txtbookMouseClicked

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            window.dispose();
        }
        new SigninAcc().setVisible(true);
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void txtuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtuserMouseClicked
        
         staffUser Frame = new staffUser();   
        pnlhome.removeAll();
        pnlhome.add(Frame);
        Frame.setVisible(true);
    }//GEN-LAST:event_txtuserMouseClicked

    private void txtreserveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtreserveMouseClicked
         staffReservation Frame = new staffReservation();   
        pnlhome.removeAll();
        pnlhome.add(Frame);
        Frame.setVisible(true);
    }//GEN-LAST:event_txtreserveMouseClicked

    private void txthistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthistoryMouseClicked
       
         staffHistory Frame = new staffHistory();   
        pnlhome.removeAll();
        pnlhome.add(Frame);
        Frame.setVisible(true);
        
    }//GEN-LAST:event_txthistoryMouseClicked

    private void txtoverdueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtoverdueMouseClicked
           staffOverdue Frame = new staffOverdue();   
        pnlhome.removeAll();
        pnlhome.add(Frame);
        Frame.setVisible(true);
    }//GEN-LAST:event_txtoverdueMouseClicked

    private void txthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthomeMouseClicked
        
        pnlhome.removeAll(); 
        pnlhome.revalidate();
        pnlhome.repaint();
        
         Connect();
         
        countTotalBooks();  
    countAvailableBooks();  
    getMostBorrowedBook();  
    getLowStockBooks();  
    countTotalOverdueBooks();  
    computeTotalFine();  
    countTotalUsers();  
    countTotalReservations(); 
    showPieChart();
    showLineChart();
    showBarChart();
    
    
          pnlhome.add(jPanel13); 
           pnlhome.add(jPanel14); 
           pnlhome.add(jPanel15); 
           pnlhome.add(jPanel16); 
            pnlhome.add(jPanel15); 
           pnlhome.add(jPanel27); 
            pnlhome.add(jPanel25); 
           pnlhome.add(jPanel29); 
           pnlhome.add(jPanel31); 
           
           pnlhome.add(chartAvailable); 
             pnlhome.add(jPanel12); 
           pnlhome.add(panelLineChart); 
           
           pnlhome.revalidate();
        pnlhome.repaint();
    }//GEN-LAST:event_txthomeMouseClicked

    private void txthomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthomeMouseEntered
         pnlhm.setBackground(new Color(204, 204, 204)); 
       pnlhome.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txthomeMouseEntered

    private void txthomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthomeMouseExited
        pnlhm.setBackground(new Color(50,61,46)); 
       pnlhome.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txthomeMouseExited

    private void txtbookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbookMouseEntered
         pnlbook.setBackground(new Color(204, 204, 204)); 
       txtbook.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txtbookMouseEntered

    private void txtbookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtbookMouseExited
         pnlbook.setBackground(new Color(50,61,46)); 
       txtbook.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txtbookMouseExited

    private void txtuserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtuserMouseEntered
         pnluser.setBackground(new Color(204, 204, 204)); 
       txtuser.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txtuserMouseEntered

    private void txtuserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtuserMouseExited
          pnluser.setBackground(new Color(50,61,46)); 
       txtuser.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txtuserMouseExited

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

    private void txthistoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthistoryMouseEntered
         pnlhistory.setBackground(new Color(204, 204, 204)); 
       txthistory.setForeground(new Color(50,61,46));
    }//GEN-LAST:event_txthistoryMouseEntered

    private void txthistoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txthistoryMouseExited
       pnlhistory.setBackground(new Color(50,61,46)); 
       txthistory
               .setForeground(new Color(255,255,255));
    }//GEN-LAST:event_txthistoryMouseExited

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
            new staffHome().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartAvailable;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel panelLineChart;
    private javax.swing.JPanel pnlbook;
    private javax.swing.JPanel pnlhistory;
    private javax.swing.JPanel pnlhm;
    private javax.swing.JPanel pnlhome;
    private javax.swing.JPanel pnloverdue;
    private javax.swing.JPanel pnlreserve;
    private javax.swing.JPanel pnluser;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private javax.swing.JLabel txtAvailableBooks;
    private javax.swing.JLabel txtLowStockBook;
    private javax.swing.JLabel txtMostBorrowedBook;
    private javax.swing.JLabel txtOverdueBooks;
    private javax.swing.JLabel txtTotalBooks;
    private javax.swing.JLabel txtTotalFine;
    private javax.swing.JLabel txtTotalReservations;
    private javax.swing.JLabel txtTotalUsers;
    private javax.swing.JLabel txtbook;
    private javax.swing.JLabel txthistory;
    private javax.swing.JLabel txthome;
    private javax.swing.JLabel txtoverdue;
    private javax.swing.JLabel txtreserve;
    private javax.swing.JLabel txtuser;
    // End of variables declaration//GEN-END:variables
}
