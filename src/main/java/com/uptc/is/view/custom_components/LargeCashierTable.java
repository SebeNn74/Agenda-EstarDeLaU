package com.uptc.is.view.custom_components;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.Schedule;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LargeCashierTable extends AbstractTableModel {

    private final String[] colums = {"C.C.","Nombres","Apellidos", "COD", "Teléfono"};
    private List<Cashier> records;

    public LargeCashierTable(){
        this.records = new ArrayList<>();
    }

    public void setRecords(List<Cashier> records){
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

        Cashier cashier = this.records.get(rowIndex);
        return switch (columnIndex){
            case 0 -> cashier.getNuip();
            case 1 -> cashier.getNames();
            case 2 -> cashier.getSurnames();
            case 3 -> cashier.getStudentCode();
            case 4 -> cashier.getTelNumber();
            default -> null;
        };

    }

    private String getStringPartitions(List<Schedule> partitions){
        return partitions.stream()
                .map(Schedule::getID)
                .collect(Collectors.joining(", "));
    }

    public String formatNumber(long numero) {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(numero);
    }

}
