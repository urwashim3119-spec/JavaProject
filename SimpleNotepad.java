/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.simplenotepad;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class SimpleNotepad extends JFrame implements ActionListener {

    JTextArea textArea;
    JMenuItem newFile, openFile, saveFile, exit;

    // Constructor
    SimpleNotepad() {
        setTitle("Simple Notepad");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Text Area
        textArea = new JTextArea();
        add(new JScrollPane(textArea));

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        exit = new JMenuItem("Exit");

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(exit);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Add Action Listeners
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        exit.addActionListener(this);

        setVisible(true);
    }

    // Action Handling
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == newFile) {
            textArea.setText("");
        }

        if (e.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    textArea.read(br, null);
                    br.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file!");
                }
            }
        }

        if (e.getSource() == saveFile) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    textArea.write(bw);
                    bw.close();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file!");
                }
            }
        }

        if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    // Main Method
    public static void main(String[] args) {
        new SimpleNotepad();
    }
}