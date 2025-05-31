package com.uptc.is.view.testing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelCajeros extends JPanel {
    public PanelCajeros() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("👤 Gestión de Cajeros");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.BLACK);

        JButton btnAgregar = new JButton("➕ Agregar Cajero");
        btnAgregar.setBackground(Color.YELLOW);
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(titulo, BorderLayout.WEST);
        header.add(btnAgregar, BorderLayout.EAST);

        String[] columnas = {"Nombre", "Matrícula", "Carrera", "Semestre", "Editar", "Eliminar"};
        Object[][] datos = {
            {"Ana López", "2101234", "Ing. Civil", "6", "✏️", "🗑️"},
            {"Carlos Pérez", "2105678", "Administración", "4", "✏️", "🗑️"}
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

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        add(header, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}