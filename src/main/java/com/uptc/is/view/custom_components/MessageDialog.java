package com.uptc.is.view.custom_components;

import com.uptc.is.util.FontLoader;

import javax.swing.*;
import java.awt.*;

public class MessageDialog extends JWindow {

    public enum MessageType {
        ERROR, WARNING, SUCCESS
    }

    public MessageDialog(Frame parent, String message, MessageType type) {
        super(parent);
        setBackground(new Color(0, 0, 0, 0));
        setLayout(new BorderLayout());

        Toolkit.getDefaultToolkit().beep();

        Icon icon = switch (type) {
            case ERROR -> UIManager.getIcon("OptionPane.errorIcon");
            case WARNING -> UIManager.getIcon("OptionPane.warningIcon");
            case SUCCESS -> UIManager.getIcon("OptionPane.informationIcon");
        };

        JPanel content = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Sombra externa
                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 24, 24);

                // Margen oscura de la ventana
                g2.setColor(new Color(10, 55, 95));
                g2.fillRoundRect(0, 0, getWidth()-10, getHeight()-10, 24, 24);

                // Fondo blanco con borde redondeado
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(3, 3, getWidth() - 16, getHeight() - 16, 20, 20);
                g2.dispose();
            }
        };
        content.setLayout(new BorderLayout(15, 15));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Mensaje e icono
        JPanel messagePanel = new JPanel(new BorderLayout(10, 0));
        messagePanel.setOpaque(false);

        JLabel iconLabel = new JLabel(icon);
        messagePanel.add(iconLabel, BorderLayout.WEST);

        JLabel messageLabel = new JLabel("<html><body style='width: 250px'>" + message + "</body></html>");
        messageLabel.setFont(FontLoader.loadFont("/fonts/Montserrat-SemiBold.ttf", 15f));
        messageLabel.setForeground(Color.DARK_GRAY);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Botón cerrar
        ModernButton closeButton = switch (type) {
            case ERROR -> ModernButtonFactory.danger("CERRAR");
            case WARNING -> ModernButtonFactory.variant("CERRAR");
            case SUCCESS -> ModernButtonFactory.success("CERRAR");
        };
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        buttonPanel.add(closeButton);

        content.add(messagePanel, BorderLayout.CENTER);
        content.add(buttonPanel, BorderLayout.SOUTH);

        add(content, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

}

