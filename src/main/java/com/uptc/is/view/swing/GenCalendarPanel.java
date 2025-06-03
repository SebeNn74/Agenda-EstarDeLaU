package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Schedule;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.custom_components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenCalendarPanel extends JPanel {

    private MyCalendarPanel calendarPanel;
    private BasicScheduleTable scheduleTbModel;
    private CustomTable scheduleTable;
    private JScrollPane spScheduleTb;

    public GenCalendarPanel(IScheduleView scheduleView){
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 30, 30, 20));
        configCalendar(scheduleView);
        configListPanel(scheduleView);
        configSplitPane();
    }

    public void configCalendar(IScheduleView scheduleView){
        calendarPanel = new MyCalendarPanel(scheduleView);
        calendarPanel.setBorder(new EmptyBorder(10, 0, 20, 0));
        calendarPanel.setPreferredSize(new Dimension(250, 250));

        this.add(calendarPanel, BorderLayout.WEST);
    }

    private void configListPanel(IScheduleView scheduleView){
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(new EmptyBorder(10, 0, 20, 0));

        scheduleTbModel = new BasicScheduleTable();
        scheduleTable = new CustomTable(scheduleTbModel);
        scheduleTable.setFillsViewportHeight(true);
        scheduleTable.setRowHeight(25);
        configTableListeners(scheduleView);

        spScheduleTb = new JScrollPane(scheduleTable);
        spScheduleTb.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        spScheduleTb.setBorder(new EmptyBorder(10, 20, 10, 0));
        spScheduleTb.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        listPanel.add(spScheduleTb, BorderLayout.CENTER);
    }

    private void configSplitPane(){
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, calendarPanel, spScheduleTb);
        splitPane.setBorder(null);
        splitPane.setResizeWeight(0.25);
        splitPane.setDividerSize(0);
        splitPane.setContinuousLayout(true);

        this.add(splitPane, BorderLayout.CENTER);
    }

    private void configTableListeners(IScheduleView scheduleView) {
        TableColumnModel columnModel = scheduleTable.getColumnModel();
        columnModel.getColumn(0).setMinWidth(0);
        columnModel.getColumn(0).setMaxWidth(0);
        columnModel.getColumn(0).setPreferredWidth(0);

        scheduleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
                    scheduleView.searchSchedule(getSelectedScheduleId());
                }
            }
        });
    }

    public String getSelectedScheduleId(){
        int modelRow = scheduleTable.convertRowIndexToModel(scheduleTable.getSelectedRow());
        return scheduleTable.getModel().getValueAt(modelRow, 0).toString();
    }

    public void updateSchedules(List<Schedule> schedules){
        scheduleTbModel.setRecords(schedules);
        scheduleTbModel.fireTableDataChanged();
        scheduleTable.scrollRectToVisible(scheduleTable.getCellRect(scheduleTable.getRowCount() - 1, 0, true));
    }

    public void updateCalendar(List<Schedule> schedules){
        List<LocalDate> dates = new ArrayList<>();
        for (Schedule s : schedules){
            dates.add(s.getDate());
        }
        calendarPanel.setPressedDates(dates);
    }

}
