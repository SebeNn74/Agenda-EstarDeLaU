package com.uptc.is.view.custom_components;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.Schedule;

import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasicCashierTable extends AbstractTableModel {

    private final String[] colums = {"C.C.","COD","Nombre Completo"};
    private List<Cashier> records;

    public BasicCashierTable(){
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
            case 1 -> cashier.getStudentCode();
            case 2 -> cashier.getNames() + " " + cashier.getSurnames();
            default -> null;
        };

    }

}
