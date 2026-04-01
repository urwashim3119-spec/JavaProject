/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pizzabilling;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.event.*;

public class PizzaBilling extends JFrame implements ActionListener {

    JCheckBox pizza, burger, pasta;
    JTextField qtyPizza, qtyBurger, qtyPasta;
    JButton btnCalculate;

    // Prices
    int pricePizza = 200;
    int priceBurger = 100;
    int pricePasta = 150;

    // Constructor
    PizzaBilling() {
        setTitle("Food Billing System");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Pizza
        pizza = new JCheckBox("Pizza (₹200)");
        pizza.setBounds(50, 30, 150, 30);
        add(pizza);

        qtyPizza = new JTextField("0");
        qtyPizza.setBounds(220, 30, 50, 30);
        add(qtyPizza);

        // Burger
        burger = new JCheckBox("Burger (₹100)");
        burger.setBounds(50, 70, 150, 30);
        add(burger);

        qtyBurger = new JTextField("0");
        qtyBurger.setBounds(220, 70, 50, 30);
        add(qtyBurger);

        // Pasta
        pasta = new JCheckBox("Pasta (₹150)");
        pasta.setBounds(50, 110, 150, 30);
        add(pasta);

        qtyPasta = new JTextField("0");
        qtyPasta.setBounds(220, 110, 50, 30);
        add(qtyPasta);

        // Button
        btnCalculate = new JButton("Calculate Bill");
        btnCalculate.setBounds(120, 170, 150, 30);
        add(btnCalculate);

        btnCalculate.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Action
    public void actionPerformed(ActionEvent e) {

        int total = 0;
        StringBuilder bill = new StringBuilder("----- BILL -----\n");

        try {
            // Pizza
            if (pizza.isSelected()) {
                int qty = Integer.parseInt(qtyPizza.getText());
                int cost = qty * pricePizza;
                total += cost;
                bill.append("Pizza x ").append(qty).append(" = ₹").append(cost).append("\n");
            }

            // Burger
            if (burger.isSelected()) {
                int qty = Integer.parseInt(qtyBurger.getText());
                int cost = qty * priceBurger;
                total += cost;
                bill.append("Burger x ").append(qty).append(" = ₹").append(cost).append("\n");
            }

            // Pasta
            if (pasta.isSelected()) {
                int qty = Integer.parseInt(qtyPasta.getText());
                int cost = qty * pricePasta;
                total += cost;
                bill.append("Pasta x ").append(qty).append(" = ₹").append(cost).append("\n");
            }

            bill.append("-----------------\n");
            bill.append("Total = ₹").append(total);

            JOptionPane.showMessageDialog(this, bill.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Enter valid quantity!");
        }
    }

    // Main Method
    public static void main(String[] args) {
        new PizzaBilling();
    }
}