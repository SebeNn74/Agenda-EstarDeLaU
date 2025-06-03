package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Cashier;
import com.uptc.is.model.domain.Schedule;
import com.uptc.is.presenter.SchedulePresenter;
import com.uptc.is.view.contracts.IScheduleView;
import com.uptc.is.view.custom_components.MessageDialog;

import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleView  extends JPanel implements IScheduleView {

    private SchedulePresenter presenter;
    private final CardLayout cardLayout;
    private ScheduleFormPanel formPanel;
    private GenCalendarPanel calendarPanel;

    public ScheduleView(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        configPanel();
    }

    private void configPanel(){
        formPanel = new ScheduleFormPanel(this);
        calendarPanel = new GenCalendarPanel(this);

        this.add(formPanel, "form");
        this.add(calendarPanel, "calendar");
    }

    @Override
    public void displaySchedules(List<Schedule> schedules) {
        formPanel.updateSchedules(schedules);
        calendarPanel.updateSchedules(schedules);
    }

    @Override
    public void displayCalendar(List<Schedule> schedules) {
        calendarPanel.updateCalendar(schedules);
    }

    @Override
    public void displayCashierList(List<Cashier> cashiers) {
        formPanel.updateCashiers(cashiers);
    }

    @Override
    public void clearForm() {
        formPanel.clearForm();
    }

    @Override
    public void clearSearchField() {
        formPanel.clearSearchField();
    }

    @Override
    public void createSchedule() {
        presenter.createSchedule();
    }

    @Override
    public void removeSchedule() {
        presenter.removeSchedule();
    }

    @Override
    public String getNuipInput() {
        return formPanel.getNuip();
    }

    @Override
    public LocalDate getDateInput() {
        return formPanel.getDate();
    }

    @Override
    public DayOfWeek getDayOfWeekInput() {
        return formPanel.getDay();
    }

    @Override
    public LocalTime getStartTimeInput() {
        return formPanel.getStarTime();
    }

    @Override
    public LocalTime getEndTimeInput() {
        return formPanel.getEndTime();
    }

    @Override
    public void searchCashier(String nuip) {
        presenter.selectCashier(nuip);
    }

    @Override
    public void searchSchedule(String id) {
        presenter.selectSchedule(id);
    }

    @Override
    public void searchSchedulesByDate(LocalDate date) {
        presenter.selectDate(date);
    }

    @Override
    public void showCashierNuip(String nuip) {
        formPanel.setNuip(nuip);
    }

    @Override
    public void showScheduleFormPanel() {
        cardLayout.show(this, "form");
        presenter.loadData();
    }

    @Override
    public void showGenCalendarPanel() {
        cardLayout.show(this, "calendar");
        presenter.loadData();
    }

    @Override
    public void showScheduleDetails(Schedule schedule) {
        formPanel.setSchedule(schedule);
    }

    @Override
    public void displayError(String message) {
        new MessageDialog(null, message, MessageDialog.MessageType.ERROR);
    }

    @Override
    public void displayMessage(String message) {
        new MessageDialog(null, message, MessageDialog.MessageType.SUCCESS);
    }

    @Override
    public void setPresenter(SchedulePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

}
