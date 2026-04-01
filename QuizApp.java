/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quizapp;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {

    JLabel questionLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    JButton btnNext, btnPrev, btnSubmit;
    ButtonGroup group;

    int current = 0;
    int score = 0;

    // Questions
    String[] questions = {
        "1. What is the capital of India?",
        "2. Which language is used for Java GUI?",
        "3. Which keyword is used to inherit a class?",
        "4. What is JVM?",
        "5. Which method is entry point in Java?"
    };

    String[][] options = {
        {"Mumbai", "Delhi", "Chennai", "Kolkata"},
        {"Swing", "HTML", "Python", "CSS"},
        {"this", "super", "extends", "implements"},
        {"Java Virtual Machine", "Java Variable Method", "Just Virtual Machine", "None"},
        {"start()", "run()", "main()", "init()"}
    };

    int[] answers = {1, 0, 2, 0, 2}; // correct option indexes

    int[] userAnswers = new int[questions.length];

    // Constructor
    QuizApp() {
        setTitle("Quiz Application");
        setSize(500, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 20, 400, 30);
        add(questionLabel);

        opt1 = new JRadioButton();
        opt1.setBounds(50, 60, 200, 30);
        opt2 = new JRadioButton();
        opt2.setBounds(50, 90, 200, 30);
        opt3 = new JRadioButton();
        opt3.setBounds(50, 120, 200, 30);
        opt4 = new JRadioButton();
        opt4.setBounds(50, 150, 200, 30);

        add(opt1); add(opt2); add(opt3); add(opt4);

        group = new ButtonGroup();
        group.add(opt1); group.add(opt2);
        group.add(opt3); group.add(opt4);

        btnPrev = new JButton("Previous");
        btnPrev.setBounds(50, 200, 100, 30);
        add(btnPrev);

        btnNext = new JButton("Next");
        btnNext.setBounds(170, 200, 100, 30);
        add(btnNext);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(290, 200, 100, 30);
        add(btnSubmit);

        btnPrev.addActionListener(this);
        btnNext.addActionListener(this);
        btnSubmit.addActionListener(this);

        loadQuestion();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Load Question
    void loadQuestion() {
        questionLabel.setText(questions[current]);
        opt1.setText(options[current][0]);
        opt2.setText(options[current][1]);
        opt3.setText(options[current][2]);
        opt4.setText(options[current][3]);

        group.clearSelection();

        // Restore previous answer
        if (userAnswers[current] != -1) {
            switch (userAnswers[current]) {
                case 0: opt1.setSelected(true); break;
                case 1: opt2.setSelected(true); break;
                case 2: opt3.setSelected(true); break;
                case 3: opt4.setSelected(true); break;
            }
        }
    }

    // Save Answer
    void saveAnswer() {
        if (opt1.isSelected()) userAnswers[current] = 0;
        else if (opt2.isSelected()) userAnswers[current] = 1;
        else if (opt3.isSelected()) userAnswers[current] = 2;
        else if (opt4.isSelected()) userAnswers[current] = 3;
    }

    // Button Actions
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnNext) {
            saveAnswer();
            if (current < questions.length - 1) {
                current++;
                loadQuestion();
            }
        }

        if (e.getSource() == btnPrev) {
            saveAnswer();
            if (current > 0) {
                current--;
                loadQuestion();
            }
        }

        if (e.getSource() == btnSubmit) {
            saveAnswer();
            score = 0;

            for (int i = 0; i < questions.length; i++) {
                if (userAnswers[i] == answers[i]) {
                    score++;
                }
            }

            JOptionPane.showMessageDialog(this,
                    "Your Score: " + score + "/" + questions.length);
        }
    }

    // Main Method
    public static void main(String[] args) {

        // Initialize answers with -1
        QuizApp app = new QuizApp();
        for (int i = 0; i < app.userAnswers.length; i++) {
            app.userAnswers[i] = -1;
        }
    }
}