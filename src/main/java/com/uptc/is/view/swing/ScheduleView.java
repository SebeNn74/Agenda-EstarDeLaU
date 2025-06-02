package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Schedule;
import com.uptc.is.model.domain.ScheduleType;
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
    private JFrame frame;
    private CardLayout cardLayout;
    private ScheduleFormPanel formPanel;
    private CashierSchedulePanel listPanel;
    private GenCalendarPanel calendarPanel;

    public ScheduleView(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        configPanel();
    }

    private void configPanel(){
        formPanel = new ScheduleFormPanel(this);
        listPanel = new CashierSchedulePanel();
        calendarPanel = new GenCalendarPanel();

        this.add(formPanel, "form");
        this.add(listPanel, "list");
        this.add(calendarPanel, "calendar");
    }

    @Override
    public void displaySchedules(List<Schedule> schedules) {

    }

    @Override
    public void setViewContext(String contextMessage, ScheduleType type) {

    }

    @Override
    public void clearForm() {

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
    public void createTimeSlot() {
        presenter.createTimeSlot();
    }

    @Override
    public void removeTimeSlot() {
        presenter.removeTimeSlot();
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
    public String getSelectedScheduleId() {
        return "";
    }

    @Override
    public String getSelectedTimeSlotId() {
        return "";
    }

    @Override
    public void showCashierNuip(String nuip) {
        formPanel.setNuip(nuip);
    }

    @Override
    public void showScheduleFormPanel() {
        cardLayout.show(this, "form");
    }

    @Override
    public void showScheduleListPanel() {
        cardLayout.show(this, "list");
    }

    @Override
    public void showScheduleDetails(Schedule schedule) {

    }

    @Override
    public void displayError(String message) {
        new MessageDialog(this.frame, message, MessageDialog.MessageType.ERROR);
    }

    @Override
    public void displayMessage(String message) {
        new MessageDialog(this.frame, message, MessageDialog.MessageType.SUCCESS);
    }

    @Override
    public void setPresenter(SchedulePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public JPanel getPanel() {
        return this;
    }

    @Override
    public void setParentFrame(JFrame parentFrame) {
        this.frame = parentFrame;
    }
}
