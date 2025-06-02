package com.uptc.is.view.custom_components;

import com.uptc.is.util.FontLoader;

import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {

    private int arc = 15;

    public CustomTextField() {
        super(100);
        setFont(FontLoader.loadFont("/fonts/Montserrat-Regular.ttf", 14f));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Espaciado interno
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(1));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);
        g2.dispose();
    }

}
