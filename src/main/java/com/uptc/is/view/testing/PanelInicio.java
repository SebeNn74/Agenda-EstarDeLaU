package com.uptc.is.view.testing;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {
    public PanelInicio() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel titulo = new JLabel("🏠 Bienvenido al Sistema de Turnos de Cafetería");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(Color.BLACK);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JTextArea mensaje = new JTextArea("Desde aquí puedes gestionar:") {
            {
                setOpaque(false);
                setEditable(false);
                setFocusable(false);
                setFont(new Font("Arial", Font.PLAIN, 16));
                setForeground(Color.DARK_GRAY);
                setMargin(new Insets(10, 20, 10, 20));
            }
        };

        DefaultListModel<String> opciones = new DefaultListModel<>();
        opciones.addElement("👤 Gestión de Cajeros");
        opciones.addElement("📚 Horarios de Clase");
        opciones.addElement("🕒 Turnos de Cafetería");
        opciones.addElement("📅 Calendario General");

        JList<String> lista = new JList<>(opciones);
        lista.setFont(new Font("Arial", Font.PLAIN, 14));
        lista.setBackground(Color.WHITE);
        lista.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel centro = new JPanel(new BorderLayout());
        centro.setOpaque(false);
        centro.add(mensaje, BorderLayout.NORTH);
        centro.add(lista, BorderLayout.CENTER);

        add(titulo, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
    }
}
