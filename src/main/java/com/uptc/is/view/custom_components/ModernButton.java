package com.uptc.is.view.custom_components;

import com.uptc.is.util.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernButton extends JButton {

    // Colores
    private Color normalBackgroundColor = new Color(59, 89, 152);
    private Color hoverBackgroundColor = new Color(88, 115, 175);
    private Color pressedBackgroundColor = new Color(40, 60, 110);
    private Color normalForegroundColor = Color.WHITE;
    private Color hoverForegroundColor = Color.WHITE;
    private Color borderColor = new Color(59, 89, 152);

    // Propiedades de Apariencia
    private int cornerRadius = 20;
    private boolean isHovering = false;

    public ModernButton(String text) {
        super(text);
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        Font robotoFont = FontLoader.loadFont("/fonts/Roboto-Bold.ttf", 15f);
        this.setFont(robotoFont);

        setForeground(normalForegroundColor);

        // Listeners para de hover y click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovering = true;
                setForeground(hoverForegroundColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isHovering = false;
                setForeground(normalForegroundColor);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo
        if (getModel().isPressed()) {
            g2.setColor(pressedBackgroundColor);
        } else if (isHovering) {
            g2.setColor(hoverBackgroundColor);
        } else {
            g2.setColor(normalBackgroundColor);
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Borde
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    public void setClickAction(ActionListener action) {
        addActionListener(action);
    }

    // SETTERS de Personalización

    public void setNormalBackgroundColor(Color color) {
        this.normalBackgroundColor = color;
        repaint();
    }

    public void setHoverBackgroundColor(Color color) {
        this.hoverBackgroundColor = color;
        repaint();
    }

    public void setPressedBackgroundColor(Color color) {
        this.pressedBackgroundColor = color;
        repaint();
    }

    public void setNormalForegroundColor(Color color) {
        this.normalForegroundColor = color;
        if (!isHovering) {
            setForeground(color);
        }
        repaint();
    }

    public void setHoverForegroundColor(Color color) {
        this.hoverForegroundColor = color;
        if (isHovering) {
            setForeground(color);
        }
        repaint();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        repaint();
    }

    /**
     * Establece un ícono al botón, escalándolo a un tamaño específico.
     * @param imagePath Ruta al archivo .png dentro de los recursos (ej. "/icons/user.png").
     * @param width Ancho deseado para el ícono.
     * @param height Alto deseado para el ícono.
     */
    public void setIcon(String imagePath, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("No se pudo encontrar el ícono en: " + imagePath);
        }
    }

    /**
     * Establece el tamaño preferido del botón.
     * @param width Ancho del botón.
     * @param height Alto del botón.
     */
    public void setButtonSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

}
