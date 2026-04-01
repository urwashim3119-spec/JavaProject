/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.analogclock;

/**
 *
 * @author RAHUL KUSHWAHA
 */
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class AnalogClock extends JPanel {

    public AnalogClock() {
        Timer timer = new Timer(1000, e -> repaint());
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 2 - 20;

        // Draw circle
        g.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        // Get time
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);
        int seconds = cal.get(Calendar.SECOND);

        // Angles
        double secondAngle = Math.toRadians(seconds * 6);
        double minuteAngle = Math.toRadians(minutes * 6);
        double hourAngle = Math.toRadians((hours + minutes / 60.0) * 30);

        // Second hand
        int secX = (int) (centerX + radius * 0.9 * Math.sin(secondAngle));
        int secY = (int) (centerY - radius * 0.9 * Math.cos(secondAngle));
        g.setColor(Color.RED);
        g.drawLine(centerX, centerY, secX, secY);

        // Minute hand
        int minX = (int) (centerX + radius * 0.7 * Math.sin(minuteAngle));
        int minY = (int) (centerY - radius * 0.7 * Math.cos(minuteAngle));
        g.setColor(Color.BLUE);
        g.drawLine(centerX, centerY, minX, minY);

        // Hour hand
        int hourX = (int) (centerX + radius * 0.5 * Math.sin(hourAngle));
        int hourY = (int) (centerY - radius * 0.5 * Math.cos(hourAngle));
        g.setColor(Color.BLACK);
        g.drawLine(centerX, centerY, hourX, hourY);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Analog Clock");
        frame.add(new AnalogClock());
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

