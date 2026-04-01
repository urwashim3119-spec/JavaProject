/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.stopwatchapp;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class StopwatchApp extends JFrame implements ActionListener {

    JLabel lblTime;
    JButton btnStart, btnStop, btnReset, btnLap;
    DefaultListModel<String> lapModel;
    JList<String> lapList;

    Timer timer;
    int milliseconds = 0;
    boolean running = false;

    ArrayList<String> laps = new ArrayList<>();

    // Constructor
    StopwatchApp() {
        setTitle("Stopwatch");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel (Time Display)
        JPanel topPanel = new JPanel();
        lblTime = new JLabel("00:00:00");
        lblTime.setFont(new Font("Arial", Font.BOLD, 30));
        topPanel.add(lblTime);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel (Lap List)
        lapModel = new DefaultListModel<>();
        lapList = new JList<>(lapModel);
        add(new JScrollPane(lapList), BorderLayout.CENTER);

        // Bottom Panel (Buttons)
        JPanel bottomPanel = new JPanel();

        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnReset = new JButton("Reset");
        btnLap = new JButton("Lap");

        bottomPanel.add(btnStart);
        bottomPanel.add(btnStop);
        bottomPanel.add(btnReset);
        bottomPanel.add(btnLap);

        add(bottomPanel, BorderLayout.SOUTH);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveLaps = new JMenuItem("Save Laps");

        fileMenu.add(saveLaps);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Timer (updates every 10ms)
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                milliseconds += 10;
                updateTime();
            }
        });

        // Events
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);
        btnReset.addActionListener(this);
        btnLap.addActionListener(this);
        saveLaps.addActionListener(e -> saveToFile());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Update time display
    void updateTime() {
        int seconds = milliseconds / 1000;
        int mins = seconds / 60;
        int hours = mins / 60;

        seconds %= 60;
        mins %= 60;

        String time = String.format("%02d:%02d:%02d", hours, mins, seconds);
        lblTime.setText(time);
    }

    // Button Actions
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnStart && !running) {
            timer.start();
            running = true;
        }

        if (e.getSource() == btnStop && running) {
            timer.stop();
            running = false;
        }

        if (e.getSource() == btnReset) {
            timer.stop();
            running = false;
            milliseconds = 0;
            lblTime.setText("00:00:00");
            lapModel.clear();
            laps.clear();
        }

        if (e.getSource() == btnLap && running) {
            String lapTime = lblTime.getText();
            lapModel.addElement("Lap " + (lapModel.size() + 1) + ": " + lapTime);
            laps.add(lapTime);
        }
    }

    // Save laps to file
    void saveToFile() {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (String lap : laps) {
                    bw.write(lap);
                    bw.newLine();
                }
                JOptionPane.showMessageDialog(this, "Laps saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file!");
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        new StopwatchApp();
    }
}