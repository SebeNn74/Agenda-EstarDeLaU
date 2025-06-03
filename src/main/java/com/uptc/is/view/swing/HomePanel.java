package com.uptc.is.view.swing;

import com.uptc.is.util.FontLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class HomePanel extends JPanel {

    public HomePanel(){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(100, 15, 100, 15));
        this.configPanel();
    }

    private void configPanel(){
        JPanel titlePanel = new JPanel(new BorderLayout());

        JLabel welcomeLbl = new JLabel("BIENVENIDO A");
        welcomeLbl.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 33f));
        welcomeLbl.setForeground(new Color(15, 14, 29));
        welcomeLbl.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(welcomeLbl, BorderLayout.NORTH);

        JLabel titleLbl = new JLabel("EL SISTEMA DE AGENDAMIENTO");
        titleLbl.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 47f));
        titleLbl.setForeground(new Color(15, 14, 29));
        titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLbl, BorderLayout.CENTER);

        JLabel icon = getLogo();

        this.add(titlePanel, BorderLayout.NORTH);
        assert icon != null;
        this.add(icon, BorderLayout.CENTER);
    }

    private JLabel getLogo(){
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/logo2_edlu2.png")));
            Image scaledImage = icon.getImage().getScaledInstance(850, 210, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("No se pudo encontrar el ícono en: " + "/icons/logo2_edlu2.png");
        }
        return null;
    }

}
