package com.uptc.is.model.domain;

import com.uptc.is.util.IdGenerator;

import java.time.LocalDate;

public class Schedule {

    private final String id;
    private String cashier;
    private LocalDate date;
    private TimeSlot timeSlot;

    public Schedule() {
        this.id = IdGenerator.generateId("H");
        this.timeSlot = new TimeSlot();
    }

    //Getters y Setters

    public String getID() {
        return id;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", cashier='" + cashier + '\'' +
                ", date=" + date +
                ", timeSlot=" + timeSlot +
                '}';
    }
}
