package com.uptc.is.model.domain;

import com.uptc.is.util.IdGenerator;

import java.time.LocalDate;
import java.util.HashMap;

public class Schedule {

    private final String id;
    private LocalDate date;
    private HashMap<String, TimeSlot> timeSlots;

    public Schedule(String id, LocalDate date){
        this.id = IdGenerator.generateId("H");
        this.date = date;
        this.timeSlots = new HashMap<>();
    }

    //Añadir nueva franja horaria
    public boolean addTimeSlot(TimeSlot timeSlot){
        if(timeSlots.containsKey(timeSlot.getID())){
            timeSlots.put(timeSlot.getID(), timeSlot );
            return true;
        }
        return false;
    }

    //Eliminar franja horaria
    public boolean removeTimeSlot(String timeSlotID){
        return false;
    }

    //Getters y Setters

    public String getID() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public HashMap<String, TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(HashMap<String, TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

}
