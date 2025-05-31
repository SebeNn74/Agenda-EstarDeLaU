package com.uptc.is.view.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class PanelLateral extends JPanel {
    public PanelLateral() {
        setLayout(new GridLayout(6, 1, 10, 10));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(200, 0));
    }

    private JButton buttonSelected = null;

    public void setAction(Consumer<String> actionCallback) {
        String[][] opciones = {
            {"🏠 Inicio", "Inicio"},
            {"👤 Cajeros", "Cajeros"},
            {"📚 Clases", "Clases"},
            {"🕒 Turnos", "Turnos"},
            {"📅 Calendario", "Calendario"}
        };

        for (String[] opcion : opciones) {
            JButton btn = new JButton(opcion[0]);
            btn.setFocusPainted(false);
            btn.setBackground(Color.BLACK);
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setBorderPainted(false);
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Efecto hover
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btn.setBackground(new Color(40, 40, 40));
                }
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(Color.BLACK);
                }
            });

            btn.addActionListener(e -> {
                if (buttonSelected != null) {
                    // Restaurar estilo del botón anterior
                    buttonSelected.setBackground(Color.BLACK);
                    buttonSelected.setForeground(Color.WHITE);
                }
                // Aplicar estilo al nuevo botón seleccionado
                buttonSelected = btn;
                buttonSelected.setBackground(new Color(40, 40, 40)); // Fondo gris
                buttonSelected.setForeground(Color.YELLOW); // Texto amarillo

                // Ejecutar el callback con el valor de la opción (ej: "Inicio")
                actionCallback.accept(opcion[1]);
            });            
            
            add(btn);
        }
    }
}

