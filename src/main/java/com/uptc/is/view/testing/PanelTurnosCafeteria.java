package com.uptc.is.view.testing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelTurnosCafeteria extends JPanel {
    public PanelTurnosCafeteria() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("🕒 Turnos de Cafetería");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.BLACK);

        JButton btnAsignar = new JButton("➕ Asignar Turno");
        btnAsignar.setBackground(Color.YELLOW);
        btnAsignar.setForeground(Color.BLACK);
        btnAsignar.setFocusPainted(false);
        btnAsignar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAsignar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(titulo, BorderLayout.WEST);
        header.add(btnAsignar, BorderLayout.EAST);

        String[] columnas = {"Cajero", "Día", "Hora de Entrada", "Hora de Salida"};
        Object[][] datos = {
            {"Ana López", "Lunes", "12:00", "14:00"},
            {"Carlos Pérez", "Viernes", "15:00", "17:00"}
        };

        DefaultTableModel modelo = new DefaultTableModel(datos, columnas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(Color.BLACK);
        tabla.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}

