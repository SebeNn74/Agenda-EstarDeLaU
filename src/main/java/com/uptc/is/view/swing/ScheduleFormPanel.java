package com.uptc.is.view.swing;

import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.TimeMenuPanel;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import com.uptc.is.util.FontLoader;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.custom_components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScheduleFormPanel extends JPanel {

    private JPanel inputPanel;
    private JPanel listPanel;
    private FormField nuip;
    private FormField cashierSearched;
    private JLabel dateLb;
    private CalendarPanel calendarPanel;
    private TimePicker startTimePicker;
    private TimePicker endTimePicker;
    private BasicCashierTable tableModel;
    private CustomTable cashierTable;
    private JScrollPane spRecords;

    public ScheduleFormPanel(IScheduleView scheduleView){
        this.setLayout(new BorderLayout());
        this.configInputPanel(scheduleView);
        this.configListPanel(scheduleView);
        this.configSplitPane();
        this.configResponsiveColumns();
    }

    private void configInputPanel(IScheduleView scheduleView) {
        inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 25, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx = 1;
        gbc.gridwidth = 2;
        gbc.gridx = 0;

        nuip = new FormField("C.C :", 60);
        nuip.setPreferredSize(new Dimension(0, 35));

        dateLb = new JLabel("Fecha: Ninguna seleccionada", SwingConstants.CENTER);
        dateLb.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 14.5f));
        dateLb.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateLb.setPreferredSize(new Dimension(200, 30));

        configCalendar();

        ModernButton createSchedule = new ModernButton("CREAR NUEVO HORARIO");
        createSchedule.addClickAction(e -> scheduleView.createSchedule());
        createSchedule.setButtonSize( 220, 35);

        JLabel startTimeLb = new JLabel("Hora de Inicio:");
        startTimeLb.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 14.5f));
        JLabel endTimeLb = new JLabel("Hora de Fin:");
        endTimeLb.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 14.5f));

        startTimePicker = new TimePicker();
        endTimePicker = new TimePicker();

        ModernButton createTimeSlot = new ModernButton("CREAR FRANJA HORARIA");
        createTimeSlot.addClickAction(e -> scheduleView.createTimeSlot());
        createTimeSlot.setButtonSize( 240, 35);

        ModernButton deleteTimeSlot = ModernButtonFactory.danger("ELIMINAR FRANJA HORARIA");
        deleteTimeSlot.addClickAction(e -> scheduleView.createTimeSlot());
        deleteTimeSlot.setButtonSize( 240, 35);

        gbc.gridy = 0;
        inputPanel.add(nuip, gbc);

        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridy = 1;
        inputPanel.add(dateLb, gbc);

        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 2;
        inputPanel.add(calendarPanel, gbc);

        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.gridy = 3;
        inputPanel.add(createSchedule, gbc);

        gbc.insets = new Insets(0, 0, 5, 0);
        gbc.gridwidth = 1;
        gbc.gridy = 4; gbc.gridx = 0;
        inputPanel.add(startTimeLb, gbc);

        gbc.gridy = 4; gbc.gridx = 1;
        inputPanel.add(endTimeLb, gbc);

        gbc.gridy = 5; gbc.gridx = 0;
        inputPanel.add(startTimePicker, gbc);

        gbc.insets = new Insets(0, 0, 20, 0);
        gbc.gridy = 5; gbc.gridx = 1;
        inputPanel.add(endTimePicker, gbc);

        gbc.gridwidth = 2;
        gbc.gridy = 6; gbc.gridx = 0;
        inputPanel.add(createTimeSlot, gbc);

        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.gridy = 7; gbc.gridx = 0;
        inputPanel.add(deleteTimeSlot, gbc);

        gbc.weighty = 1;
        inputPanel.add(Box.createVerticalGlue(), gbc);
    }

    private void configListPanel(IScheduleView scheduleView){
        listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(new EmptyBorder(20, 20, 30, 20));

        JPanel topPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 20, 0, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 0;

        ModernButton searchCashier = new ModernButton("BUSCAR");
        searchCashier.addClickAction(e -> scheduleView.searchCashier(cashierSearched.getText()));
        searchCashier.setButtonSize( 100, 35);

        cashierSearched = new FormField("Cajero (C.C) :", 100);
        cashierSearched.setPreferredSize(new Dimension(0, 35));
        cashierSearched.addActionListener(e -> searchCashier.doClick());

        gbc.weightx = 2;
        gbc.gridx = 0;
        topPanel.add(cashierSearched, gbc);
        gbc.weightx = 0;
        gbc.gridx = 1;
        topPanel.add(searchCashier, gbc);

        tableModel = new BasicCashierTable();
        cashierTable = new CustomTable(tableModel);
        cashierTable.setFillsViewportHeight(true);
        cashierTable.setRowHeight(25);

        spRecords = new JScrollPane(cashierTable);
        spRecords.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        spRecords.setBorder(new EmptyBorder(10, 0, 10, 0));
        spRecords.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        listPanel.add(topPanel, BorderLayout.NORTH);
        listPanel.add(spRecords, BorderLayout.CENTER);
    }

    private void configSplitPane(){
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inputPanel, listPanel);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerSize(2);
        splitPane.setContinuousLayout(true);

        this.add(splitPane, BorderLayout.CENTER);
    }

    private void configCalendar(){
        calendarPanel = new CalendarPanel();
        calendarPanel.addCalendarListener(new CalendarListener() {
            @Override
            public void selectedDateChanged(CalendarSelectionEvent calendarSelectionEvent) {
                LocalDate newDate = calendarSelectionEvent.getNewDate();

                if (newDate != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
                    dateLb.setText("Fecha : " + newDate.format(formatter));
                } else {
                    dateLb.setText("Fecha: Ninguna seleccionada");
                }
            }

            @Override
            public void yearMonthChanged(YearMonthChangeEvent yearMonthChangeEvent) {

            }
        });
    }

    private void configResponsiveColumns() {
        double[] widths = {0.25, 0.25, 0.5};

        spRecords.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int totalWidth = spRecords.getViewport().getWidth();
                TableColumnModel columnModel = cashierTable.getColumnModel();
                for (int i = 0; i < widths.length; i++) {
                    columnModel.getColumn(i).setPreferredWidth((int) (totalWidth * widths[i]));
                }
            }
        });
    }

    public String getNuip(){
        return nuip.getText();
    }

    public LocalDate getDate(){
        return calendarPanel.getSelectedDate();
    }

    public DayOfWeek getDay(){
        return calendarPanel.getSelectedDate().getDayOfWeek();
    }

    public LocalTime getStarTime(){
        return startTimePicker.getTime();
    }

    public LocalTime getEndTime(){
        return endTimePicker.getTime();
    }

    public void setNuip(String nuip){
        this.nuip.setText(nuip);
    }

}
