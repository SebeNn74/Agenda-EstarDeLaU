package com.uptc.is.view.custom_components;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProcessTable extends AbstractTableModel {

    private final String[] colums = {"Nombre","Tiempo","Tamaño", "Particiones"};
    private List<Process> records;

    public ProcessTable(){
        this.records = new ArrayList<>();
    }

    public void setRecords(List<Process> records){
        this.records = records;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return this.records.size();
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
        Process process = this.records.get(rowIndex);
        return switch (columnIndex){
            case 0, 1 -> "";
            default -> null;
        };

    }

    public String formatNumber(long numero) {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(numero);
    }

}
