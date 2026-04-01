/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.todolistapp;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.event.*;

public class TodoListApp extends JFrame implements ActionListener {

    JTextField txtTask;
    JButton btnAdd, btnDelete;
    JList<String> taskList;
    DefaultListModel<String> model;

    // Constructor
    TodoListApp() {
        setTitle("To-Do List");
        setSize(400, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input field
        txtTask = new JTextField();
        txtTask.setBounds(50, 30, 200, 30);
        add(txtTask);

        // Add Button
        btnAdd = new JButton("Add Task");
        btnAdd.setBounds(260, 30, 100, 30);
        add(btnAdd);

        // Delete Button
        btnDelete = new JButton("Delete Task");
        btnDelete.setBounds(130, 250, 130, 30);
        add(btnDelete);

        // List Model
        model = new DefaultListModel<>();
        taskList = new JList<>(model);

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(50, 80, 300, 150);
        add(scrollPane);

        // Action Listeners
        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Button Actions
    public void actionPerformed(ActionEvent e) {

        // Add Task
        if (e.getSource() == btnAdd) {
            String task = txtTask.getText().trim();

            if (!task.isEmpty()) {
                model.addElement(task);
                txtTask.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Enter a task!");
            }
        }

        // Delete Task
        if (e.getSource() == btnDelete) {
            int selectedIndex = taskList.getSelectedIndex();

            if (selectedIndex != -1) {
                model.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to delete!");
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        new TodoListApp();
    }
}