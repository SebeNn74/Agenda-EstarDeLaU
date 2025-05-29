package com.uptc.is.model.domain;

import com.uptc.is.util.IdGenerator;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlot {

    private final String id;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(DayOfWeek day, LocalTime startTime, LocalTime endTime){
        this.id = IdGenerator.generateId("F");
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //Getters y Setters

    public String getID() {
        return id;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

}
