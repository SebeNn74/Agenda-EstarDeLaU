package com.uptc.is.view.custom_components;

import com.uptc.is.model.domain.Schedule;

import javax.swing.table.AbstractTableModel;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BasicScheduleTable extends AbstractTableModel {

    private final String[] colums = {"ID","C.C.","Fecha","Día", "Franja Horaria"};
    private List<Schedule> records;

    public BasicScheduleTable(){
        this.records = new ArrayList<>();
    }

    public void setRecords(List<Schedule> records){
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

        Schedule schedule = this.records.get(rowIndex);
        return switch (columnIndex){
            case 0 -> schedule.getID();
            case 1 -> schedule.getCashier();
            case 2 -> schedule.getDate();
            case 3 -> toSpanishDay(schedule.getTimeSlot().getDay());
            case 4 -> schedule.getTimeSlot().getStartTime().toString() + " - " +
                    schedule.getTimeSlot().getEndTime().toString();
            default -> null;
        };

    }

    private String toSpanishDay(DayOfWeek dayOfWeek){
        String spanishDay = dayOfWeek.getDisplayName(TextStyle.FULL, Locale.of("es", "ES"));
        return spanishDay.substring(0, 1).toUpperCase() + spanishDay.substring(1);
    }

}
