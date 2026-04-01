/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.numberguessing;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.event.*;

public class NumberGuessing extends JFrame implements ActionListener {

    JLabel lblTitle, lblHint, lblAttempts;
    JTextField txtGuess;
    JButton btnCheck, btnReset;

    int number;
    int attempts = 0;

    // Constructor
    NumberGuessing() {
        setTitle("Number Guessing Game");
        setSize(350, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Title
        lblTitle = new JLabel("Guess a number (1-100)");
        lblTitle.setBounds(80, 20, 200, 30);
        add(lblTitle);

        // Input
        txtGuess = new JTextField();
        txtGuess.setBounds(100, 60, 120, 30);
        add(txtGuess);

        // Buttons
        btnCheck = new JButton("Check");
        btnCheck.setBounds(60, 110, 100, 30);
        add(btnCheck);

        btnReset = new JButton("Reset");
        btnReset.setBounds(170, 110, 100, 30);
        add(btnReset);

        // Hint Label
        lblHint = new JLabel(" ");
        lblHint.setBounds(100, 150, 200, 30);
        add(lblHint);

        // Attempts Label
        lblAttempts = new JLabel("Attempts: 0");
        lblAttempts.setBounds(120, 180, 120, 30);
        add(lblAttempts);

        // Events
        btnCheck.addActionListener(this);
        btnReset.addActionListener(this);

        // Generate number
        number = (int)(Math.random() * 100) + 1;

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Button Actions
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCheck) {
            try {
                int guess = Integer.parseInt(txtGuess.getText());
                attempts++;

                if (guess < number) {
                    lblHint.setText("Too Low!");
                } else if (guess > number) {
                    lblHint.setText("Too High!");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "🎉 Correct! You guessed in " + attempts + " attempts.");
                }

                lblAttempts.setText("Attempts: " + attempts);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number!");
            }
        }

        if (e.getSource() == btnReset) {
            number = (int)(Math.random() * 100) + 1;
            attempts = 0;
            txtGuess.setText("");
            lblHint.setText(" ");
            lblAttempts.setText("Attempts: 0");
        }
    }

    // Main Method
    public static void main(String[] args) {
        new NumberGuessing();
    }
}