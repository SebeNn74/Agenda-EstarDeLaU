package com.uptc.is.view.swing;

import com.uptc.is.util.FontLoader;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.custom_components.ModernButton;
import com.uptc.is.view.custom_components.ModernButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyCalendarPanel extends JPanel {

    private final IScheduleView scheduleView;
    private JPanel headerPanel;
    private JPanel daysGridPanel;
    private JLabel monthYearLabel;
    private LocalDate displayedDate;
    private ModernButton lastPressedBtn;
    private List<LocalDate> datesPressed;

    public MyCalendarPanel(IScheduleView scheduleView) {
        this.scheduleView = scheduleView;
        this.displayedDate = LocalDate.now();
        this.datesPressed = new ArrayList<>();

        setLayout(new BorderLayout());

        createHeader();
        createDaysGrid();

        add(headerPanel, BorderLayout.NORTH);
        add(daysGridPanel, BorderLayout.CENTER);

        updateCalendar();
    }

    private void createHeader() {
        headerPanel = new JPanel(new BorderLayout());

        JButton prevMonthBtn = new ModernButton("<");
        prevMonthBtn.addActionListener(e -> changeMonth(-1));

        JButton nextMonthBtn = new ModernButton(">");
        nextMonthBtn.addActionListener(e -> changeMonth(1));

        monthYearLabel = new JLabel("", SwingConstants.CENTER);
        monthYearLabel.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 18f));

        headerPanel.add(prevMonthBtn, BorderLayout.WEST);
        headerPanel.add(monthYearLabel, BorderLayout.CENTER);
        headerPanel.add(nextMonthBtn, BorderLayout.EAST);
    }

    private void changeMonth(int amount) {
        displayedDate = displayedDate.plusMonths(amount);
        updateCalendar();
    }

    private void createDaysGrid() {
        daysGridPanel = new JPanel(new GridLayout(0, 7, 2, 2)); // 2px de espacio
    }

    private void updateCalendar() {
        // 1. Actualizar la etiqueta del mes/año
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.of("es", "ES"));
        String date = displayedDate.format(formatter);
        monthYearLabel.setText(date.substring(0, 1).toUpperCase() + date.substring(1));

        // 2. Limpiar la rejilla de días anterior
        daysGridPanel.removeAll();

        // 3. Añadir las cabeceras de los días de la semana (L, M, M, etc.)
        String[] dayNames = {"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};
        for (String dayName : dayNames) {
            JLabel dayLabel = new JLabel(dayName, SwingConstants.CENTER);
            dayLabel.setFont(FontLoader.loadFont("/fonts/Montserrat-Bold.ttf", 16f));
            dayLabel.setBackground(new Color(125, 200, 250));
            dayLabel.setOpaque(true);
            daysGridPanel.add(dayLabel);
        }

        // 4. Calcular el primer día del mes y su posición en la semana
        LocalDate firstDayOfMonth = displayedDate.withDayOfMonth(1);
        int dayOfWeekValue = firstDayOfMonth.getDayOfWeek().getValue(); // 1=Lunes, 7=Domingo

        // 5. Añadir celdas vacías para los días del mes anterior
        for (int i = 1; i < dayOfWeekValue; i++) {
            daysGridPanel.add(new JLabel("")); // Celdas en blanco
        }

        // 6. Llenar la rejilla con los días del mes actual
        int daysInMonth = displayedDate.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            ModernButton dayButton = ModernButtonFactory.transparent(String.valueOf(day));
            dayButton.setFontSize(15f);
            dayButton.setFocusable(false);

            LocalDate aDate = displayedDate.withDayOfMonth(day);

            // Resaltar el día de hoy
            if (aDate.equals(LocalDate.now())) {
                dayButton.setBorderColor(new Color(227, 93, 48));
                dayButton.setBackgroundColors(new Color(247, 133, 88),
                                            new Color(252, 178, 146),
                                            new Color(46, 204, 113));
                lastPressedBtn = dayButton;
            }
            if(datesPressed.contains(aDate)){
                dayButton.setBorderColor(new Color(14, 121, 182));
                dayButton.setBackgroundColors(new Color(14, 121, 182),
                                            new Color(64, 161, 212),
                                            new Color(46, 204, 113));
            }

            // Añadir acción de clic
            dayButton.addActionListener(e -> setPressedButton(dayButton));
            dayButton.addActionListener(e -> sendDate(aDate, dayButton));

            daysGridPanel.add(dayButton);
        }

        // 7. Refrescar el panel
        daysGridPanel.revalidate();
        daysGridPanel.repaint();
    }

    private void setPressedButton(ModernButton button){
        if(lastPressedBtn != button){
            lastPressedBtn.setPressed(false);
            button.setPressed(true);
            lastPressedBtn = button;
        }else{
            button.setPressed(false);
        }
    }

    public void setPressedDates(List<LocalDate> dates){
        datesPressed = dates;
        updateCalendar();
    }

    public void sendDate(LocalDate date, ModernButton button){
        if(lastPressedBtn != button){
            scheduleView.searchSchedulesByDate(date);
        }else{
            scheduleView.searchSchedulesByDate(null);
        }
    }

}