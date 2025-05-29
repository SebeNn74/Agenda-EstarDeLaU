package com.uptc.is.model.domain;

import java.util.List;

public class Cashier {

    private final String nuip;
    private String names;
    private String surnames;
    private String studentCode;
    private String telNumber;
    private String email;

    private List<Schedule> workingSchedule;
    private List<Schedule> classSchedule;

    public Cashier(String nuip, String studentCode){
        this.nuip = nuip;
        this.studentCode = studentCode;
    }

    //Getters y Setters

    public String getNuip() {
        return nuip;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Schedule> getWorkingSchedule() {
        return workingSchedule;
    }

    public void setWorkingSchedule(List<Schedule> workingSchedule) {
        this.workingSchedule = workingSchedule;
    }

    public List<Schedule> getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(List<Schedule> classSchedule) {
        this.classSchedule = classSchedule;
    }
}
