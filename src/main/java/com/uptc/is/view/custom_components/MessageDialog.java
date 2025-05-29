package com.uptc.is.view.custom_components;

import javax.swing.*;
import java.awt.*;

public class MessageDialog extends JWindow {

    public enum MessageType {
        ERROR, WARNING, SUCCESS
    }

    public MessageDialog(Frame parent, String message, MessageType type) {
        super(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE);

        Toolkit.getDefaultToolkit().beep();

        Color buttonColor = switch (type) {
            case ERROR -> new Color(200, 50, 50);
            case WARNING -> new Color(255, 165, 0);
            case SUCCESS -> new Color(50, 150, 50);
        };


        JPanel contentPanel = madeContentPanel(buttonColor);

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton closeButton = new JButton("Cerrar");
        closeButton.setBackground(buttonColor);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        closeButton.addActionListener(e -> dispose()); // Cierra la ventana

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(closeButton);

        contentPanel.add(messageLabel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private static JPanel madeContentPanel(Color buttonColor) {
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(buttonColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 15, 15);
            }
        };

        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setLayout(new BorderLayout(10, 10));
        return contentPanel;
    }

}

