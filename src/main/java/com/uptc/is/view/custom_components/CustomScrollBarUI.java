package com.uptc.is.view.custom_components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(148, 172, 225 );
        this.thumbDarkShadowColor = new Color(80, 80, 80);
        this.thumbHighlightColor = new Color(180, 180, 180);
        this.trackColor = new Color(220, 220, 220);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createInvisibleButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createInvisibleButton();
    }

    private JButton createInvisibleButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        return button;
    }
}

