package com.uptc.is.view.swing;

import com.uptc.is.model.domain.Schedule;
import com.uptc.is.model.domain.ScheduleType;
import com.uptc.is.view.contracts.IScheduleView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ScheduleView implements IScheduleView {

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
    public void showScheduleDetails(Schedule schedule) {

    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void displaySuccessMessage(String message) {

    }

    @Override
    public void closeView() {

    }

    @Override
    public void setPresenter(Object presenter) {

    }
}
