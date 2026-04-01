/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.imageviewer;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ImageViewer extends JFrame implements ActionListener {

    JLabel imageLabel;
    JButton btnOpen, btnNext, btnPrev;

    File[] imageFiles;
    int currentIndex = -1;

    // Constructor
    ImageViewer() {
        setTitle("Image Viewer");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Image Label
        imageLabel = new JLabel("No Image Loaded", JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel panel = new JPanel();

        btnOpen = new JButton("Open");
        btnPrev = new JButton("Previous");
        btnNext = new JButton("Next");

        panel.add(btnOpen);
        panel.add(btnPrev);
        panel.add(btnNext);

        add(panel, BorderLayout.SOUTH);

        // Events
        btnOpen.addActionListener(this);
        btnNext.addActionListener(this);
        btnPrev.addActionListener(this);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Load Image
    void loadImage() {
        if (imageFiles != null && currentIndex >= 0 && currentIndex < imageFiles.length) {
            ImageIcon icon = new ImageIcon(imageFiles[currentIndex].getAbsolutePath());

            // Resize image
            Image img = icon.getImage().getScaledInstance(
                    imageLabel.getWidth(),
                    imageLabel.getHeight(),
                    Image.SCALE_SMOOTH
            );

            imageLabel.setIcon(new ImageIcon(img));
            imageLabel.setText("");
        }
    }

    // Button Actions
    public void actionPerformed(ActionEvent e) {

        // Open File
        if (e.getSource() == btnOpen) {
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);

            int option = chooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                imageFiles = chooser.getSelectedFiles();
                currentIndex = 0;
                loadImage();
            }
        }

        // Next Image
        if (e.getSource() == btnNext) {
            if (imageFiles != null && currentIndex < imageFiles.length - 1) {
                currentIndex++;
                loadImage();
            }
        }

        // Previous Image
        if (e.getSource() == btnPrev) {
            if (imageFiles != null && currentIndex > 0) {
                currentIndex--;
                loadImage();
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        new ImageViewer();
    }
}