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

    public ScheduleView(){
        this.setBackground(new Color(133, 193, 233));
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
    public LocalDate getDateInput() {
        return null;
    }

    @Override
    public DayOfWeek getDayOfWeekInput() {
        return null;
    }

    @Override
    public LocalTime getStartTimeInput() {
        return null;
    }

    @Override
    public LocalTime getEndTimeInput() {
        return null;
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
    public void showScheduleDetails(Schedule schedule) {

    }

    @Override
    public void displayError(String title, String message) {
        new MessageDialog(this.frame, message, MessageDialog.MessageType.ERROR);
    }

    @Override
    public void displayMessage(String title, String message) {
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
