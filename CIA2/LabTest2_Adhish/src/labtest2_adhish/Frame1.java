/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package labtest2_adhish;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author adhis
 */

class Contact {
    String name;
    String email;
    String phoneNumber;

    Contact(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

public class Frame1 extends javax.swing.JFrame {

    /**
     * Creates new form Frame1
     */
    private List<Contact> contactlist;
    
    private static final String DB_URL = "jdbc:mysql://localhost/javalab2";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bahl";
    private Connection connection;
    
    public Frame1() {
        initComponents();
        initDatabaseConnection();
        contactlist = new ArrayList<>();
        fetchContactsFromDatabase();
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
    
    private void fetchContactsFromDatabase() {
        try {
            String query = "SELECT * FROM contacts";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String number = resultSet.getString("number");
                    
                    Contact contact = new Contact(name, email, number);
                    contactlist.add(contact);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void insertContactIntoDatabase(Contact contact) {
        try {
            String query = "INSERT INTO contacts (name, email, number) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, contact.name);
                preparedStatement.setString(2, contact.email);
                preparedStatement.setString(3, contact.phoneNumber);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private Contact createContact(String name, String email, String number) {
        return new Contact(name, email, number);
    }
    
    private void updateNumberInDatabase(Contact contact) {
        try {
            String query = "UPDATE contacts SET number = ? WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, contact.phoneNumber);
                preparedStatement.setString(2, contact.name);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void updateEmailInDatabase(Contact contact) {
        try {
            String query = "UPDATE contacts SET email = ? WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, contact.email);
                preparedStatement.setString(2, contact.name);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteContactFromDatabase(String contactName) {
        try {
            String query = "DELETE FROM contacts WHERE name = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, contactName);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void displayAllContacts() {
        if (contactlist.isEmpty()) {
            updateOutput("No contacts available.\n");
            return;
        }

        String[] columnNames = {"Name", "Email", "Phone Number"};

        Object[][] data = new Object[contactlist.size()][3];

        for (int i = 0; i < contactlist.size(); i++) {
            Contact contact = contactlist.get(i);
            data[i][0] = contact.name;
            data[i][1] = contact.email;
            data[i][2] = contact.phoneNumber;
        }

        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(this, scrollPane, "All Contacts", JOptionPane.PLAIN_MESSAGE);
    }
    
    private void updateOutput(String message) {
        jTextArea1.append(message + "\n");
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton5.setText("Exit");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jMenu1.setText("Contact");

        jMenuItem1.setText("Add");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenu2.setText("Update");

        jMenuItem4.setText("Number");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Email");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenu1.add(jMenu2);

        jMenu3.setText("Search");

        jMenuItem6.setText("By Name");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("By Email");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu4.setText("View");

        jMenuItem2.setText("All Contacts");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        System.exit(0);
        closeDatabaseConnection();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JTextField nameInput = new JTextField();
        JTextField emailInput = new JTextField();
        JTextField numberInput = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameInput);
        panel.add(new JLabel("Email:"));
        panel.add(emailInput);
        panel.add(new JLabel("Phone Number:"));
        panel.add(numberInput);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Contact",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String contactName = nameInput.getText();
                String contactEmail = emailInput.getText();
                String contactNumber = numberInput.getText();

                Contact newContact = createContact(contactName, contactEmail, contactNumber);
                updateOutput("Contact added to list: " + newContact.name + "\n==============================================================\n");
                insertContactIntoDatabase(newContact);
                contactlist.add(newContact);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid input.");
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        String name = JOptionPane.showInputDialog("Enter contact name to delete:");

        boolean removed = contactlist.removeIf(contact -> contact.name.equals(name));

        if (removed) {
            deleteContactFromDatabase(name);
            updateOutput("Contact " + name + " deleted.");
        } else {
            updateOutput("Contact " + name + " not found.");
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JTextField nameInput = new JTextField();
        JTextField numberInput = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameInput);
        panel.add(new JLabel("New Phone Number:"));
        panel.add(numberInput);

        int result = JOptionPane.showConfirmDialog(null, panel, "Update Contact",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String contactName = nameInput.getText();
                String newNumber = numberInput.getText();

                // Find the contact in the list
                Contact foundContact = contactlist.stream()
                        .filter(contact -> contact.name.equals(contactName))
                        .findFirst()
                        .orElse(null);

                if (foundContact != null) {
                    foundContact.phoneNumber = newNumber;

                    updateNumberInDatabase(foundContact);

                    updateOutput("Phone number updated for contact " + foundContact.name + "\n");
                } else {
                    updateOutput("Contact " + contactName + " not found.\n");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid input.");
            }
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JTextField nameInput = new JTextField();
        JTextField emailInput = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Name:"));
        panel.add(nameInput);
        panel.add(new JLabel("New Email:"));
        panel.add(emailInput);

        int result = JOptionPane.showConfirmDialog(null, panel, "Update Contact",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            try {
                String contactName = nameInput.getText();
                String newEmail = emailInput.getText();

                Contact foundContact = contactlist.stream()
                        .filter(contact -> contact.name.equals(contactName))
                        .findFirst()
                        .orElse(null);

                if (foundContact != null) {
                    foundContact.email = newEmail;

                    updateEmailInDatabase(foundContact);

                    updateOutput("Email updated for contact " + foundContact.name + "\n");
                } else {
                    updateOutput("Contact " + contactName + " not found.\n");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid input.");
            }
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        String name = JOptionPane.showInputDialog("Enter contact name to search:");

        Contact foundContact = contactlist.stream()
                .filter(contact -> contact.name.equals(name))
                .findFirst()
                .orElse(null);

        if (foundContact != null) {
            updateOutput("Contact found: \nName: " + foundContact.name + "\nEmail: " + foundContact.email + "\nPhone Number: " + foundContact.phoneNumber + "\n");
        } else {
            updateOutput("Contact " + name + " not found.\n");
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        String email = JOptionPane.showInputDialog("Enter contact email to search:");

        Contact foundContact = contactlist.stream()
                .filter(contact -> contact.email.equals(email))
                .findFirst()
                .orElse(null);

        if (foundContact != null) {
            updateOutput("Contact found: \nName: " + foundContact.name + "\nEmail: " + foundContact.email + "\nPhone Number: " + foundContact.phoneNumber + "\n");
        } else {
            updateOutput("Contact with email " + email + " not found.\n");
        }
        updateOutput("\n==============================================================\n");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        displayAllContacts();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
