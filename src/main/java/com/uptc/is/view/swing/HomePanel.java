package com.uptc.is.view.swing;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.uptc.is.util.FontLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class HomePanel extends JPanel {

    public HomePanel(){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(35, 15, 35, 15));
        this.configPanel();
    }

    private void configPanel(){
        JPanel titlePanel = new JPanel(new BorderLayout());

        JLabel welcomeLbl = new JLabel("BIENVENIDO A EL SISTEMA DE");
        welcomeLbl.setFont(FontLoader.loadFont("/fonts/Roboto-Bold.ttf", 27f));
        welcomeLbl.setForeground(new Color(40, 60, 110));
        welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(welcomeLbl, BorderLayout.NORTH);

        JLabel titleLbl = new JLabel("AGENDAMIENTO DEL ESTAR DE LA U");
        titleLbl.setFont(FontLoader.loadFont("/fonts/Roboto-Bold.ttf", 47f));
        titleLbl.setForeground(new Color(40, 60, 110));
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLbl, BorderLayout.CENTER);

        JLabel icon = getLogo();

        this.add(titlePanel, BorderLayout.NORTH);
        assert icon != null;
        this.add(icon, BorderLayout.CENTER);
    }

    private JLabel getLogo(){
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/home_icon.png")));
            Image scaledImage = icon.getImage().getScaledInstance(330, 330, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("No se pudo encontrar el ícono en: " + "/icons/logo_estardelau.png");
        }
        return null;
    }

}
