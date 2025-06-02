package com.uptc.is.view.custom_components;

import com.uptc.is.util.FontLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;

public class CustomTable extends JTable {

    public CustomTable(TableModel model) {
        super(model);
        config();
    }

    private void config() {
        // Fuente general
        this.setFont(FontLoader.loadFont("/fonts/Montserrat-SemiBold.ttf", 15f));
        this.setRowHeight(30);

        // Colores zebra (alternancia de colores por fila)
        this.setDefaultRenderer(Object.class, new ZebraTableCellRenderer());

        // Encabezado personalizado
        JTableHeader header = this.getTableHeader();
        header.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 14f));
        header.setBackground(new Color(14, 121, 182));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        header.setReorderingAllowed(false);
        this.setTableHeader(header);

        // Bordes y líneas
        this.setShowHorizontalLines(false);
        this.setShowVerticalLines(true);
        this.setGridColor(new Color(220, 220, 220));
        this.setIntercellSpacing(new Dimension(2, 0));
    }

    // Clase interna para celdas tipo "zebra"
    static class ZebraTableCellRenderer extends DefaultTableCellRenderer {
        private final Color evenColor = new Color(230, 230, 230);
        private final Color oddColor = Color.WHITE;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Colores alternos
            if (!isSelected) {
                setBackground(row % 2 == 0 ? evenColor : oddColor);
            } else {
                setBackground(new Color(200, 220, 255)); // Color al seleccionar
            }

            // Centrado horizontal
            setHorizontalAlignment(SwingConstants.CENTER);
            return this;
        }
    }
}

