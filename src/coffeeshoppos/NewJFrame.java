package coffeeshoppos;

import static com.mysql.cj.telemetry.TelemetryAttribute.DB_USER;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author trish
 */
public class NewJFrame extends javax.swing.JFrame {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/coffeeshoppos";
    private static final String USERNAME = "Trisha Delantar";
    private static final String PASSWORD = "@Lalay22011139*";
    
    private void insertOrderTransaction(Date orderDate, double totalAmount) {
    // Connect to your database
    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeeshoppos", "Trisha Delantar", "@Lalay22011139*");

        // Define your SQL statement
        String sql = "INSERT INTO orders (order_date, total_amount) VALUES (?, ?)";

        // Create a PreparedStatement object
        pstmt = conn.prepareStatement(sql);

        // Set the parameters
        pstmt.setDate(1, new java.sql.Date(orderDate.getTime()));
        pstmt.setDouble(2, totalAmount);

        // Execute the insert statement
        pstmt.executeUpdate();

        // Close the PreparedStatement and Connection
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the resources in a finally block to ensure they are always closed
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
    
    
    
    public NewJFrame() {
        initComponents();
        testDatabaseConnection(); // Test the database connection upon initialization
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable3.getColumnModel().getColumn(1).setPreferredWidth(180);

    }
    
    // Method to establish a connection to the database
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
        return conn;
    }
    // Method to test database connection
    private void testDatabaseConnection() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                String query = "SELECT 1"; // Simple query to test connection
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    System.out.println("Database connection test successful: " + rs.getInt(1));
                } else {
                    System.out.println("Database connection test failed.");
                }

                // Close the connections
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public class DatabaseManager {
    private Connection connection;
    
    // Constructor
    public DatabaseManager() {
        // Initialize connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeeshoppos", "Trisha Delantar", "@Lalay22011139*");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to retrieve orders from the database
    public ResultSet getOrders() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
        return statement.executeQuery();
    }
}
    
    private int orderNumber = 1;
    
    public void addtable(int id ,String Name, int Qty ,Double Price) {
        DefaultTableModel dt = (DefaultTableModel) jTable3.getModel();
        
        DecimalFormat df = new DecimalFormat("#.00");   
        double totPrice = Price * Double.valueOf(Qty);
        String TotalPrice = df.format(totPrice);
        
        for (int row =0; row < jTable3.getRowCount(); row++) {
            
            if (Name == jTable3.getValueAt(row, 1)) {
                dt.removeRow(jTable3.convertRowIndexToModel(row));
            }
        }
        Vector v = new Vector();
        
        v.add(id);
        v.add(Name);
        v.add(Qty);
        v.add(TotalPrice);
   
        dt.addRow(v);
    }
    private void fillOrdersTable() {
        
    try {
        DatabaseManager dbManager = new DatabaseManager();
        ResultSet resultSet = dbManager.getOrders();
        DefaultTableModel model = (DefaultTableModel) transactiontable.getModel();
        
        // Clear existing rows
        model.setRowCount(0);
        
        // Iterate through the ResultSet and add rows to the table
        while (resultSet.next()) {
            int orderID = resultSet.getInt("order_id");
            String orderDate = resultSet.getString("order_date");
            double totalAmount = resultSet.getDouble("total_amount");
            model.addRow(new Object[]{orderID, orderDate, totalAmount});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error fetching orders from the database", "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

 
    
    public void cal() {
        //cal total table values
        double tot = 0.0;
        DefaultTableModel dt = (DefaultTableModel) jTable3.getModel();
        int numOfRow = jTable3.getRowCount();
        
            for (int i = 0; i < numOfRow; i++) {
            double value = Double.valueOf(jTable3.getValueAt(i, 3).toString());
            tot += value;
        }
        DecimalFormat df = new DecimalFormat("#.00") ;   
        total.setText(df.format(tot));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        q1 = new javax.swing.JLabel();
        q2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        q3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        q4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        q5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        q6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        q7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        q8 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        q9 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        deletebutton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTextArea();
        addbutton = new javax.swing.JButton();
        updatebutton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        transactiontable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        pay = new javax.swing.JTextField();
        total = new javax.swing.JLabel();
        bal = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        print = new javax.swing.JButton();

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("0");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(99, 72, 50));

        jPanel1.setBackground(new java.awt.Color(99, 72, 50));

        jPanel2.setBackground(new java.awt.Color(236, 224, 209));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        q1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q1.setText("0");

        q2.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q2.setText("0");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        q3.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q3.setText("0");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/3.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        q4.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q4.setText("0");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/4.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        q5.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q5.setText("0");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/5.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        q6.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q6.setText("0");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/6.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        q7.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q7.setText("0");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/7.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        q8.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q8.setText("0");

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/8.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        q9.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        q9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        q9.setText("0");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coffeepics/9.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(q4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(q1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(q7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jButton5)
                            .addComponent(q5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8)
                            .addComponent(q8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(q6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(q9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(q3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(q3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(q6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(q5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(q9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(q8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(q7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton8))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(236, 224, 209));

        jTable3.setBackground(new java.awt.Color(219, 193, 172));
        jTable3.setForeground(new java.awt.Color(99, 72, 50));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Items", "Qty", "Price"
            }
        ));
        jTable3.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable3AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane5.setViewportView(jTable3);

        deletebutton.setBackground(new java.awt.Color(150, 114, 89));
        deletebutton.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        deletebutton.setText("DELETE");
        deletebutton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deletebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebuttonActionPerformed(evt);
            }
        });

        bill.setColumns(20);
        bill.setRows(5);
        jScrollPane1.setViewportView(bill);

        addbutton.setBackground(new java.awt.Color(150, 114, 89));
        addbutton.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        addbutton.setText("ADD");
        addbutton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbuttonActionPerformed(evt);
            }
        });

        updatebutton.setBackground(new java.awt.Color(150, 114, 89));
        updatebutton.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        updatebutton.setText("UPDATE");
        updatebutton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        updatebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebuttonActionPerformed(evt);
            }
        });

        transactiontable.setBackground(new java.awt.Color(219, 193, 172));
        transactiontable.setForeground(new java.awt.Color(99, 72, 50));
        transactiontable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        transactiontable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                transactiontableAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane6.setViewportView(transactiontable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(updatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(deletebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(updatebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(deletebutton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(236, 224, 209));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("TOTAL :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("CASH :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setText("BALANCE :");

        pay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        bal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jButton10.setBackground(new java.awt.Color(150, 114, 89));
        jButton10.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton10.setText("PAY");
        jButton10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        print.setBackground(new java.awt.Color(150, 114, 89));
        print.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        print.setText("PRINT");
        print.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(total, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(pay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bal)
                            .addComponent(jLabel13))
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(698, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(703, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void deletebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebuttonActionPerformed
        DefaultTableModel dt = (DefaultTableModel) jTable3.getModel();
        
        String r = dt.getValueAt(jTable3.getSelectedRow(), 0).toString();
        
        //remove product
        int rw = jTable3.getSelectedRow();
        dt.removeRow(rw);
        
        // Update total
        cal();
        
        //remove Qty label
        System.out.println(r);
        switch (r) {
            case "1":
                q1.setText("0");
                break;
            case "2":
                q2.setText("0");
                break;
            case "3":
                q3.setText("0");
                break;
            case "4":
                q4.setText("0");
                break; 
            case "5":
                q5.setText("0");
                break;
            case "6":
                q6.setText("0");
                break;
            case "7":
                q7.setText("0");
                break;  
            case "8":
                q8.setText("0");
                break;
            case "9":
                q9.setText("0");
                break;
                
            default:
                System.out.println("Over");
        }
        cal();
    }//GEN-LAST:event_deletebuttonActionPerformed
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q1.getText());
        ++i;
        q1.setText(String.valueOf(i));
        addtable(1, "Espresso", i, 199.9);
        
        cal();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // Bill printing
        
        try {
            bill.setText("\n\t      Trisha's Coffee Shop \n");
            bill.setText(bill.getText()+"\t          Commonwealth\n");
            bill.setText(bill.getText()+"\t          Building 24 MRB\n");
            bill.setText(bill.getText()+"\t          Commonwealth\n\n");
            bill.setText(bill.getText()+"---------------------------------------------------------------------------------\n");
            bill.setText(bill.getText()+"Items:                                        Qty:                      Price:             \n");
            bill.setText(bill.getText()+"---------------------------------------------------------------------------------\n");
            
        DefaultTableModel df = (DefaultTableModel) jTable3.getModel();
        
        //get table product details
        for (int i = 0; i < jTable3.getRowCount(); i++) {
            String Name = df.getValueAt(i, 1).toString();
            String Qty = df.getValueAt(i, 2).toString();
            String Price = df.getValueAt(i, 3).toString();
            
            bill.setText(bill.getText() + Name+"\t\t"+ Qty+"\t"+ Price +"\n");
        }
        // Display payment details
        bill.setText(bill.getText()+"---------------------------------------------------------------------------------\n");
        bill.setText(bill.getText() + "Payment Details:\n");
        bill.setText(bill.getText() + "Cash: ₱" + pay.getText() + "\n");
        bill.setText(bill.getText() + "Total: ₱" + total.getText() + "\n");
        bill.setText(bill.getText() + "Change: ₱" + bal.getText() + "\n");
        bill.setText(bill.getText() + "Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error printing bill", "Print Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  
        }          
    }//GEN-LAST:event_printActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q2.getText());
        ++i;
        q2.setText(String.valueOf(i));
        addtable(2, "Amerikano", i, 149.50);     
        cal();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q3.getText());
        ++i;
        q3.setText(String.valueOf(i));
        addtable(3, "Cappuccino", i, 174.99);     
        cal();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q4.getText());
        ++i;
        q4.setText(String.valueOf(i));
        addtable(4, "Latte", i, 189.90);     
        cal();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q5.getText());
        ++i;
        q5.setText(String.valueOf(i));
        addtable(5, "Macchiato", i, 174.90);     
        cal();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q6.getText());
        ++i;
        q6.setText(String.valueOf(i));
        addtable(6, "Frappuccino", i, 195.90);     
        cal();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q7.getText());
        ++i;
        q7.setText(String.valueOf(i));
        addtable(7, "Flat White", i, 169.50);     
        cal();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q8.getText());
        ++i;
        q8.setText(String.valueOf(i));
        addtable(8, "Irish Coffee", i, 165.50);     
        cal();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // BTN CODE
        int i = Integer.valueOf(q9.getText());
        ++i;
        q9.setText(String.valueOf(i));
        addtable(9, "Mocha", i, 189.50);     
        cal();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            double tot = Double.valueOf(total.getText());
            double paid = Double.valueOf(pay.getText());
            double balance = paid - tot;

            if (balance < 0) {
                JOptionPane.showMessageDialog(null, "You don't have enough balance", "Insufficient Balance", JOptionPane.ERROR_MESSAGE);
            } else {
                DecimalFormat df = new DecimalFormat("#.00");
                bal.setText(String.valueOf(df.format(balance)));
                
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers for total and payment", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jTable3AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable3AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3AncestorAdded

    private void clearTable() {
    DefaultTableModel dt = (DefaultTableModel) jTable3.getModel();
    dt.setRowCount(0); // Clear the table
    pay.setText(""); // Clear payment field
    total.setText(""); // Clear total field
    bal.setText(""); // Clear balance field
    q1.setText("0"); // Reset quantity labels
    q2.setText("0");
    q3.setText("0");
    q4.setText("0");
    q5.setText("0");
    q6.setText("0");
    q7.setText("0");
    q8.setText("0");
    q9.setText("0");
    bill.setText("");
}
    private void addbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbuttonActionPerformed
    // Extract total amount and order date from GUI components
    String totalText = total.getText();
    String orderDateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    
    if (!totalText.isEmpty()) {
        try {
            double totalAmount = Double.parseDouble(totalText);
            // Proceed with further operations using totalAmount
            
            // Now you can use totalAmount for further processing, such as inserting into the database
            // For example:
            insertOrderTransaction(new Date(), totalAmount);
            
        } catch (NumberFormatException e) {
            // Handle the case where the total text is not a valid double
            JOptionPane.showMessageDialog(null, "Invalid total amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Handle the case where the total text is empty
        JOptionPane.showMessageDialog(null, "Total amount is empty", "Error", JOptionPane.ERROR_MESSAGE);
    }
    
        // Perform your current operations
        // Perform your current operations
        // Assuming you have variables `orderDate` and `totalAmount` defined elsewhere
        // You need to set these variables with the appropriate values before calling insertOrderTransaction
        Date orderDate = new Date(); // Example: current date and time
        double totalAmount = Double.parseDouble(total.getText()); // Example: total amount from your GUI
        
        //Insert the order transaction into the database
        //insertOrderTransaction(orderDate, totalAmount);
        clearTable();
    }//GEN-LAST:event_addbuttonActionPerformed

    private void updateBillPrinting() {
    try {
        // Clear existing bill printing
        bill.setText("");

        // Append shop details
        bill.append("\n\t      Trisha's Coffee Shop \n");
        bill.append("\t          Commonwealth\n");
        bill.append("\t          Building 24 MRB\n");
        bill.append("\t          Commonwealth\n\n");
        bill.append("---------------------------------------------------------------------------------\n");
        bill.append("Items:                                        Qty:                      Price:             \n");

        // Get table product details
        DefaultTableModel df = (DefaultTableModel) jTable3.getModel();
        for (int i = 0; i < jTable3.getRowCount(); i++) {
            String Name = df.getValueAt(i, 1).toString();
            String Qty = df.getValueAt(i, 2).toString();
            String Price = df.getValueAt(i, 3).toString();
            
            bill.append(Name + "\t\t" + Qty + "\t" + Price + "\n");
        }

        // Add cash, total, change, and date
        bill.append("---------------------------------------------------------------------------------\n");
        bill.append("Cash: " + pay.getText() + "\n");
        bill.append("Total: " + total.getText() + "\n");
        bill.append("Change: " + bal.getText() + "\n");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        bill.append("Date: " + dateFormat.format(currentDate) + "\n");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error updating bill printing", "Update Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();  
    }   
}
    private void updateBalance() {
        // Compute balance
        double tot = Double.parseDouble(total.getText());
        double paid = Double.parseDouble(pay.getText());
        double balance = paid - tot;

        // Update bal text field
        DecimalFormat df = new DecimalFormat("#.00");   
        bal.setText(String.valueOf(df.format(balance)));
        
        // Update Change in bill printing
        updateBillPrinting();
    
}
    
    private void updatebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebuttonActionPerformed
        // Update totals
        cal(); // Update total
        // Update bill printing
        updateBillPrinting();
        updateBalance();
    }//GEN-LAST:event_updatebuttonActionPerformed

    private void transactiontableAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_transactiontableAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_transactiontableAncestorAdded

  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbutton;
    private javax.swing.JLabel bal;
    private javax.swing.JTextArea bill;
    private javax.swing.JButton deletebutton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField pay;
    private javax.swing.JButton print;
    private javax.swing.JLabel q1;
    private javax.swing.JLabel q2;
    private javax.swing.JLabel q3;
    private javax.swing.JLabel q4;
    private javax.swing.JLabel q5;
    private javax.swing.JLabel q6;
    private javax.swing.JLabel q7;
    private javax.swing.JLabel q8;
    private javax.swing.JLabel q9;
    private javax.swing.JLabel total;
    private javax.swing.JTable transactiontable;
    private javax.swing.JButton updatebutton;
    // End of variables declaration//GEN-END:variables

    private void updateProduct(int selectedRow, int productId, String productName, int quantity, double price) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void addTransactionToDatabase(double tot) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
