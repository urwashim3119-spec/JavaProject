/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.contactbook;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.event.*;

public class ContactBook extends JFrame implements ActionListener {

    JTextField txtName, txtPhone;
    JButton btnAdd, btnUpdate, btnDelete;
    JList<String> contactList;
    DefaultListModel<String> model;

    int selectedIndex = -1;

    // Constructor
    ContactBook() {
        setTitle("Contact Book");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Name
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(50, 20, 80, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 20, 200, 25);
        add(txtName);

        // Phone
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(50, 60, 80, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(120, 60, 200, 25);
        add(txtPhone);

        // Buttons
        btnAdd = new JButton("Add");
        btnAdd.setBounds(50, 100, 80, 30);
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(150, 100, 80, 30);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(250, 100, 80, 30);
        add(btnDelete);

        // List
        model = new DefaultListModel<>();
        contactList = new JList<>(model);

        JScrollPane scroll = new JScrollPane(contactList);
        scroll.setBounds(50, 150, 280, 120);
        add(scroll);

        // Events
        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);

        // Click on list to view
        contactList.addListSelectionListener(e -> {
            selectedIndex = contactList.getSelectedIndex();
            if (selectedIndex != -1) {
                String data = model.get(selectedIndex);
                String[] parts = data.split(" - ");
                txtName.setText(parts[0]);
                txtPhone.setText(parts[1]);
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Actions
    public void actionPerformed(ActionEvent e) {

        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();

        // Add
        if (e.getSource() == btnAdd) {
            if (!name.isEmpty() && !phone.isEmpty()) {
                model.addElement(name + " - " + phone);
                txtName.setText("");
                txtPhone.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Enter all details!");
            }
        }

        // Update
        if (e.getSource() == btnUpdate) {
            if (selectedIndex != -1) {
                model.set(selectedIndex, name + " - " + phone);
            } else {
                JOptionPane.showMessageDialog(this, "Select a contact to update!");
            }
        }

        // Delete
        if (e.getSource() == btnDelete) {
            if (selectedIndex != -1) {
                model.remove(selectedIndex);
                txtName.setText("");
                txtPhone.setText("");
                selectedIndex = -1;
            } else {
                JOptionPane.showMessageDialog(this, "Select a contact to delete!");
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        new ContactBook();
    }
}