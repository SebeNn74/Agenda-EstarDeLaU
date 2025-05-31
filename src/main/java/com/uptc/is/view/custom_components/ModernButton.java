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
    private float fontSize = 14f;

    // Propiedades de Apariencia
    private int cornerRadius = 20;
    private boolean isHovering = false;
    private boolean wasPressed = false;

    public ModernButton(String text) {
        super(text);
        init();
    }

    private void init() {
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

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
        Font robotoFont = FontLoader.loadFont("/fonts/Roboto-Bold.ttf", fontSize);
        this.setFont(robotoFont);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo
        if (getModel().isPressed() || wasPressed) {
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

    public void addClickAction(ActionListener action) {
        this.addActionListener(action);
    }

    // SETTERS de Personalización

    public void setBackgroundColors(Color normalColor, Color hoverColor, Color pressedColor) {
        this.normalBackgroundColor = normalColor;
        this.hoverBackgroundColor = hoverColor;
        this.pressedBackgroundColor = pressedColor;
        repaint();
    }

    public void setForegroundColors(Color normalColor, Color hoverColor){
        this.normalForegroundColor = normalColor;
        this.hoverForegroundColor = hoverColor;
        if (isHovering) {
            setForeground(hoverColor);
        }else{
            setForeground(normalColor);
        }
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setFontSize(float size){
        this.fontSize = size;
        repaint();
    }

    public void setPressed(boolean isPressed){
        this.wasPressed = isPressed;
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
