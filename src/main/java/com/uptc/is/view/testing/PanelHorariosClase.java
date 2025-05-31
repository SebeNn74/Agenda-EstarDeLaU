package com.uptc.is.view.testing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelHorariosClase extends JPanel {
    public PanelHorariosClase() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("📚 Horarios de Clase");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.BLACK);

        JButton btnAgregar = new JButton("➕ Agregar Horario");
        btnAgregar.setBackground(Color.YELLOW);
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(titulo, BorderLayout.WEST);
        header.add(btnAgregar, BorderLayout.EAST);

        String[] columnas = {"Cajero", "Día", "Hora de Inicio", "Hora de Fin"};
        Object[][] datos = {
            {"Ana López", "Lunes", "08:00", "10:00"},
            {"Carlos Pérez", "Miércoles", "10:00", "12:00"}
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

