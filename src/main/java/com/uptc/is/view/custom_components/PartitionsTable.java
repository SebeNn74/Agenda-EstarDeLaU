package com.uptc.is.view.custom_components;

import javax.swing.table.AbstractTableModel;

public class PartitionsTable extends AbstractTableModel {

    private final String[] colums = {"Nombre","Tamaño", "Procesos", "Tiempo"};

    public PartitionsTable(){

    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return this.colums.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.colums[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return null;
    }

}
