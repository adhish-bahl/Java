package lab10;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


class Sneaker {
    String brand;
    String name;
    int size;
    float price;
    int quantity;

    Sneaker(String name, String brand, int size, float price, int quantity) {
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
    }
}

class Customer {
    String name;
    int age;
    float totalAmount;

    Customer(String name, int age) {
        this.name = name;
        this.age = age;
        this.totalAmount = 0.0f;
    }
}

/**
 *
 * @author adhis
 */
public class Hello extends javax.swing.JFrame {

    /**
     * Creates new form Hello
     */
    
    private List<Sneaker> inventory;
    private List<Customer> customers;
    
    private static final String DB_URL = "jdbc:mysql://localhost/java10sneaker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bahl";
    
    private Connection connection;
    
    
    public Hello() {
        initComponents();
        
        initDatabaseConnection();
        inventory = new ArrayList<>();
        customers = new ArrayList<>();
        fetchDataFromDatabase();
    }
    
    private void initDatabaseConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("Connected to the database");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading JDBC driver." + e.getMessage());
        System.exit(1);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error connecting to the database: " + e.getMessage());
        System.exit(1);
    }
}
    
    private void fetchDataFromDatabase() {
        fetchSneakersFromDatabase();
        fetchCustomersFromDatabase();
    }
    
    private void fetchSneakersFromDatabase() {
        try {
            String query = "SELECT * FROM sneakers";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String brand = resultSet.getString("brand");
                    int size = resultSet.getInt("size");
                    float price = resultSet.getFloat("price");
                    int quantity = resultSet.getInt("quantity");
                    
                    Sneaker sneaker = new Sneaker(name, brand, size, price, quantity);
                    inventory.add(sneaker);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void fetchCustomersFromDatabase() {
        try {
            String query = "SELECT * FROM customers";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    float totalAmount = resultSet.getFloat("totalAmount");
                    
                    Customer customer = new Customer(name, age);
                    customer.totalAmount = totalAmount;
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private void closeDatabaseConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void insertSneakerIntoDatabase(Sneaker sneaker) {
        try {
            String query = "INSERT INTO sneakers (name, brand, size, price, quantity) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, sneaker.name);
                preparedStatement.setString(2, sneaker.brand);
                preparedStatement.setInt(3, sneaker.size);
                preparedStatement.setFloat(4, sneaker.price);
                preparedStatement.setInt(5, sneaker.quantity);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void insertCustomerIntoDatabase(Customer customer) {
        try {
            String query = "INSERT INTO customers (name, age, totalAmount) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, customer.name);
                preparedStatement.setInt(2, customer.age);
                preparedStatement.setFloat(3, customer.totalAmount);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Sneaker createSneaker(String name, String brand, int size, float price, int quantity) {
        return new Sneaker(name, brand, size, price, quantity);
    }
    
    private void updateOutput(String message) {
        jTextArea1.append(message + "\n");
    }
    
    private Customer createCustomer(String name, int age) {
        return new Customer(name, age);
    }
    
    private void updateSneakerQuantityInDatabase(Sneaker sneaker) {
    try {
        String query = "UPDATE sneakers SET quantity = ? WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sneaker.quantity);
            preparedStatement.setString(2, sneaker.name);
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void deleteSneakerFromDatabase(String sneakerName) {
    try {
        String query = "DELETE FROM sneakers WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, sneakerName);
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void deleteCustomerFromDatabase(String customerName) {
    try {
        String query = "DELETE FROM customers WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customerName);
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new java.awt.Font("Javanese Text", 1, 12)); // NOI18N
        jButton1.setText("Add Sneaker to Inventory");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Javanese Text", 1, 12)); // NOI18N
        jButton2.setText("Display Sneaker Inventory");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Javanese Text", 1, 12)); // NOI18N
        jButton3.setText("Buy Sneaker");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Javanese Text", 1, 12)); // NOI18N
        jButton4.setText("Display Customers");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Javanese Text", 1, 12)); // NOI18N
        jButton5.setText("Delete Customer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Javanese Text", 1, 12)); // NOI18N
        jButton6.setText("Delete Sneaker");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Javanese Text", 1, 12)); // NOI18N
        jButton7.setText("Exit");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("outputArea"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JTextField sneakerbrand = new JTextField();
        JTextField sneakername = new JTextField();
        JTextField sneakersize = new JTextField();
        JTextField sneakerprice = new JTextField();
        JTextField sneakerquantity = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Sneaker Brand:"));
        panel.add(sneakerbrand);
        panel.add(new JLabel("Sneaker Name:"));
        panel.add(sneakername);
        panel.add(new JLabel("Sneaker Size:"));
        panel.add(sneakersize);
        panel.add(new JLabel("Sneaker Price:"));
        panel.add(sneakerprice);
        panel.add(new JLabel("Sneaker Quantity:"));
        panel.add(sneakerquantity);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Sneaker",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String sneakerBrand = sneakerbrand.getText();
                String sneakerName = sneakername.getText();
                int sneakerSize = Integer.parseInt(sneakersize.getText());
                float sneakerPrice = Float.parseFloat(sneakerprice.getText());
                int sneakerQuantity = Integer.parseInt(sneakerquantity.getText());

                Sneaker newSneaker = createSneaker(sneakerName, sneakerBrand, sneakerSize, sneakerPrice, sneakerQuantity);
                updateOutput("Sneaker added to inventory: " + newSneaker.name + "\n==============================================================\n");
                insertSneakerIntoDatabase(newSneaker);
                inventory.add(newSneaker);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid input.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (inventory.isEmpty()) {
            updateOutput("Inventory is empty.");
        } else {
            for (Sneaker sneaker : inventory) {
                updateOutput(String.format("%s %s | %d | Rs. %.2f | %d pcs", sneaker.brand, sneaker.name, sneaker.size, sneaker.price, sneaker.quantity));
            }
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String sneakerName = JOptionPane.showInputDialog("Enter the name of the sneaker you want to buy:");
        Sneaker selectedSneaker = inventory.stream().filter(sneaker -> sneaker.name.equals(sneakerName)).findFirst().orElse(null);

        if (selectedSneaker == null) {
            updateOutput("Sneaker not found in the inventory.\n==============================================================\n");
            return;
        }

        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity you want to buy:"));

        if (quantity > selectedSneaker.quantity) {
            updateOutput("Insufficient quantity in stock.");
            return;
        }

        JTextField customername = new JTextField();
        JTextField customerage = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Customer Name:"));
        panel.add(customername);
        panel.add(new JLabel("Customer Age:"));
        panel.add(customerage);

        int result = JOptionPane.showConfirmDialog(null, panel, "Customer Details",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String customerName = customername.getText();
                int customerAge = Integer.parseInt(customerage.getText());

                insertCustomerIntoDatabase(createCustomer(customerName, customerAge));
                customers.add(createCustomer(customerName, customerAge));
                float totalAmount = quantity * selectedSneaker.price;

                selectedSneaker.quantity -= quantity;
                updateSneakerQuantityInDatabase(selectedSneaker);

                for (Customer currentCustomer : customers) {
                    if (currentCustomer.name.equals(customerName)) {
                        currentCustomer.totalAmount += totalAmount;
                        break;
                    }
                }

                updateOutput("  Bill for " + customerName + ":\n" +
                    "\t\t\tSneakShop\n\t\t\t===========\n\n " +
                    selectedSneaker.name + "\t\t " + quantity + "\t\t Rs. " + selectedSneaker.price + "\n\n" +
                    "\t\t\t\tTotal Amount: Rs. " + totalAmount + "\n\n\t\t\tThank you");
                updateOutput("\n==============================================================\n");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid input.");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (customers.isEmpty()) {
            updateOutput("Customer list is empty.");
        } else {
            for (Customer customer : customers) {
                updateOutput(String.format(" %s | %d years old | Rs. %.2f", customer.name, customer.age, customer.totalAmount));
            }
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String name = JOptionPane.showInputDialog("Enter customer name to delete:");

        boolean removed = customers.removeIf(customer -> customer.name.equals(name));

        if (removed) {
            deleteCustomerFromDatabase(name);
            updateOutput("Customer " + name + " deleted.");
        } else {
            updateOutput("Customer " + name + " not found.");
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String name = JOptionPane.showInputDialog("Enter sneaker name to delete:");

        boolean removed = inventory.removeIf(sneaker -> sneaker.name.equals(name));

        if (removed) {
            deleteSneakerFromDatabase(name);
            updateOutput("Sneaker " + name + " deleted.");
        } else {
            updateOutput("Sneaker " + name + " not found in the inventory.");
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        System.exit(0);
        closeDatabaseConnection();
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Hello.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hello.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hello.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hello.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hello().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
