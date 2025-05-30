package com.uptc.is.view.custom_components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomButton extends JButton {
    private Color defaultColor;
    private Color hoverColor;

    public CustomButton(String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 14));
        setFocusPainted(false);
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor != null ? hoverColor : defaultColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultColor);
            }
        });
    }

    public void setColors(Color defaultColor, Color hoverColor) {
        this.defaultColor = defaultColor;
        this.hoverColor = hoverColor;
        setBackground(defaultColor);
    }

    public void setTextColor(Color textColor) {
        setForeground(textColor);
    }

    public void setClickAction(ActionListener action) {
        addActionListener(action);
    }

    public void setButtonSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

}


